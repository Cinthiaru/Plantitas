package mx.uv.herbolario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarUsuario extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        et1=(EditText)findViewById(R.id.editText3);
        et2=(EditText)findViewById(R.id.editText4);
        et3=(EditText)findViewById(R.id.editText5);
        et4=(EditText)findViewById(R.id.editText6);
    }
    public void registar(View view){
        DBHelper admin= new DBHelper(this,"Herbolario",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String nombre= et1.getText().toString();
        String usuario= et2.getText().toString();
        String contrasena= et3.getText().toString();
        String tipo= et4.getText().toString();

        ContentValues values= new ContentValues();
        values.put("nombre",nombre);
        values.put("usuario",usuario);
        values.put("contrasena",contrasena);
        values.put("tipo",tipo);

        db.insert("usuarios",null,values);
        db.close();

        Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_LONG).show();

        Intent ventana= new Intent(this, MainActivity.class);
        startActivity(ventana);

        }
    }


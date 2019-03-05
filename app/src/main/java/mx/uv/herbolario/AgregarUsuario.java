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
    UsuariosOperaciones usr=new UsuariosOperaciones(this);
    Usuario us;
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
        String nombre= et1.getText().toString();
        String usuario= et2.getText().toString();
        String contrasena= et3.getText().toString();
        String tipo= et4.getText().toString();

        usr.open();
        us=usr.addUsuario(nombre,usuario,contrasena,tipo);
        usr.close();

        Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_LONG).show();

        Intent ventana= new Intent(this, usuarios.class);
        startActivity(ventana);

        }
    }


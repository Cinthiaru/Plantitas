package mx.uv.herbolario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class usuarios extends AppCompatActivity {

    ListView listaUsuarios;
    ArrayList<String> lista;
    ArrayAdapter adapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        DBHelper db=new DBHelper(getApplicationContext(),null,null,1);
        listaUsuarios =(ListView) findViewById(R.id.listUsuarios);
        cargarListado();
    }
    private void cargarListado(){
        lista = listaPersonas();
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);
        listaUsuarios.setAdapter(adapter);
    }

    private ArrayList<String> listaPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        DBHelper helper= new DBHelper(this,"Herbolario",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from usuarios";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst()) {
            do{
                String linea = "Nombre: "+c.getString(1)+ " " +
                        c.getString(2)+"\n"
                        +"Usuario: " + c.getString(3) + "\n"
                        +"Contraseña: " + c.getString(4)+ "\n"
                        + "Tipo usuario: " + c.getString(5);
                datos.add(linea);

            }while(c.moveToNext());

        }
        db.close();
        return datos;

    }

    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
}

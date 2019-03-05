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
    UserList usr= new UserList();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        DBHelper db=new DBHelper(getApplicationContext(),null,null,1);
        listaUsuarios =(ListView) findViewById(R.id.listUsuarios);
    }

    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
}

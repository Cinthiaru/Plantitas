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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class usuarios extends AppCompatActivity {

    ListView listaUsuarios;
    ArrayAdapter<String> adapter;
    private UsuariosOperaciones usuariosdb;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        usuariosdb = new UsuariosOperaciones(this);
        usuariosdb.open();
        listaUsuarios =this.findViewById(R.id.listUsuarios);
        LlenarListaUsuarios();
    }

    public void LlenarListaUsuarios(){
        List usuarios = usuariosdb.getAllUsuarios();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usuarios);
        listaUsuarios.setAdapter(adapter);
    }

    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
}

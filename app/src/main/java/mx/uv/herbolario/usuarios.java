package mx.uv.herbolario;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class usuarios extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView listaUsuarios;
    ArrayAdapter<String> adapter;
    private UsuariosOperaciones usuariosdb;
    private Object action;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        usuariosdb = new UsuariosOperaciones(this);
        usuariosdb.open();
        listaUsuarios = this.findViewById(R.id.listUsuarios);
        LlenarListaUsuarios();
        borrar();


    }

    public void LlenarListaUsuarios() {
        List usuarios = usuariosdb.getAllUsuarios();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usuarios);
        listaUsuarios.setAdapter(adapter);
    }
   //PARA SELECCIONAR ACCION AL PRESIONAR EL ITEM
    public void borrar() {
        listaUsuarios.setLongClickable(true);
        listaUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //COMO EL ADMIN TIENE ID 1 ENTONCES LE SUMO A LA POSICION//
                String nombre= (String)listaUsuarios.getAdapter().getItem(position);
                usuariosdb.deleteUsuario(nombre);
                Toast.makeText(getApplicationContext(), "Usuario eliminado", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        listaUsuarios.setOnItemClickListener(this);
    }

    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
    public void atras(View vw){
        Intent agregar= new Intent(this,PantallaAdmin.class);
        startActivity(agregar);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

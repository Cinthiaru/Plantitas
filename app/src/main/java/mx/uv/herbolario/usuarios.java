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
    int seleccionado;

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
        listaUsuarios.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listaUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado=position;
                action = usuarios.this.startActionMode(amc);
                view.setSelected(true);
                return true;
            }
        });
        //listaUsuarios.setOnItemClickListener(this);
    }
    private ActionMode.Callback amc = new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            //SI LA OPCION ES ELIMINAR
            if(item.getItemId() == R.id.EliminarItem){
                String nombre= (String)listaUsuarios.getAdapter().getItem(seleccionado);
                usuariosdb.deleteUsuario(nombre);
                Toast.makeText(getApplicationContext(), "Usuario eliminado", Toast.LENGTH_LONG).show();
            //SI LA OPCION ES EDITAR
            }else if(item.getItemId() == R.id.EditarItem){
                String nombre= (String)listaUsuarios.getAdapter().getItem(seleccionado);
                Intent in = new Intent(usuarios.this,Editar.class);
                in.putExtra("nombre", nombre);
                startActivity(in);
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

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

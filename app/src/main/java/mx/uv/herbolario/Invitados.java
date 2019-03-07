package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Invitados extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView listaPlant;
    ArrayAdapter<String> adapter;
    private PlantasOperaciones plantaDB;
    String nombre;
    private Object action;
    int seleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados);
        plantaDB = new PlantasOperaciones(this) ;
        plantaDB.open();
        listaPlant =this.findViewById(R.id.listPlantas);
        cargarListado();
        menu();
    }

    public void menu(){
        listaPlant.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listaPlant.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                seleccionado=position;
                action = Invitados.this.startActionMode(amc);
                view.setSelected(true);
                return true;
            }
        });
    }

    private ActionMode.Callback amc = new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones_invitado,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.EditarItem){
                String nombre= (String)listaPlant.getAdapter().getItem(seleccionado);
                Intent agregar= new Intent(Invitados.this, verDetalles.class);
                agregar.putExtra("nombre", nombre);
                startActivity(agregar);
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

    public void cargarListado(){
        List listado = plantaDB.getAllPlantas();
        adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);

        listaPlant.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

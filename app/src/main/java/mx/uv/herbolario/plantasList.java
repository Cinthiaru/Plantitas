package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class plantasList extends AppCompatActivity implements ListView.OnItemClickListener {
    ListView listaPlant;
    ArrayAdapter<String> adapter;
    PlantasOperaciones plantaDB = new PlantasOperaciones(this) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas_list);
        plantaDB= new PlantasOperaciones(this);
        plantaDB.open();
        listaPlant =this.findViewById(R.id.listPlantas);
        cargarListado();
        borrar();
    }

    public void borrar() {
        listaPlant.setLongClickable(true);
        listaPlant.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre= (String)listaPlant.getAdapter().getItem(position);
                plantaDB.deletePlanta(nombre);
                Toast.makeText(getApplicationContext(), "Planta "+nombre+" eliminada", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        listaPlant.setOnItemClickListener(this);
    }

     public void cargarListado(){
      List listado = plantaDB.getAllPlantas();
         adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);

         listaPlant.setAdapter(adapter);
      }

    public void agregar(View view){
        Intent agregar= new Intent(this, AgregarPlanta.class);
        startActivity(agregar);
    }

    public void atras(View vw){
        Intent pantallaAdmin= new Intent(this,PantallaAdmin.class);
        startActivity(pantallaAdmin);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class plantasList extends AppCompatActivity {
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
}

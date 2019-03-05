package mx.uv.herbolario;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class PlantaList extends AppCompatActivity {

    ListView listaPlant;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas);

        listaPlant = (ListView) this.findViewById(R.id.listPlantas);
        cargarListado();
    }

    public void cargarListado(){
        listado = listaPlantas();
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listaPlant.setAdapter(adapter);
    }

    public ArrayList<String> listaPlantas(){
        ArrayList<String> datos = new ArrayList<String>();
        DBHelper helper= new DBHelper(this,"Herbolario",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from planta";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst()) {
            do{
                String linea =
                        "Nombre: " + c.getString(1) + "\n" +
                        "Nombre científico: " + c.getString(2) + "\n" +
                        "Familia: " + c.getString(3) + "\n" +
                        "Usos: " + c.getString(4) + "\n" +
                        "Descripción: " + c.getString(5) + "\n" +
                        "Propiedades: " + c.getString(6) + "\n" +
                        "Contraindicaciones: " + c.getString(7) + "\n" +
                        "Imagen: " + c.getString(8);
                datos.add(linea);

            }while(c.moveToNext());

        }
        db.close();
        return datos;

    }

}

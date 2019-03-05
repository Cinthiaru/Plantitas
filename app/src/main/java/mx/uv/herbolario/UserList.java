package mx.uv.herbolario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    ListView listaUser;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaUser = (ListView) this.findViewById(R.id.listUsuarios);
        cargarListado();
    }

    public void cargarListado(){
        listado = listaPersonas();
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listaUser.setAdapter(adapter);
    }

    public ArrayList<String> listaPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        DBHelper helper= new DBHelper(this,"Herbolario",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select nombre,usuario from usuarios";
        Cursor c = db.rawQuery(sql,null);

        if (c.moveToFirst()) {
            do{
                String linea = "Nombre: "+c.getString(1)+ " "
                        +"Usuario: " + c.getString(2);
                datos.add(linea);
            }while(c.moveToNext());

        }
        db.close();
        return datos;

    }
}

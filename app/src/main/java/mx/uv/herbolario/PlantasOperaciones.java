package mx.uv.herbolario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlantasOperaciones {

    //Database fields
    private DataBaseHelper dbHelper;

    //Columnas de la tabla Planta
    private String[] PLANTA_TABLE_COLUMNS = { DataBaseHelper.PLANTA_ID, DataBaseHelper.PLANTA_NOMBRE, DataBaseHelper.PLANTA_NOMBRECIENT, DataBaseHelper.PLANTA_FAMILIA, DataBaseHelper.PLANTA_USOS, DataBaseHelper.PLANTA_DESCRIPCION, DataBaseHelper.PLANTA_PROPIEDADES, DataBaseHelper.PLANTA_CONTRAINDI, DataBaseHelper.PLANTA_IMAGEN };

    //Ayudante de base de datos
    private SQLiteDatabase database;


    public PlantasOperaciones(Context context) { dbHelper = new DataBaseHelper(context);}

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Método de inserción
    public Planta addPlanta(String nombre, String nombreCientifico, String familia, String usos,
                            String descripcion, String propiedades, String contraindicaciones,
                            String imagen) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.PLANTA_NOMBRE, nombre);
        values.put(DataBaseHelper.PLANTA_NOMBRECIENT, nombreCientifico);
        values.put(DataBaseHelper.PLANTA_FAMILIA, familia);
        values.put(DataBaseHelper.PLANTA_USOS, usos);
        values.put(DataBaseHelper.PLANTA_DESCRIPCION, descripcion);
        values.put(DataBaseHelper.PLANTA_PROPIEDADES, propiedades);
        values.put(DataBaseHelper.PLANTA_CONTRAINDI, contraindicaciones);
        values.put(DataBaseHelper.PLANTA_IMAGEN, imagen);

        long plantaId = database.insert(DataBaseHelper.PLANTA, null, values);
        // now that the PLANTA is created return it ...
        Cursor cursor = database.query(DataBaseHelper.PLANTA,
                PLANTA_TABLE_COLUMNS, DataBaseHelper.PLANTA_ID + " = "
                        + plantaId, null, null, null, null);

        cursor.moveToFirst();

        Planta newComment = parsePlanta(cursor);
        cursor.close();
        return newComment;
    }

    public void deletePlanta(String seleccionado) {
        database.execSQL("DELETE FROM PLANTA WHERE _nombre ='" + seleccionado+"'");
    }

    public List<String> getAllPlantas() {
        List<String> PLANTA = new ArrayList<String>();
        Cursor cursor = database.query(DataBaseHelper.PLANTA,
                new String[]{DataBaseHelper.PLANTA_NOMBRE}, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                PLANTA.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();

        return PLANTA;
    }

    private Planta parsePlanta(Cursor cursor) {
        Planta planta = new Planta();
        planta.setId((cursor.getInt(0)));
        planta.setNombre(cursor.getString(1));
        planta.setNombreCientifico(cursor.getString(2));
        planta.setFamilia(cursor.getString(3));
        planta.setUsos(cursor.getString(4));
        planta.setDescripcion(cursor.getString(5));
        planta.setPropiedades(cursor.getString(6));
        planta.setContraindicaciones(cursor.getString(7));
        planta.setImagen(cursor.getString(8));

        return planta;
    }


}

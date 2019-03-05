package mx.uv.herbolario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    /*
    Realiza un insert en la base de datos
     */
    public void insertData(String nombre, String nombreCientifico, String familia,
                           String descripcion, String propiedades,
                           String contraindicaciones, byte[] imagen){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO PLANTA VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nombre);
        statement.bindString(2, nombreCientifico);
        statement.bindString(3, familia);
        statement.bindString(4, descripcion);
        statement.bindString(5, propiedades);
        statement.bindString(6,contraindicaciones);
        statement.bindBlob(7, imagen);

        statement.executeInsert();
    }

    /*
    Realiza un update en la base de datos
     */
    public void updateData(String nombre, String nombreCientifico, String familia,
                           String descripcion, String propiedades,
                           String contraindicaciones, byte[] imagen,
                           int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE PLANTA SET nombre = ?, nombreCientifico = ?, " +
                "familia = ?, descripcion = ?, propiedades = ?, " +
                "contraindicaciones = ?, imagen = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nombre);
        statement.bindString(2, nombreCientifico);
        statement.bindString(3, familia);
        statement.bindString(4, descripcion);
        statement.bindString(5, propiedades);
        statement.bindString(6, contraindicaciones);
        statement.bindBlob(7, imagen);
        statement.bindDouble(8, (double)id);

        statement.execute();
        database.close();
    }

    /*
    Realiza un delete en la base de datos
     */
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM PLANTA WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

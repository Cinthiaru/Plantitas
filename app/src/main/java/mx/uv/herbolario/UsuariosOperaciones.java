package mx.uv.herbolario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UsuariosOperaciones {
    // Database fields
    List<String> USUARIO = new ArrayList<String>();
    private DataBaseHelper dbHelper;
    private String[] USUARIOS_TABLE_COLUMNS = { DataBaseHelper.USUARIO_ID, DataBaseHelper.USUARIO_NOMBRE, DataBaseHelper.USUARIO_ROL, DataBaseHelper.USUARIO_USU, DataBaseHelper.USUARIO_PASSWORD };
    private SQLiteDatabase database;

    public UsuariosOperaciones(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Busca donde el usuarios y el password sean iguales y se trae el rol
    public String login(String usuario, String password){
        String rol="";
        String whereClause = "_usuario = ? AND _password = ?";
        String[] whereArgs = new String[] {
                usuario,
                password
        };
        Cursor cursor = database.query(DataBaseHelper.USUARIO,
                new String[]{DataBaseHelper.USUARIO_USU, DataBaseHelper.USUARIO_PASSWORD, DataBaseHelper.USUARIO_ROL }, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            rol=cursor.getString(2);
        }
        cursor.close();
        return rol;
    }

    public Usuario addUsuario(String nombre, String usuario, String contrasena, String rol) {

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.USUARIO_NOMBRE, nombre);
        values.put(DataBaseHelper.USUARIO_USU,usuario);
        values.put(DataBaseHelper.USUARIO_PASSWORD,contrasena);
        values.put(DataBaseHelper.USUARIO_ROL,rol);

        long usuarioId = database.insert(DataBaseHelper.USUARIO, null, values);
        // now that the USUARIO is created return it ...
        Cursor cursor = database.query(DataBaseHelper.USUARIO,
                USUARIOS_TABLE_COLUMNS, DataBaseHelper.USUARIO_ID + " = "
                        + usuarioId, null, null, null, null);

        cursor.moveToFirst();

        Usuario newComment = parseUsuario(cursor);
        cursor.close();
        return newComment;

    }

    public void UsuarioDefault() {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.USUARIO_ID,"1");
        values.put(DataBaseHelper.USUARIO_NOMBRE, "admin");
        values.put(DataBaseHelper.USUARIO_USU,"admin");
        values.put(DataBaseHelper.USUARIO_PASSWORD,"admin");
        values.put(DataBaseHelper.USUARIO_ROL,"admin");

        long usuarioId = database.insert(DataBaseHelper.USUARIO, null, values);
    }

    public void deleteUsuario(String seleccionado) {
        database.execSQL("DELETE FROM USUARIO WHERE _usuario ='" + seleccionado+"'");
    }

    public List<String> getAllUsuarios() {
        Cursor cursor = database.query(DataBaseHelper.USUARIO,
                new String[]{DataBaseHelper.USUARIO_USU}, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                USUARIO.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return USUARIO;
    }

    public void updateUsuarios(String nombre){
        ContentValues cv = new ContentValues();
        cv.put("Field1","Bob"); //These Fields should be your String values of actual column names
        cv.put("Field2","19");
        cv.put("Field2","Male");
    }

    public String[] getUsuarioByNombre(String nombre){
        String listUsuario []= new  String[5];
        Cursor cursor = database.query(DataBaseHelper.USUARIO,
                USUARIOS_TABLE_COLUMNS, DataBaseHelper.USUARIO_USU + " = "
                        + nombre, null, null, null, null);
        if(cursor.moveToFirst()) {
            listUsuario[0] = new String(cursor.getString(0));
            listUsuario[1] = new String(cursor.getString(1));
            listUsuario[2] = new String(cursor.getString(2));
            listUsuario[3] = new String(cursor.getString(3));
            listUsuario[4] = new String(cursor.getString(4));
        }
        cursor.close();
        return listUsuario;
    }

    private Usuario parseUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId((cursor.getInt(0)));
        usuario.setNombre(cursor.getString(1));
        usuario.setRol(cursor.getString(2));
        usuario.setUser(cursor.getString(3));
        usuario.setPassword(cursor.getString(4));
        return usuario;
    }
}

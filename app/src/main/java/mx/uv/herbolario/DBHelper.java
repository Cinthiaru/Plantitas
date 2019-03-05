package mx.uv.herbolario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
       public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
           super(context, name, factory, version);
       }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table usuarios(codigo integer primary key autoincrement,nombre text, usuario tex,contrasena text, tipo text)");

            db.execSQL("insert into usuarios values(01,'admin','admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("create table usuarios(codigo integer primary key autoincrement,nombre text, usuario tex,contrasena text, tipo text)");
            db.execSQL("insert into usuarios values(01,'admin','admin','admin')");
    }

    public ArrayList llenar(){
        ArrayList<String>lista=new ArrayList();
        SQLiteDatabase conn=this.getWritableDatabase();
        Cursor c= conn.rawQuery("SELECT * FROM usuarios",null);
        if (c.moveToFirst()){
            do {
                lista.add(c.getString(1));
            }while(c.moveToNext());
        }
        return lista;
    }

}

package mx.uv.herbolario;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Bloque apartado de Usuario
    public static final String USUARIO = "USUARIO";
    public static final String USUARIO_ID = "_id";
    public static final String USUARIO_NOMBRE = "_nombre";
    public static final String USUARIO_PASSWORD = "_password";
    public static final String USUARIO_USU = "_usuario";
    public static final String USUARIO_ROL = "_rol";

    //Bloque apartado de Planta
    public static final String PLANTA = "PLANTA";
    public static final String PLANTA_ID = "_id";
    public static final String PLANTA_NOMBRE = "_nombre";
    public static final String PLANTA_NOMBRECIENT = "_nombreCientifico";
    public static final String PLANTA_FAMILIA = "_familia";
    public static final String PLANTA_USOS = "_usos";
    public static final String PLANTA_DESCRIPCION = "_descripcion";
    public static final String PLANTA_PROPIEDADES = "_propiedades";
    public static final String PLANTA_CONTRAINDI = "_contraindicaciones";
    public static final String PLANTA_IMAGEN = "_imagen";


    //Definición de la base de datos
    private static final String DATABASE_NAME = "Herbolario.db";
    private static final int DATABASE_VERSION = 1;

    //Instrucción de creación de la tabla Usuario
    private static final String DATABASE_CREATE = "create table " + USUARIO
            + "(" + USUARIO_ID + " integer primary key autoincrement, "
            + USUARIO_NOMBRE + " text not null,"
            + USUARIO_PASSWORD +" text not null,"
            + USUARIO_USU +" text not null,"
            + USUARIO_ROL +" text not null);";

    //Instrucción de creación de la tabla Planta
    private static final String DATABASE_CREATE_TWO = "create table " + PLANTA
            + "(" + PLANTA_ID + " integer primary key autoincrement, "
            + PLANTA_NOMBRE + " text not null,"
            + PLANTA_NOMBRECIENT + " text not null,"
            + PLANTA_FAMILIA + " text,"
            + PLANTA_USOS + " text ,"
            + PLANTA_DESCRIPCION + " text ,"
            + PLANTA_PROPIEDADES + " text,"
            + PLANTA_CONTRAINDI + " text ,"
            + PLANTA_IMAGEN + " text );";
            //+ PLANTA_IMAGEN + " blob not null);";


    //Instrucción de inserción a tabla Usuario
    private static final String DATABASE_USER = "insert into " + USUARIO
            +"(" +USUARIO_NOMBRE+","
            +USUARIO_USU+","
            +USUARIO_PASSWORD+","
            +USUARIO_ROL+")"
            + "values ('admin', 'admin', 'admin', 'admin');";

    private static final String DATABASE_PLANTA = "insert into " + PLANTA
            +"(" +PLANTA_NOMBRE+","
            +PLANTA_NOMBRECIENT+","
            +PLANTA_FAMILIA+","
            +PLANTA_PROPIEDADES+")"
            + "values ('Ruda', 'sdbhhbds', 'arbusto', 'es muy ruda');";

    //Contexto
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_TWO);
        db.execSQL(DATABASE_USER);
        db.execSQL(DATABASE_PLANTA );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + PLANTA );
        onCreate(db);
    }
}

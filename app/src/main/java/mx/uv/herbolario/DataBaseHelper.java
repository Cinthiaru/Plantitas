package mx.uv.herbolario;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USUARIO = "USUARIOS";
    public static final String USUARIO_ID = "_id";
    public static final String USUARIO_NOMBRE = "_nombre";
    public static final String USUARIO_PASSWORD = "_password";
    public static final String USUARIO_USU = "_usuario";
    public static final String USUARIO_ROL = "_rol";

    private static final String DATABASE_NAME = "Herbolario.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + USUARIO
            + "(" + USUARIO_ID + " integer primary key autoincrement, "
            + USUARIO_NOMBRE + " text not null,"
            + USUARIO_PASSWORD +" text not null,"
            + USUARIO_USU +" text not null,"
            + USUARIO_ROL +" text not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + USUARIO);
        onCreate(db);
    }
}

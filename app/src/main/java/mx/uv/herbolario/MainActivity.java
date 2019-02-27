package mx.uv.herbolario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    private Cursor fila;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText) findViewById(R.id.editText);
        et2=(EditText) findViewById(R.id.editText2);

        boton=(Button) findViewById(R.id.button5);

    }
    public void ingresar(View view) {
        DBHelper admin = new DBHelper(this, "Herbolario", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String usuario = et1.getText().toString();
        String contrasena = et2.getText().toString();
        fila = db.rawQuery("Select usuario, contrasena, tipo from usuarios where usuario='" + usuario + "'and contrasena='" + contrasena + "'", null);

        if (fila.moveToFirst() == true) {
            String usua = fila.getString(0);
            String pass = fila.getString(1);
            String tipo = fila.getString(2);

            if (usuario.equals(usua) && contrasena.equals(pass)) {
                if (tipo.equals("admin")) {
                    Intent ven = new Intent(this, PantallaAdmin.class);
                    startActivity(ven);

                    et1.setText("");
                    et2.setText("");
                    Toast.makeText(getApplicationContext(), "Usuario encontrado", Toast.LENGTH_LONG).show();

                }
                if (usuario.equals(usua) && contrasena.equals(pass)) {
                    if (tipo.equals("user")) {
                        Intent ven = new Intent(this, PantallaUsuario.class);
                        startActivity(ven);

                        et1.setText("");
                        et2.setText("");
                        Toast.makeText(getApplicationContext(), "Usuario encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
    public void salir(View view){
        finish();
    }
}

package mx.uv.herbolario;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    EditText txtUsuario, txtPassword;
    private Cursor fila;
    Button btnIngresar;
    private UsuariosOperaciones usuariosdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuariosdb = new UsuariosOperaciones(this);
        usuariosdb.open();
        txtUsuario=this.findViewById(R.id.editText);
        txtPassword=this.findViewById(R.id.editText2);
        btnIngresar=this.findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String usuario = txtUsuario.getText().toString();
        String contrasena = txtPassword.getText().toString();
        String existeUsuario= usuariosdb.login(usuario, contrasena);

        if(existeUsuario.equals("admin")){
            Intent ven = new Intent(this, PantallaAdmin.class);
            startActivity(ven);
            txtUsuario.setText("");
            txtPassword.setText("");
            Toast.makeText(getApplicationContext(), "Usuario encontrado", Toast.LENGTH_LONG).show();
        }
        if(existeUsuario.equals("user")){
            Intent ven = new Intent(this, PantallaUsuario.class);
            startActivity(ven);

            txtUsuario.setText("");
            txtPassword.setText("");
            Toast.makeText(getApplicationContext(), "Usuario encontrado", Toast.LENGTH_LONG).show();
        }

    }
}

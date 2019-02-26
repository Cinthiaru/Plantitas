package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PantallaAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_admin);
    }
    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
    public void salir(View view){
        Intent salir= new Intent(this,MainActivity.class);
        startActivity(salir);
    }
}

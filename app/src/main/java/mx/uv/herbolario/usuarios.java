package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
    }

    public void agregar(View view){
        Intent agregar= new Intent(this,AgregarUsuario.class);
        startActivity(agregar);
    }
}

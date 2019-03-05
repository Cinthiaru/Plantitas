package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaUsuario extends AppCompatActivity {
     Button salir,plantas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_usuario);

        plantas=(Button)findViewById(R.id.button7);
        salir=(Button)findViewById(R.id.button9);

    }
    public void plantas(View view){

    }
    public void salir(View vw){
        Intent ventana=new Intent(this,MainActivity.class);
    }
}

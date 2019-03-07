package mx.uv.herbolario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Editar extends AppCompatActivity {
    private UsuariosOperaciones usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        String valor = getIntent().getExtras().getString("nombre");
        usu= new UsuariosOperaciones(this);
        usu.open();
        usu.getUsuarioByNombre(valor);
    }
}

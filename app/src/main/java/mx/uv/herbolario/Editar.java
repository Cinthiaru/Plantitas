package mx.uv.herbolario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    private UsuariosOperaciones usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        usu= new UsuariosOperaciones(this);
        usu.open();
        llenarDatos();
    }

    public void llenarDatos(){
        String datos="";
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            datos = parametros.getString("datos");
        }
        String [] usua= usu.getUsuarioByNombre(datos);
        String nombre = usua[1];
        Toast.makeText(getApplicationContext(), nombre+"", Toast.LENGTH_LONG).show();
    }
}

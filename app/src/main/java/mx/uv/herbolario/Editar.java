package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editar extends AppCompatActivity implements View.OnClickListener{
    private UsuariosOperaciones usu;
    EditText nombreUsuario;
    EditText name;
    EditText contrasena;
    Button btnActualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nombreUsuario = this.findViewById(R.id.txtNombreUsuario);
        name = this.findViewById(R.id.txtNombre);
        contrasena= this.findViewById(R.id.txtContrasena);
        btnActualizar= this.findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this);

        String datos="";
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null ){
            datos = parametros.getString("nombre");
        }

        usu= new UsuariosOperaciones(this);
        usu.open();
        llenarDatos(datos);
    }

    public void llenarDatos(String datos){
        Usuario us = usu.getUsuarioByNombre(datos);
        name.setText(us.getNombre());
        nombreUsuario.setText(us.getUser());
        contrasena.setText(us.getPassword());
    }

    public void Editar(){
        String nombre = name.getText().toString();
        String usuario = nombreUsuario.getText().toString();
        String contra = contrasena.getText().toString();
        if(usu.updateUsuarios(nombre, usuario, contra)){
            Toast.makeText(getApplicationContext(),"Actualización exitosa.",Toast.LENGTH_LONG).show();
        }
        Intent ventana= new Intent(this, usuarios.class);
        startActivity(ventana);
    }

    @Override
    public void onClick(View v) {
        Editar();
    }
}

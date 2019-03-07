package mx.uv.herbolario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarPlanta extends AppCompatActivity {

    EditText edtNombre;
    EditText edtNombreCientifico;
    EditText edtFamilia;
    EditText edtUsos;
    EditText edtDescripcion;
    EditText edtPropiedades;
    EditText edtContraindicaciones;
    EditText edtImagen;
    Button botonRegistrarPlanta;
    int id=0;

    PlantasOperaciones pla=new PlantasOperaciones(this);

    Planta pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_planta);

        edtNombre = (EditText) findViewById(R.id.editTextNombre);
        edtNombreCientifico = (EditText) findViewById(R.id.editTextNombreCientifico);
        edtFamilia = (EditText) findViewById(R.id.editTextFamilia);
        edtUsos = (EditText) findViewById(R.id.editTextUsos);
        edtDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        edtPropiedades = (EditText) findViewById(R.id.editTextPropiedades);
        edtContraindicaciones = (EditText) findViewById(R.id.editTextContraindicaciones);
        edtImagen = (EditText) findViewById(R.id.editTextImagen);
        botonRegistrarPlanta = (Button) findViewById(R.id.botonRegistrarPlanta);

        Bundle parametros = this.getIntent().getExtras();
        String nombre = parametros.getString("nombre");
        if(parametros !=null && nombre!="" && nombre!=null){
            pla.open();
            pl=pla.getPlanta(nombre);
            pla.close();


            edtNombre.setText(pl.getNombre());
            edtNombreCientifico.setText(pl.getNombreCientifico());
            edtFamilia.setText(pl.getFamilia());
            edtUsos.setText(pl.getUsos());
            edtDescripcion.setText(pl.getDescripcion());
            edtPropiedades.setText(pl.getPropiedades());
            edtContraindicaciones.setText(pl.getContraindicaciones());
            edtImagen.setText(pl.getImagen());
            id=pl.getId();

            Toast.makeText(getApplicationContext(),nombre,Toast.LENGTH_LONG).show();
            botonRegistrarPlanta.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    editarPlanta(v);
                }
            });
        }else{
            botonRegistrarPlanta.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    registarPlanta(v);
                }
            });
        }
    }

    public void registarPlanta(View view){
        String nombre = edtNombre.getText().toString();
        String nombreCientifico = edtNombreCientifico.getText().toString();
        String familia = edtFamilia.getText().toString();
        String usos = edtUsos.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String propiedades = edtPropiedades.getText().toString();
        String contraindicaciones = edtContraindicaciones.getText().toString();
        String imagen = edtImagen.getText().toString();

        pla.open();
        pl=pla.addPlanta(nombre, nombreCientifico, familia, usos, descripcion, propiedades, contraindicaciones, imagen);
        pla.close();

        Toast.makeText(getApplicationContext(),"Registro exitoso.",Toast.LENGTH_LONG).show();

        Intent ventana= new Intent(this, plantasList.class);
        startActivity(ventana);

    }

    public void editarPlanta(View view){
        String nombre = edtNombre.getText().toString();
        String nombreCientifico = edtNombreCientifico.getText().toString();
        String familia = edtFamilia.getText().toString();
        String usos = edtUsos.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String propiedades = edtPropiedades.getText().toString();
        String contraindicaciones = edtContraindicaciones.getText().toString();
        String imagen = edtImagen.getText().toString();

        pla.open();
        if(pla.updatePlanta(id, nombre, nombreCientifico, familia, usos, descripcion, propiedades, contraindicaciones, imagen)){
            Toast.makeText(getApplicationContext(),"Actualización exitosa.",Toast.LENGTH_LONG).show();
        }
        pla.close();
        Intent ventana= new Intent(this, plantasList.class);
        startActivity(ventana);

    }
}

package mx.uv.herbolario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class verDetalles extends AppCompatActivity {

    EditText edtNombre;
    EditText edtNombreCientifico;
    EditText edtFamilia;
    EditText edtUsos;
    EditText edtDescripcion;
    EditText edtPropiedades;
    EditText edtContraindicaciones;
    EditText edtImagen;

    PlantasOperaciones pla=new PlantasOperaciones(this);

    Planta pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalles);

        edtNombre = (EditText) findViewById(R.id.editTextNombre);
        edtNombreCientifico = (EditText) findViewById(R.id.editTextNombreCientifico);
        edtFamilia = (EditText) findViewById(R.id.editTextFamilia);
        edtUsos = (EditText) findViewById(R.id.editTextUsos);
        edtDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        edtPropiedades = (EditText) findViewById(R.id.editTextPropiedades);
        edtContraindicaciones = (EditText) findViewById(R.id.editTextContraindicaciones);
        edtImagen = (EditText) findViewById(R.id.editTextImagen);

        edtNombre.setEnabled(false);
        edtNombreCientifico.setEnabled(false);
        edtFamilia.setEnabled(false);
        edtUsos.setEnabled(false);
        edtDescripcion.setEnabled(false);
        edtPropiedades.setEnabled(false);
        edtContraindicaciones.setEnabled(false);
        edtImagen.setEnabled(false);

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
        }
    }
}

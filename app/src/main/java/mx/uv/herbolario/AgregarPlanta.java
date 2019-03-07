package mx.uv.herbolario;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AgregarPlanta extends AppCompatActivity {

    //EditText a utilizar ok
    EditText edtNombre;
    EditText edtNombreCientifico;
    EditText edtFamilia;
    EditText edtUsos;
    EditText edtDescripcion;
    EditText edtPropiedades;
    EditText edtContraindicaciones;
    EditText edtImagen;

    Button botonImagen;
    final int REQUEST_CODE_GALLERY = 999;

    PlantasOperaciones pla=new PlantasOperaciones(this);

    Planta pl;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_planta);

        botonImagen = (Button) findViewById(R.id.botonCargarImagen);
        edtNombre = (EditText) findViewById(R.id.editTextNombre);
        edtNombreCientifico = (EditText) findViewById(R.id.editTextNombreCientifico);
        edtFamilia = (EditText) findViewById(R.id.editTextFamilia);
        edtUsos = (EditText) findViewById(R.id.editTextUsos);
        edtDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        edtPropiedades = (EditText) findViewById(R.id.editTextPropiedades);
        edtContraindicaciones = (EditText) findViewById(R.id.editTextContraindicaciones);
        edtImagen = (EditText) findViewById(R.id.editTextImagen);

        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AgregarPlanta.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
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

        Intent ventana= new Intent(this, usuarios.class);
        startActivity(ventana);

    }

}

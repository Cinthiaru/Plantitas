package mx.uv.herbolario;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    Button botonCargarImagen;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
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
        botonCargarImagen = (Button) findViewById(R.id.botonCargarImagen);
        imageView = (ImageView) findViewById(R.id.imageView);

        botonCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AgregarPlanta.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "Tú no tienes poder aquí", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

package mx.uv.herbolario;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlantaListaAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Planta> plantaList;

    public PlantaListaAdapter(Context context, int layout, ArrayList<Planta> plantaList) {
        this.context = context;
        this.layout = layout;
        this.plantaList = plantaList;
    }

    @Override
    public int getCount() {
        return plantaList.size();
    }

    @Override
    public Object getItem(int position) {
        return plantaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtNombre;
        TextView txtNombreCientifico;
        TextView txtFamilia;
        TextView txtDescripcion;
        TextView txtPropiedades;
        TextView txtContraindicaciones;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtNombre = (TextView) row.findViewById(R.id.txtNombre);
            holder.txtNombreCientifico = (TextView) row.findViewById(R.id.txtNombreCientifico);
            holder.txtFamilia = (TextView) row.findViewById(R.id.txtFamilia);
            holder.txtDescripcion = (TextView) row.findViewById(R.id.txtDescripcion);
            holder.txtPropiedades = (TextView) row.findViewById(R.id.txtPropiedades);
            holder.txtContraindicaciones = (TextView) row.findViewById(R.id.txtContraindicaciones);
            holder.imageView = (ImageView) row.findViewById(R.id.imgPlanta);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Planta planta = plantaList.get(position);

        holder.txtNombre.setText(planta.getNombre());
        holder.txtNombreCientifico.setText(planta.getNombreCientifico());
        holder.txtFamilia.setText(planta.getFamilia());
        holder.txtDescripcion.setText(planta.getDescripcion());
        holder.txtPropiedades.setText(planta.getPropiedades());
        holder.txtContraindicaciones.setText(planta.getContraindicaciones());


        byte[] foodImage = planta.getImagen();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}

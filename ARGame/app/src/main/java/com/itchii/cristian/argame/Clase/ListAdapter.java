package com.itchii.cristian.argame.Clase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itchii.cristian.argame.R;

public class ListAdapter extends BaseAdapter {
    String[] nombreP;
    int[] imagenP, bloqueoP;
    Context contexto;
    private static LayoutInflater inflater = null;

    public ListAdapter(Context mainAct, String[] nombre, int[] imagen, int[] bloqueo){
        contexto = mainAct;
        nombreP = nombre;
        imagenP = imagen;
        bloqueoP = bloqueo;
        inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nombreP.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView txtPersonaje;
        ImageView imgPersonaje;
        ImageView imgBloqueo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View fila;
        fila = inflater.inflate(R.layout.renglon, null);
        holder.txtPersonaje = (TextView) fila.findViewById(R.id.txtNombrePersonaje);
        holder.imgPersonaje = (ImageView) fila.findViewById(R.id.imgPersonaje);
        holder.imgBloqueo = (ImageView) fila.findViewById(R.id.imgBloqueo);

        holder.txtPersonaje.setText(nombreP[position]);
        holder.imgPersonaje.setImageResource(imagenP[position]);
        holder.imgBloqueo.setImageResource(bloqueoP[0]);
        return fila;
    }
}

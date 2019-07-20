package com.example.ud4_ejemplo3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.MiViewHolder> {

    private ArrayList<Vehiculo> lista;

    public VehiculoAdapter(ArrayList<Vehiculo> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public VehiculoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Creamos las views de los vehículos
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.elementos_lista, viewGroup, false);

        MiViewHolder miViewHolder = new MiViewHolder(view);

        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculoAdapter.MiViewHolder holder, int position) {
        // Establecemos el nombre y aparición para el vehículo de esa posición
        String nombre = lista.get(position).getNombre();

        holder.nombretextView.setText(nombre);

        String aparicion = lista.get(position).getAparicion();

        holder.apariciontextView.setText(aparicion);
    }

    @Override
    public int getItemCount() {
        if (lista == null)
            return 0;
        else
            return lista.size();
    }


    public static class MiViewHolder extends RecyclerView.ViewHolder {
        public TextView nombretextView;
        public TextView apariciontextView;

        public MiViewHolder(View view) {
            super(view);

            nombretextView = itemView.findViewById(R.id.nombreTextView);
            apariciontextView = itemView.findViewById(R.id.aparicionTextView);
        }
    }
}

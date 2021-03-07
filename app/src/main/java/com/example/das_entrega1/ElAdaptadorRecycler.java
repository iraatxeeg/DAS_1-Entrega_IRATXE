package com.example.das_entrega1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {

    private String[] losNombres;
    private int[] lasImagenes;

    public ElAdaptadorRecycler (String[] nombres, int[] imagenes) {
        losNombres = nombres;
        lasImagenes = imagenes;
    }

    @NonNull
    @Override
    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.eltexto.setText(losNombres[position]);
        holder.laimagen.setImageResource(lasImagenes[position]);
    }

    @Override
    public int getItemCount() {
        return losNombres.length;
    }
}

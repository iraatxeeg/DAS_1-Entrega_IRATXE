package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

public class ActivityListArtistas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artistas);

        RecyclerView laLista = findViewById(R.id.elreciclerview);
        String[] nombres = {"Niall Horan", "Shawn Mendes", "Selena Gómez"};
        int[] fotos = {R.drawable.niallhoran, R.drawable.shawnmendes, R.drawable.selenagomez};
        ElAdaptadorRecycler elAdaptador = new ElAdaptadorRecycler(nombres, fotos);
        laLista.setAdapter(elAdaptador);

        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL);
        laLista.setLayoutManager(layout);
    }

}
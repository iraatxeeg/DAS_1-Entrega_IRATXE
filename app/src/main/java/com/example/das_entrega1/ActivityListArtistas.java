package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ActivityListArtistas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artistas);

        SQLiteDatabase bd = miBD.getInstance(this).getReadableDatabase();
        RecyclerView laLista = findViewById(R.id.elreciclerview);


        String[] nombres = {"Niall Horan", "Shawn Mendes", "Selena Gómez",
        "Demi Lovato", "Billie Eilish"};
        int[] ids = {1,2,3,4,5};
        int[] fotos = {R.drawable.niallhoran, R.drawable.shawnmendes, R.drawable.selenagomez,
        R.drawable.demilovato, R.drawable.billieeilish};
        ElAdaptadorRecycler elAdaptador = new ElAdaptadorRecycler(nombres, fotos);
        elAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idArtista = new Intent(getBaseContext(), ActivityArtista.class);
                idArtista.putExtra("idArtista",ids[laLista.getChildAdapterPosition(v)]);
                startActivity(idArtista);
            }
        });
        laLista.setAdapter(elAdaptador);


        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        laLista.setLayoutManager(layout);
    }

}
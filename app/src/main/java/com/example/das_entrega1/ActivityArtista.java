package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityArtista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista);

        Bundle extras = getIntent().getExtras();
        String artista = "";
        if (extras != null) {
            artista = extras.getString("Artista");
        }

        TextView textView = findViewById(R.id.textView3);
        textView.setText(artista);

    }
}
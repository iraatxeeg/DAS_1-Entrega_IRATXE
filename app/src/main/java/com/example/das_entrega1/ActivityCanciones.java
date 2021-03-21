package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityCanciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canciones);
        FragmentCanciones fragment2 = (FragmentCanciones) getSupportFragmentManager().
                findFragmentById(R.id.fragment2);
        int position = getIntent().getIntExtra("position",0);
        fragment2.rellenarDatos(position);
    }
}
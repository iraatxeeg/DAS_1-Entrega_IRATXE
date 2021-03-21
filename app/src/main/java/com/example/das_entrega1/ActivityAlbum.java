package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class ActivityAlbum extends AppCompatActivity {

    String idArtista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
    }

    public void seleccionarElemento(int position) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentCanciones fragment2 = (FragmentCanciones) getSupportFragmentManager().findFragmentById(R.id.fragment2);
            fragment2.rellenarDatos(position);
        } else {
            Intent i = new Intent(this, ActivityCanciones.class);
            i.putExtra("position", position);
            startActivity(i);
        }
    }
}
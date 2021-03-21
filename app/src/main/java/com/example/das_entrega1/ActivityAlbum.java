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

        Bundle extras = getIntent().getExtras();
        idArtista = "";
        if (extras != null) {
            idArtista = extras.getString("idArtista");
        }

        SQLiteDatabase bd = miBD.getInstance(this).getWritableDatabase();

        String[] campos = new String[] {"Titulo"};
        String[] argumentos = new String[] {idArtista};
        Cursor c = bd.query("Album",campos,"ID=?",argumentos,null,null,null);

        String[] tituloAlbum = {};
        int i = 0;
        while (c.moveToNext()) {
            tituloAlbum[i] = c.getString(0);
            i++;
        }

        Bundle args = new Bundle();
        args.putStringArray("Tiulos", tituloAlbum);

        FragmentAlbumes newFragment = new FragmentAlbumes();
        newFragment.setArguments(args);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, (Fragment) newFragment);
    }

    public void seleccionarElemento(int position) {
//        int orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
//            fragment2.rellenarDatos(position);
//        } else {
//            Intent i = new Intent(this, MainActivity2.class);
//            i.putExtra("position", position);
//            Log.i("hola","antes de startActivity");
//            startActivity(i);
//        }
    }
}
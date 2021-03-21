package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInfoAlbumes extends AppCompatActivity {

    String idAlbum;
    // Actividad para mostrar información de un disco pasado por parametro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_albumes);
        setSupportActionBar(findViewById(R.id.labarraInfoAlbum));

        Bundle extras = getIntent().getExtras();
        idAlbum = "";
        if (extras != null) {
            idAlbum = extras.getString("idAlbum");
        }

        SQLiteDatabase bd = miBD.getInstance(this).getWritableDatabase();
        String[] campos = new String[] {"Titulo", "Autor", "Género", "Anio"};
        String[] argumentos = new String[] {idAlbum};
        Cursor c = bd.query("Album",campos,"ID=?",argumentos,null,null,null);

        TextView titulo = findViewById(R.id.txtTituloAlbum1);
        TextView artista = findViewById(R.id.txtAutorAlbum1);
        TextView genero = findViewById(R.id.txtGeneroAlbum1);
        TextView anio = findViewById(R.id.txtAnioAlbum1);

        if(c.moveToNext()) {
            titulo.setText(c.getString(0));
            artista.setText(c.getString(1));
            genero.setText(c.getString(2));
            anio.setText(c.getString(3));
        }

        ImageView imagen = findViewById(R.id.imageViewAlbum);
        if (idAlbum.equals("1")) imagen.setImageResource(R.drawable.heartbreakweather);
        if (idAlbum.equals("2")) imagen.setImageResource(R.drawable.flicker);
        if (idAlbum.equals("3")) imagen.setImageResource(R.drawable.rare);
        if (idAlbum.equals("4")) imagen.setImageResource(R.drawable.revelacion);
        if (idAlbum.equals("5")) imagen.setImageResource(R.drawable.wonder);
        if (idAlbum.equals("6")) imagen.setImageResource(R.drawable.shawnmendesalbum);
        if (idAlbum.equals("7")) imagen.setImageResource(R.drawable.tellmeyou);
        if (idAlbum.equals("8")) imagen.setImageResource(R.drawable.dontforget);
        if (idAlbum.equals("9")) imagen.setImageResource(R.drawable.whenweall);
        if (idAlbum.equals("10")) imagen.setImageResource(R.drawable.thereforeiam);
        if (idAlbum.equals("11")) imagen.setImageResource(R.drawable.everythingiwanted);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barrainfoalbum, menu);
        return true;
    }
}
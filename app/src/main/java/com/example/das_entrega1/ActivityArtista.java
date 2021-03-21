package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityArtista extends AppCompatActivity {

    String idArtista;
    // Actividad para mostrar la información de cada artista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista);

        // Barra con opciones: Añadir artista a favs
        setSupportActionBar(findViewById(R.id.labarra1));

        Bundle extras = getIntent().getExtras();
        idArtista = "";
        if (extras != null) {
            idArtista = extras.getString("idArtista");
        }

        SQLiteDatabase bd = miBD.getInstance(this).getWritableDatabase();

        String[] campos = new String[] {"ID", "NombreCompleto",
        "Nacimiento", "LugarNac"};
        String[] argumentos = new String[] {idArtista};
        Cursor c = bd.query("Artistas",campos,"ID=?",argumentos,null,null,null);

        String nombre = "";
        String nacimiento = "";
        String lugar = "";

        if(c.moveToNext()) {
            nombre = c.getString(1);
            nacimiento = c.getString(2);
            lugar = c.getString(3);
        }

        TextView txtNombre = findViewById(R.id.txtNombreArtista1);
        txtNombre.setText(nombre);
        TextView txtFechaNac = findViewById(R.id.txtNacimientoArtista1);
        txtFechaNac.setText(nacimiento);
        TextView txtLugarNac = findViewById(R.id.txtNacimientoArtistaLugar);
        txtLugarNac.setText(lugar);

        ImageView imagen = findViewById(R.id.imageViewArtista);
        if (idArtista.equals("1")) imagen.setImageResource(R.drawable.niallhoran);
        if (idArtista.equals("2")) imagen.setImageResource(R.drawable.shawnmendes);
        if (idArtista.equals("3")) imagen.setImageResource(R.drawable.selenagomez);
        if (idArtista.equals("4")) imagen.setImageResource(R.drawable.demilovato);
        if (idArtista.equals("5")) imagen.setImageResource(R.drawable.billieeilish);

        Button btnAlbum = findViewById(R.id.btnAlbumes);
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAlbum = new Intent(getBaseContext(), ActivityAlbum.class);
                iAlbum.putExtra("IdArtista", idArtista);
                startActivity(iAlbum);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraartistasinfo, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.añadirArtistaFav) {
//
//        }
//        return ;
//    }
}
package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityArtista extends AppCompatActivity {

    // Actividad para mostrar la información de cada artista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista);

        // Barra con opciones: Añadir artista a favs
        setSupportActionBar(findViewById(R.id.labarra1));

        Bundle extras = getIntent().getExtras();
        String idArtista = "";
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
        TextView txtEdad = findViewById(R.id.txtEdadArtista);
        Date fechaactual = new Date();
        Date dateNac = new Date();
        try {
            dateNac = new SimpleDateFormat("dd/MM/yyyy").parse(nacimiento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtEdad.setText(String.valueOf(fechaactual.getTime() - dateNac.getTime()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraartistasinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.añadirArtistaFav) {

        }
        return true;
    }
}
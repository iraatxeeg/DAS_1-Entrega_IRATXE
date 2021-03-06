package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityPantallaPrincipal extends AppCompatActivity {

    String usuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        setSupportActionBar(findViewById(R.id.labarraPrincipal));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuario = extras.getString("Usuario");
        }
        String text = "Hola " + usuario;
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
        toast.show();

        SQLiteDatabase bd = miBD.getInstance(this).getReadableDatabase();

        ////////////////////////////////////////////////////////////////////////////////////////////
        // RecyclerView Artistas
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
                idArtista.putExtra("idArtista",String.valueOf(ids[laLista.getChildAdapterPosition(v)]));
                startActivity(idArtista);
            }
        });
        laLista.setAdapter(elAdaptador);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        laLista.setLayoutManager(layout);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // RecyclerView Discos
        RecyclerView laLista1 = findViewById(R.id.elreciclerview1);
        String[] albumes = {"Heartbreak Weather", "Rare", "Wonder", "Don't Forget",
        "When We All Fall Sleep, Where Do We Go?"};
        int[] ids1 = {1,3,5,8,9};
        int[] covers = {R.drawable.heartbreakweather, R.drawable.rare, R.drawable.wonder,
                R.drawable.dontforget, R.drawable.whenweall};
        ElAdaptadorRecycler elAdaptador1 = new ElAdaptadorRecycler(albumes, covers);
        elAdaptador1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idAlbum = new Intent(getBaseContext(), ActivityInfoAlbumes.class);
                idAlbum.putExtra("idAlbum",String.valueOf(ids1[laLista1.getChildAdapterPosition(v)]));
                startActivity(idAlbum);
            }
        });
        laLista1.setAdapter(elAdaptador1);
        LinearLayoutManager layout1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        laLista1.setLayoutManager(layout1);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // VER TODOS LOS ÁLBUMES
        Button btnVerTodos = findViewById(R.id.btnVerTodos);
        btnVerTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVerTodos = new Intent(getBaseContext(),ActivityAlbum.class);
                startActivity(iVerTodos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barrapantallaprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Perfil) {
            Intent iPerfil = new Intent(this, ActivityPerfil.class);
            iPerfil.putExtra("Usuario", usuario);
            startActivity(iPerfil);
        }
        return super.onOptionsItemSelected(item);
    }
}
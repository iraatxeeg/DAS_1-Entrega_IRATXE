package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityArtista extends AppCompatActivity {

    String idArtista;
    // Actividad para mostrar la información de cada artista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista);

        // Barra con opciones: Añadir artista a favs
        setSupportActionBar(findViewById(R.id.labarrainfoartitsta));

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


        ////////////////////////////////////////////////////////////////////////////////////////////
        // NOTIFICACIONES LOCALES
        NotificationManager elManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "Canal1");
        elBuilder.setSmallIcon(R.drawable.logo).setContentTitle("¿Por qué no revisas...")
                .setVibrate(new long[] {0, 500})
                .setAutoCancel(true);
        if (idArtista.equals("1")) elBuilder.setContentText("Heartbreak Weather de Niall Horan?");
        if (idArtista.equals("2")) elBuilder.setContentText("Wonder de Shawn Mendes?");
        if (idArtista.equals("3")) elBuilder.setContentText("Revelación de Selena Gómez?");
        if (idArtista.equals("4")) elBuilder.setContentText("Don't Forget de Demi Lovato?");
        if (idArtista.equals("5")) elBuilder.setContentText("Therefore I Am de Billie Eilish?");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel elCanal = new NotificationChannel("Canal1", "CanalMain",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elCanal.setDescription("Canal Principal");
            elCanal.setVibrationPattern(new long[] {0, 500});
            elCanal.enableVibration(true);
            elManager.createNotificationChannel(elCanal);
        }

        elManager.notify(1, elBuilder.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraartistasinfo, menu);
        return true;
    }

}
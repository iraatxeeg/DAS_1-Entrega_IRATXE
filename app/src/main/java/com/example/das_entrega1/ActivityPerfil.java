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
import android.widget.EditText;
import android.widget.TextView;

public class ActivityPerfil extends AppCompatActivity {

    String idUsuario = "";
    String password = "";
    TextView usuario;
    TextView nombre;
    TextView apellidos;
    TextView cumple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setSupportActionBar(findViewById(R.id.labarraPerfil));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idUsuario = extras.getString("Usuario");
        }

        SQLiteDatabase bd = miBD.getInstance(this).getWritableDatabase();

        usuario = findViewById(R.id.txtPerfilUsuario1);
        usuario.setText(idUsuario);
        nombre = findViewById(R.id.txtPerfilNombre1);
        apellidos = findViewById(R.id.txtPerfilApellido1);
        cumple = findViewById(R.id.txtPerfilCumple1);

        String[] campos = new String[] {"Nombre", "Apellidos", "Cumpleaños", "Password"};
        String[] argumentos = new String[] {idUsuario};
        Cursor c = bd.query("Usuarios",campos,"Usuario=?",argumentos,null,null,null);

        if (c.moveToNext()) {
            nombre.setText(c.getString(0));
            apellidos.setText(c.getString(1));
            cumple.setText(c.getString(2));
            password = c.getString(3);
        }

        Button btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iEdit = new Intent(getBaseContext(), ActivityEditarPerfil.class);
                iEdit.putExtra("Usuario", idUsuario);
                iEdit.putExtra("Password", password);
                iEdit.putExtra("Nombre", nombre.getText().toString());
                iEdit.putExtra("Apellidos", apellidos.getText().toString());
                iEdit.putExtra("Cumple", cumple.getText().toString());
                startActivity(iEdit);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraperfil, menu);
        return true;
    }



}
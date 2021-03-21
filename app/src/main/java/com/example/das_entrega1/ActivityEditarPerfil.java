package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityEditarPerfil extends AppCompatActivity {

    SQLiteDatabase bd;
    TextView usuario;
    EditText password;
    EditText password2;
    EditText nombre;
    EditText apellidos;
    EditText cumple;
    String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        setSupportActionBar(findViewById(R.id.labarraEdit));

        usuario = findViewById(R.id.txtEditUsuario1);
        password = findViewById(R.id.editPerfilPassword);
        password2 = findViewById(R.id.editPerfilPassword1);
        nombre = findViewById(R.id.editPerfilNombre);
        apellidos = findViewById(R.id.editPerfilApellidos);
        cumple = findViewById(R.id.editPerfilCumple);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idUsuario = extras.getString("ID");
            usuario.setText(extras.getString("Usuario"));
            password.setText(extras.getString("Password"));
            password2.setText(extras.getString("Password"));
            nombre.setText(extras.getString("Nombre"));
            apellidos.setText(extras.getString("Apellidos"));
            cumple.setText(extras.getString("Cumple"));
        }

        cumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    // show Dialog para elegir la fecha de cumpleaños
    private void showDatePickerDialog() {
        ClaseDialogoDatePicker dialogo = new ClaseDialogoDatePicker(cumple);
        dialogo.show(getSupportFragmentManager(), "datePickerEdit");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraedit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opcionGuardar) {
            ContentValues update = new ContentValues();
            update.put("Usuario", idUsuario);
            update.put("Password", password.getText().toString());
            update.put("Nombre", nombre.getText().toString());
            update.put("Apellidos", apellidos.getText().toString());
            update.put("Cumpleaños", cumple.getText().toString());
            String[] args = new String[] {idUsuario};
            bd.update("Usuarios", update, "ID=?", args);
            Intent iPerfil = new Intent(this, ActivityPerfil.class);
            startActivity(iPerfil);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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

        bd = miBD.getInstance(this).getWritableDatabase();

        usuario = findViewById(R.id.txtEditUsuario1);
        password = findViewById(R.id.editPerfilPassword);
        password2 = findViewById(R.id.editPerfilPassword1);
        nombre = findViewById(R.id.editPerfilNombre);
        apellidos = findViewById(R.id.editPerfilApellidos);
        cumple = findViewById(R.id.editPerfilCumple);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idUsuario = extras.getString("Usuario");
            usuario.setText(idUsuario);
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
            // Si alguno de los campos no está rellenado
            if(usuario.getText().toString().equals("") || password.getText().toString().equals("")
                    || password2.getText().toString().equals("") || nombre.getText().toString().equals("")
                    || apellidos.getText().toString().equals("") || cumple.getText().toString().equals("")) {
                DialogFragment dialogoAlerta = new ClaseDialogoCamposSinRellenar();
                dialogoAlerta.show(getSupportFragmentManager(), "CamposSinRellenar");
            }else if (!password.getText().toString().equals(password2.getText().toString())) {
                DialogFragment dialogoAlerta = new ClaseDialogoPasswordError();
                dialogoAlerta.show(getSupportFragmentManager(), "PasswordError");
            } else {
                ContentValues update = new ContentValues();
                update.put("Usuario", idUsuario);
                update.put("Password", password.getText().toString());
                update.put("Nombre", nombre.getText().toString());
                update.put("Apellidos", apellidos.getText().toString());
                update.put("Cumpleaños", cumple.getText().toString());
                String[] args = new String[] {idUsuario};
                bd.update("Usuarios", update, "Usuario=?", args);
                Intent iPerfil = new Intent(this, ActivityPerfil.class);
                iPerfil.putExtra("Usuario", idUsuario);
                startActivity(iPerfil);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
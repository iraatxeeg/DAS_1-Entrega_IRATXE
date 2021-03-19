package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase bd = miBD.getInstance(this).getWritableDatabase();

        EditText usuario = findViewById(R.id.editUsuario);
        EditText password = findViewById(R.id.editPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Buscar el ususario en la base de datos
                String[] campos = new String[] {"Usuario"};
                String[] argumentos = new String[] {usuario.getText().toString(), password.getText().toString()};
                Cursor c = bd.query("Usuarios",campos,"Usuario=? AND Password=?",argumentos,null,null,null);

                if (c.moveToNext()) {
                    // Se ha encontrado -> Está registrado
                    Intent i = new Intent(getBaseContext(), ActivityPantallaPrincipal.class);
                    i.putExtra("Usuario", usuario.getText().toString());
                    startActivity(i);
                    finish();
                } else {
                    // No está registrado
                    DialogFragment dialogoAlerta = new ClaseDialogoLoginError();
                    dialogoAlerta.show(getSupportFragmentManager(), "LoginError");

                }
            }
        });

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pasar a la pantalla de registro
                Intent iRegistro = new Intent(getBaseContext(),RegistroActivity.class);
                startActivity(iRegistro);
                finish();
            }
        });
    }


}
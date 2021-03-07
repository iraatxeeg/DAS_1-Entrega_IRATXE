package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miBD GestorDB = new miBD (this, "MusicaBD", null, 1);
        SQLiteDatabase bd = GestorDB.getWritableDatabase();

        EditText usuario = findViewById(R.id.editUsuario);
        EditText password = findViewById(R.id.editPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] campos = new String[] {"Usuario"};
                String[] argumentos = new String[] {usuario.getText().toString(), password.getText().toString()};
                Cursor c = bd.query("Usuarios",campos,"Usuario=? AND Password=?",argumentos,null,null,null);

                if (c.moveToNext()) {
                    Intent i = new Intent(getBaseContext(),ActivityListArtistas.class);
                    i.putExtra("Usuario", usuario.getText().toString());
                    startActivity(i);
                    finish();
                } else {
                    DialogFragment dialogoAlerta = new ClaseDialogoLoginError();
                    dialogoAlerta.show(getSupportFragmentManager(), "LoginError");

                }
            }
        });

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegistro = new Intent(getBaseContext(),RegistroActivity.class);
                startActivity(iRegistro);
                finish();
            }
        });
    }

}
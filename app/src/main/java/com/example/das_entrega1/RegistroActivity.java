package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class RegistroActivity extends AppCompatActivity implements Serializable {

    SQLiteDatabase bd;
    EditText usuario;
    EditText password;
    EditText password2;
    EditText nombre;
    EditText apellidos;
    EditText cumple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setSupportActionBar(findViewById(R.id.barraRegistro));

        miBD GestorDB = new miBD(this, "MusicaBD", null, 1);
        bd = GestorDB.getWritableDatabase();

        usuario = findViewById(R.id.editUsuarioR);
        password = findViewById(R.id.editPasswordR);
        password2 = findViewById(R.id.editPasswordR2);
        nombre = findViewById(R.id.editNombreR);
        apellidos = findViewById(R.id.editApellidosR);
        cumple = findViewById(R.id.editCumpleR);
        cumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    private void showDatePickerDialog() {
        ClaseDialogoDatePicker dialogo = ClaseDialogoDatePicker.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                final String selectedDate = twoDigits(day)+"/"+twoDigits(month)+"/"+year;
                cumple.setText(selectedDate);
            }
        });
        dialogo.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int day) {
        return (day<10) ? ("0"+day) : String.valueOf(day);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraregistro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.opcionCancelar: {
                Intent iMain = new Intent(this, MainActivity.class);
                startActivity(iMain);
                finish();
            }
            case R.id.opcionRegistrarme: {


                if (password.getText().toString().equals(password2.getText().toString())) {
                    // Insertar en BD
                    ContentValues insert = new ContentValues();
                    insert.put("Usuario", usuario.getText().toString());
                    insert.put("Password", password.getText().toString());
                    insert.put("Nombre",nombre.getText().toString());


                    bd.insert("Usuarios", null, insert);
                } else { // Avisar de que la contraseña está mal
                    DialogFragment dialogoAlerta = new ClaseDialogoPasswordError();
                    dialogoAlerta.show(getSupportFragmentManager(), "PasswordError");


                }
            }
        }
        return true;
    }

}
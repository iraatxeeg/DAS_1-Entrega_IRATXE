package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class RegistroActivity extends AppCompatActivity implements Serializable {
// Activity para registar un nuevo usuario en la aplicación
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

        bd = miBD.getInstance(this).getWritableDatabase();

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

    // show Dialog para elegir la fecha de cumpleaños
    private void showDatePickerDialog() {
        ClaseDialogoDatePicker dialogo = new ClaseDialogoDatePicker(cumple);
        dialogo.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barraregistro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // Opción cancelar -> Volver a la pantalla de Login
        if (id == R.id.opcionCancelar) {
                Intent iMain = new Intent(this, MainActivity.class);
                startActivity(iMain);
                finish();

        }
        // Opción Registrar -> insertar en la base de datos
        else {
            // Si alguno de los campos no está rellenado
            if(usuario.getText().toString().equals("") || password.getText().toString().equals("")
                    || password2.getText().toString().equals("") || nombre.getText().toString().equals("")
            || apellidos.getText().toString().equals("") || cumple.getText().toString().equals("")) {
                DialogFragment dialogoAlerta = new ClaseDialogoCamposSinRellenar();
                dialogoAlerta.show(getSupportFragmentManager(), "CamposSinRellenar");
            }
            // Todos los campos se han rellenado y las contraseñas coinciden
            else if (password.getText().toString().equals(password2.getText().toString())) {
                // Buscar el ususario en la base de datos por si ya existe ese usuario
                String[] campos = new String[] {"Usuario"};
                String[] argumentos = new String[] {usuario.getText().toString()};
                Cursor c = bd.query("Usuarios",campos,"Usuario=?",argumentos,null,null,null);

                if (c.moveToNext()) {
                    DialogFragment dialogoAlerta = new ClaseDialogoUsuarioExiste();
                    dialogoAlerta.show(getSupportFragmentManager(), "UsuarioExiste");
                }
                else {
                    // Insertar en BD
                    ContentValues insert = new ContentValues();
                    insert.put("Usuario", usuario.getText().toString());
                    insert.put("Password", password.getText().toString());
                    insert.put("Nombre", nombre.getText().toString());
                    insert.put("Apellidos", apellidos.getText().toString());
                    insert.put("Cumpleaños", cumple.getText().toString());

                    bd.insert("Usuarios", null, insert);

                    Toast toast = Toast.makeText(this, "Registro realizado con éxito.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent iMain = new Intent(this, MainActivity.class);
                    startActivity(iMain);
                    finish();
                }

            } else {  // Si las contraseñas no coinciden se avisa al usuario
                DialogFragment dialogoAlerta = new ClaseDialogoPasswordError();
                dialogoAlerta.show(getSupportFragmentManager(), "PasswordError");
            }
        }
        return true;
    }

}
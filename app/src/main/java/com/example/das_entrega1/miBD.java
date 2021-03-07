package com.example.das_entrega1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class miBD extends SQLiteOpenHelper implements Serializable {

    public miBD(@Nullable Context context, @Nullable String name,
                @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Usuarios ('Usuario' PRIMARY KEY " +
                "NOT NULL, 'Password' PASSWORD(255), 'Nombre' VARCHAR(255), 'Apellidos' VARCHAR(255)," +
                "'Cumpleaños' DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("hola", "upgrade");
    }

}
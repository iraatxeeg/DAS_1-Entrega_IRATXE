package com.example.das_entrega1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class miBD extends SQLiteOpenHelper {

    private static miBD mInstance = null;

    private static final String DATABASE_NAME = "MusicDB";
    private static final int DATABASE_VERSION = 1;

    private Context mCtx;

    public static miBD getInstance(@Nullable Context ctx) {
        if (mInstance == null) {
            mInstance = new miBD(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private miBD(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        mCtx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // USUARIOS
        db.execSQL("CREATE TABLE Usuarios ('Usuario' PRIMARY KEY " +
                "NOT NULL, 'Password' PASSWORD(255), 'Nombre' VARCHAR(255), 'Apellidos' VARCHAR(255)," +
                "'Cumpleaños' DATE)");

        // ARTISTAS
        db.execSQL("CREATE TABLE Artistas ('ID' PRIMARY KEY " +
                "NOT NULL, 'NombreCompleto' VARCHAR(255), 'Nacimiento' DATE, 'LugarNac' " +
                "VARCHAR(255))");

        ContentValues insert = new ContentValues();
        insert.put("ID", "1");
        insert.put("NombreCompleto", "Niall Horan");
        insert.put("Nacimiento", "13/09/1993");
        insert.put("LugarNac", "Mullingar, Irlanda");
        db.insert("Artistas", null, insert);

        insert.put("ID", "2");
        insert.put("NombreCompleto", "Shawn Mendes");
        insert.put("Nacimiento", "08/08/1998");
        insert.put("LugarNac", "Pickering, Canadá");
        db.insert("Artistas", null, insert);

        insert.put("ID", "3");
        insert.put("NombreCompleto", "Selena Gómez");
        insert.put("Nacimiento", "22/07/1992");
        insert.put("LugarNac", "Grand Prairie, Texas, Estados Unidos");
        db.insert("Artistas", null, insert);

        insert.put("ID", "4");
        insert.put("NombreCompleto", "Demi Lovato");
        insert.put("Nacimiento", "20/08/1992");
        insert.put("LugarNac", "Albuquerque, Nuevo México, Estados Unidos");
        db.insert("Artistas", null, insert);

        insert.put("ID", "5");
        insert.put("NombreCompleto", "Billie Eilish");
        insert.put("Nacimiento", "18/12/2001");
        insert.put("LugarNac", "Los Ángeles, California, Estados Unidos");
        db.insert("Artistas", null, insert);


        // ÁLBUMES
        db.execSQL("CREATE TABLE Álbumes ('ID' PRIMARY KEY " +
                "NOT NULL, 'Titulo' VARCHAR(255), 'Autor' VARCHAR(255), " +
                "'Género' VARCHAR(255), 'Nº Canciones' INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
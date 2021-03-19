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
        insert.put("NombreCompleto", "Selena Gomez");
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
        db.execSQL("CREATE TABLE Albumes ('ID' PRIMARY KEY " +
                "NOT NULL, 'Titulo' VARCHAR(255), 'Autor' VARCHAR(255), " +
                "'Género' VARCHAR(255), 'NCanciones' INTEGER," +
                "'Anio' INTEGER)");

        // Niall Horan
        insert.put("ID", "1");
        insert.put("Titulo", "Heartbreak Weather");
        insert.put("Autor", "Niall Horan");
        insert.put("Género", "Pop Rock");
        insert.put("NCanciones", "14");
        insert.put("Anio", "2020");
        db.insert("Albumes", null, insert);

        insert.put("ID", "2");
        insert.put("Titulo", "Flicker");
        insert.put("Autor", "Niall Horan");
        insert.put("Género", "Pop");
        insert.put("NCanciones", "13");
        insert.put("Anio", "2017");
        db.insert("Albumes", null, insert);

        // Selena Gomez
        insert.put("ID", "3");
        insert.put("Titulo", "Rare");
        insert.put("Autor", "Selena Gomez");
        insert.put("Género", "Pop, Dance");
        insert.put("NCanciones", "13");
        insert.put("Anio", "2020");
        db.insert("Albumes", null, insert);

        insert.put("ID", "4");
        insert.put("Titulo", "Revelacion");
        insert.put("Autor", "Selena Gomez");
        insert.put("Género", "Regueton, R&B");
        insert.put("NCanciones", "7");
        insert.put("Anio", "2021");
        db.insert("Albumes", null, insert);

        // Shawn Mendes
        insert.put("ID", "5");
        insert.put("Titulo", "Wonder");
        insert.put("Autor", "Shawn Mendes");
        insert.put("Género", "Pop");
        insert.put("NCanciones", "14");
        insert.put("Anio", "2020");
        db.insert("Albumes", null, insert);

        insert.put("ID", "6");
        insert.put("Titulo", "Shawn Mendes");
        insert.put("Autor", "Shawn Mendes");
        insert.put("Género", "Pop, Rock");
        insert.put("NCanciones", "14");
        insert.put("Anio", "2017");
        db.insert("Albumes", null, insert);

        // Demi Lovato
        insert.put("ID", "7");
        insert.put("Titulo", "Tell Me You Love Me");
        insert.put("Autor", "Demi Lovato");
        insert.put("Género", "Pop, R&B");
        insert.put("NCanciones", "12");
        insert.put("Anio", "2017");
        db.insert("Albumes", null, insert);

        insert.put("ID", "8");
        insert.put("Titulo", "Don't Forget");
        insert.put("Autor", "Demi Lovato");
        insert.put("Género", "Pop Rock");
        insert.put("NCanciones", "11");
        insert.put("Anio", "2008");
        db.insert("Albumes", null, insert);

        // Billie Eilish
        insert.put("ID", "9");
        insert.put("Titulo", "When We All Fall Asleep, Where Do We Go?");
        insert.put("Autor", "Billie Eilish");
        insert.put("Género", "Pop, Electropop");
        insert.put("NCanciones", "14");
        insert.put("Anio", "2019");
        db.insert("Albumes", null, insert);

        insert.put("ID", "10");
        insert.put("Titulo", "Therefore I Am");
        insert.put("Autor", "Billie Eilish");
        insert.put("Género", "Música Alternativa");
        insert.put("NCanciones", "1");
        insert.put("Anio", "2020");
        db.insert("Albumes", null, insert);

        insert.put("ID", "11");
        insert.put("Titulo", "everything i wanted");
        insert.put("Autor", "Billie Eilish");
        insert.put("Género", "Música Alternativa");
        insert.put("NCanciones", "1");
        insert.put("Anio", "2019");
        db.insert("Albumes", null, insert);

        // Lista de artistas favoritos
        db.execSQL("CREATE TABLE ArtistasFav ('IdArtista' INTEGER," +
                " 'NombreUsuario' VARCHAR(255)), PRIMARY KEY " +
                "('IdArtista','NombreUsuario')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
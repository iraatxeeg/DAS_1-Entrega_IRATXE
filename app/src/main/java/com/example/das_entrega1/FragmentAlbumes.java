package com.example.das_entrega1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class FragmentAlbumes extends ListFragment {

    public interface listenerDelFragment {
        void seleccionarElemento(int position);
    }

    private listenerDelFragment elListener;


    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            elListener = (listenerDelFragment) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException("La clase " + context.toString()
                    + " debe implementar listenerDelFragment.");
        }
    }

    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Buscar los albumes en la base de datos
        SQLiteDatabase bd = miBD.getInstance(getActivity()).getWritableDatabase();

        String[] campos = new String[] {"Titulo"};
        String[] argumentos = new String[] {};
        Cursor c = bd.query("Album",campos,"",argumentos,null,null,null);

        String[] datos = new String[11];
        int i = 0;
        while (c.moveToNext()) {
            datos[i] = c.getString(0);
            i++;
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datos));

    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        elListener.seleccionarElemento(position);
    }
}

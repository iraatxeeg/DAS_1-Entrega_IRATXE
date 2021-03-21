package com.example.das_entrega1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class FragmentAlbumes extends ListFragment {

    public interface listenerDelFragment {
        void seleccionarElemento(int position);
    }

    private listenerDelFragment elListener;
    String[] datos = {"Iratxe", "Gorka", "Ander", "Landeta"};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = new Intent(getActivity(), ActivityAlbum.class);
        startActivityForResult(intent, 1);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,datos));

    }
}

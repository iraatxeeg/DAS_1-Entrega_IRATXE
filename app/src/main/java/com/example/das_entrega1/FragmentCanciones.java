package com.example.das_entrega1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FragmentCanciones extends Fragment {

    TextView txt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_canciones, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void rellenarDatos(int position) {
        txt = getView().findViewById(R.id.txtCanciones);
        txt.setText("");
        Log.i("hola", "rellenarDatos: fragmentCanciones");
        InputStream fich;
        if (position == 1) fich = getResources().openRawResource(R.raw.c_heartbreak);
        if (position == 2) fich = getResources().openRawResource(R.raw.c_flicker);
        if (position == 3) fich = getResources().openRawResource(R.raw.c_rare);
        if (position == 4) fich = getResources().openRawResource(R.raw.c_revelacion);
        if (position == 5) fich = getResources().openRawResource(R.raw.c_wonder);
        if (position == 6) fich = getResources().openRawResource(R.raw.c_shawnmendes);
        if (position == 7) fich = getResources().openRawResource(R.raw.c_tellmeyou);
        if (position == 8) fich = getResources().openRawResource(R.raw.c_dontforget);
        if (position == 9) fich = getResources().openRawResource(R.raw.c_whenweall);
        if (position == 10) fich = getResources().openRawResource(R.raw.c_thereforeiam);
        else fich = getResources().openRawResource(R.raw.c_everythingiwanted);


        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(fich));
            String linea;
            while (buff.readLine() != null) {
                linea = buff.readLine();
                txt.setText(txt.getText() + linea + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

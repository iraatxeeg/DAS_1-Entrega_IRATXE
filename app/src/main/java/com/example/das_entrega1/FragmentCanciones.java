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
        InputStream fich;
        if (position == 0) fich = getResources().openRawResource(R.raw.c_heartbreak);
        else if (position == 1) fich = getResources().openRawResource(R.raw.c_flicker);
        else if (position == 2) fich = getResources().openRawResource(R.raw.c_rare);
        else if (position == 3) fich = getResources().openRawResource(R.raw.c_revelacion);
        else if (position == 4) fich = getResources().openRawResource(R.raw.c_wonder);
        else if (position == 5) fich = getResources().openRawResource(R.raw.c_shawnmendes);
        else if (position == 6) fich = getResources().openRawResource(R.raw.c_tellmeyou);
        else if (position == 7) fich = getResources().openRawResource(R.raw.c_dontforget);
        else if (position == 8) fich = getResources().openRawResource(R.raw.c_whenweall);
        else if (position == 9) fich = getResources().openRawResource(R.raw.c_thereforeiam);
        else fich = getResources().openRawResource(R.raw.c_everythingiwanted);


        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(fich));
            String linea;
            while (buff.readLine() != null) {
                linea = buff.readLine();
                txt.setText(txt.getText().toString() + linea + "\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

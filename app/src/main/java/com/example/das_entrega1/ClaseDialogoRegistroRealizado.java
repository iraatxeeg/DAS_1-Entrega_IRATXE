package com.example.das_entrega1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ClaseDialogoRegistroRealizado  extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("¡Registro realizado con éxito!");
        builder.setMessage("Vuelve a la pantalla principal para iniciar sesión.");
        builder.setNeutralButton("Volver", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent iRegistro = new Intent(getContext(),RegistroActivity.class);
                startActivity(iRegistro);

            }
        });

        return builder.create();
    }
}

package com.example.das_entrega1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class ClaseDialogoDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

//    private DatePickerDialog.OnDateSetListener listener;

//    public static ClaseDialogoDatePicker newInstance(DatePickerDialog.OnDateSetListener pListener) {
//        ClaseDialogoDatePicker dialogo = new ClaseDialogoDatePicker();
//        dialogo.setListener(pListener);
//        return dialogo;
//    }

//    private void setListener(DatePickerDialog.OnDateSetListener pListener) {
//        listener = pListener;
//    }

    TextView txt;
    public ClaseDialogoDatePicker(TextView pTxt) {
        txt = pTxt;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog eldialogo = new DatePickerDialog(getActivity(),this, year,month,day);
        return eldialogo;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
//        Log.i("hola","adios");
          final String selectedDate = twoDigits(day)+"-"+twoDigits(month)+"-"+year;
          txt.setText(selectedDate);


    }

        private String twoDigits(int day) {
        return (day<10) ? ("0"+day) : String.valueOf(day);
    }


}


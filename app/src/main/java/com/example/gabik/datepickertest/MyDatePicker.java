package com.example.gabik.datepickertest;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.Calendar;

/**
 * Created by gabik on 10/25/16.
 * Custom date picker, only month and year.
 */

public class MyDatePicker extends DialogFragment {

    Button dateButton;

    public MyDatePicker() {
        super();
    }

    public void setButton(Button db) {
        dateButton = db;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Calendar cc = Calendar.getInstance();
        final int mon = cc.get(Calendar.MONTH) + 1;
        final int year = cc.get(Calendar.YEAR);
        View view = inflater.inflate(R.layout.custom_date_picker, container, false);
        setCancelable(false);
        final Dialog d = getDialog();
        d.setTitle("Please Choose Month and Year");

        final NumberPicker mp = (NumberPicker) view.findViewById(R.id.monthPicker);
        final NumberPicker yp = (NumberPicker) view.findViewById(R.id.yearPicker);
        mp.setMaxValue(12);
        mp.setMinValue(mon);
        mp.setValue(mon);
        mp.setWrapSelectorWheel(false);

        yp.setMaxValue(year+1);
        yp.setMinValue(year);
        yp.setValue(year);
        yp.setWrapSelectorWheel(false);
        yp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal == year) { mp.setMinValue(mon); }
                else {mp.setMinValue(1);}
            }
        });

        Button cancel = (Button)view.findViewById(R.id.buttoncncl);
        Button thismon = (Button)view.findViewById(R.id.buttonthismon);
        Button set = (Button)view.findViewById(R.id.buttonset);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateButton.setText(mp.getValue() + ", " + yp.getValue());
                d.dismiss();
            }
        });

        thismon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateButton.setText(mon + ", " + year);
                d.dismiss();
            }
        });

        return view;
    }

}

package com.example.gabik.datepickertest;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.NumberPicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatePicker newFragment = new MyDatePicker();
                newFragment.show(getFragmentManager(), "MyDatePicker");
            }
        });
    }

    public static class MyDatePicker extends DialogFragment {

        public interface EditNameDialogListener {
            void onFinishEditDialog(String inputText);
        }

        public MyDatePicker() {
            // Empty constructor required for DialogFragment
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Calendar cc = Calendar.getInstance();
            final int mon = cc.get(Calendar.MONTH) + 1;
            final int year = cc.get(Calendar.YEAR);
            View view = inflater.inflate(R.layout.custom_date_picker, container);
            getDialog().setTitle("Please Choose Month and Year");

            final NumberPicker mp = (NumberPicker) view.findViewById(R.id.monthPicker);
            final NumberPicker yp = (NumberPicker) view.findViewById(R.id.yearPicker);
            mp.setMaxValue(12);
            mp.setMinValue(mon);
            mp.setValue(mon);
            mp.setWrapSelectorWheel(false);
//            mp.setOnValueChangedListener(this);

            yp.setMaxValue(year+1);
            yp.setMinValue(year);
            yp.setValue(year);
            yp.setWrapSelectorWheel(false);
            yp.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (newVal == year) { mp.setMinValue(mon); }
                    else {mp.setMinValue(1); mp.setValue(1);}
                }
            });

            return view;
        }

    }


}

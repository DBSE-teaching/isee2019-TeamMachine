package com.example.celedger1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;

public class SortIncbyCat extends AppCompatActivity {
    //DECLARATION
    CheckBox check_salary, check_payment, check_others, check_icCash, check_icOT;
    public static ArrayList<String> IncCat, IncPM;
    public static Integer Sizeof, pmsize, idate = 0;
    public static String fromdte, fromdate, todte, todate, frmdte, tdte;
    EditText from, to;
    Button Applyfilter;
    DatePickerDialog incdatePicker;

    public static String TAG = SortIncbyCat.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_incby_cat);

        //DECLARATION & DEFINITION
        ImageView Back = findViewById(R.id.goBack);
        IncCat = new ArrayList<>();
        IncPM = new ArrayList<>();
        from = findViewById(R.id.fromdte);
        to = findViewById(R.id.todte);
        Applyfilter = findViewById(R.id.incAF);
        check_salary = findViewById(R.id.check_salary);
        check_payment = findViewById(R.id.check_payment);
        check_others = findViewById(R.id.check_iothers);
        check_icCash = findViewById(R.id.check_icCash);
        check_icOT = findViewById(R.id.check_icOT);

        //GO BACK
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IncAct = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(IncAct);
                IncCat.clear();
                IncPM.clear();
                Sizeof=0;
                pmsize=0;
                idate=0;
                finish();
            }
        });

        //CHECKBOX SALARY
        check_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_salary.isChecked()){
                    IncCat.add("Salary");
                }
                else
                    IncCat.remove("Salary");
            }
        });
        //CHECKBOX PAYMENT
        check_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_payment.isChecked()){
                    IncCat.add("Payment");
                }
                else
                    IncCat.remove("Payment");
            }
        });
        //CHECKBOX OTHERS
        check_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_others.isChecked()){
                    IncCat.add("Others");
                }
                else
                    IncCat.remove("Others");
            }
        });

        //CHECKBOX CASH
        check_icCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_icCash.isChecked()){
                    IncPM.add("Cash");
                }
                else
                    IncPM.remove("Cash");
            }
        });
        //CHECKBOX ONLINE TRANSFER
        check_icOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_icOT.isChecked()){
                    IncPM.add("Online Transfer");
                }
                else
                    IncPM.remove("Online Transfer");
            }
        });

        //LISTENER FOR FROM DATE
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar exp_cal = Calendar.getInstance();
                final int xp_year = exp_cal.get(Calendar.YEAR);
                final int xp_month = exp_cal.get(Calendar.MONTH);
                final int xp_day = exp_cal.get(Calendar.DAY_OF_MONTH);

                incdatePicker = new DatePickerDialog(SortIncbyCat.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        from.setText(date);
                        fromdte = date;
                        Log.d(TAG, "from date: " +fromdte);
                    }
                },xp_year,xp_month,xp_day);
                incdatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                incdatePicker.show();
            }
        });

        //LISTENER FOR TO DATE
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar exp_cal = Calendar.getInstance();
                final int xp_year = exp_cal.get(Calendar.YEAR);
                final int xp_month = exp_cal.get(Calendar.MONTH);
                final int xp_day = exp_cal.get(Calendar.DAY_OF_MONTH);

                incdatePicker = new DatePickerDialog(SortIncbyCat.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        to.setText(date);
                        todte = date;
                        Log.d(TAG, "to date: "+ todte);
                    }
                },xp_year,xp_month,xp_day);
                incdatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                incdatePicker.show();
            }
        });

        //APPLY FILTER
        Applyfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IncAct = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(IncAct);
                frmdte = from.getText().toString().trim();
                tdte = to.getText().toString().trim();
                if(!frmdte.isEmpty() && !tdte.isEmpty())
                {
                    idate = 1;
                    fromdate = fromdte;
                    todate = todte;
                }
                else idate = 0;
                Sizeof = IncCat.size();
                pmsize = IncPM.size();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent IncAct = new Intent(getApplicationContext(), IncomeActivity.class);
        startActivity(IncAct);
        IncCat.clear();
        IncPM.clear();
        Sizeof=0;
        pmsize=0;
        idate=0;
        super.onBackPressed();
    }
}

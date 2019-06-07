package com.example.celedger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

public class SortIncbyCat extends AppCompatActivity {
    //DECLARATION
    CheckBox check_salary, check_payment, check_others, check_icCash, check_icOT;
    public static ArrayList<String> IncCat, IncPM;
    public static Integer Sizeof, pmsize;
    Button Applyfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_incby_cat);

        //DECLARATION & DEFINITION
        ImageView Back = findViewById(R.id.goBack);
        IncCat = new ArrayList<>();
        IncPM = new ArrayList<>();
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

        //APPLY FILTER
        Applyfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IncAct = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(IncAct);
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
        super.onBackPressed();
    }
}

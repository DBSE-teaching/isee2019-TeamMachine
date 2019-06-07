package com.example.celedger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;

public class SortExpbyCat extends AppCompatActivity {
    //DECLARATION
    CheckBox check_food, check_travel, check_fees, check_bills, check_rent, check_shopping, check_others;
    public static ArrayList<String> ExpCat;
    public static Integer Sizeof;
    Button Applyfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_expby_cat);

        //DECLARATION & DEFINITION
        ImageView Back = findViewById(R.id.goBack);
        ExpCat = new ArrayList<>();
        Applyfilter = findViewById(R.id.expAF);
        check_bills = findViewById(R.id.check_bills);
        check_fees = findViewById(R.id.check_fees);
        check_food = findViewById(R.id.check_food);
        check_others = findViewById(R.id.check_others);
        check_rent = findViewById(R.id.check_rent);
        check_shopping = findViewById(R.id.check_shopping);
        check_travel = findViewById(R.id.check_travel);

        //GO BACK
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ExpAct = new Intent(getApplicationContext(), ExpendActivity.class);
                startActivity(ExpAct);
                ExpCat.clear();
                Sizeof=0;
                finish();
            }
        });

        //CHECKBOX FOOD
        check_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_food.isChecked()){
                    ExpCat.add("Food");
                }
                else
                    ExpCat.remove("Food");
            }
        });
        //CHECKBOX FEES
        check_fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_fees.isChecked()){
                    ExpCat.add("Fees");
                }
                else
                    ExpCat.remove("Fees");
            }
        });
        //CHECKBOX BILLS
        check_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_bills.isChecked()){
                    ExpCat.add("Bills");
                }
                else
                    ExpCat.remove("Bills");
            }
        });
        //CHECKBOX TRAVEL
        check_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_travel.isChecked()){
                    ExpCat.add("Travel");
                }
                else
                    ExpCat.remove("Travel");
            }
        });
        //CHECKBOX RENT
        check_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_rent.isChecked()){
                    ExpCat.add("Rent");
                }
                else
                    ExpCat.remove("Rent");
            }
        });
        //CHECKBOX SHOPPING
        check_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_shopping.isChecked()){
                    ExpCat.add("Shopping");
                }
                else
                    ExpCat.remove("Shopping");
            }
        });
        //CHECKBOX OTHERS
        check_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_others.isChecked()){
                    ExpCat.add("Others");
                }
                else
                    ExpCat.remove("Others");
            }
        });

        //APPLY FILTER
        Applyfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ExpAct = new Intent(getApplicationContext(), ExpendActivity.class);
                startActivity(ExpAct);
                Sizeof = ExpCat.size();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent ExpAct = new Intent(getApplicationContext(), ExpendActivity.class);
        startActivity(ExpAct);
        ExpCat.clear();
        Sizeof=0;
        super.onBackPressed();
    }
}

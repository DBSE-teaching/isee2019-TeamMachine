package com.example.celedger1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

public class AddIncome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARATIONS
    DatabaseHelper income_db;
    SQLiteDatabase incdb;
    EditText addincamt, addincdte, incdesc;
    MaterialBetterSpinner addinccat, addincPM;
    Button addincomebtn;
    DatePickerDialog incdatePicker;
    DrawerLayout navigation;

    String[] IncomeCat = {"Salary", "Payment", "Others"}, PayMethod = {"Cash", "Online Transfer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        Button addxpnse = findViewById(R.id.xpnseimg);
        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        income_db = new DatabaseHelper(this);
        incdb = income_db.getWritableDatabase();
        addincamt = findViewById(R.id.incamt);
        addinccat = findViewById(R.id.incsrc);
        addincPM = findViewById(R.id.incpm);
        addincdte = findViewById(R.id.incdte);
        incdesc = findViewById(R.id.incdesc);
        addincomebtn = findViewById(R.id.addincomebtn);

        //Expense Add Image
        addxpnse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentaddxpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startIntentaddxpense);
                finish();
            }
        });

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        //INCOME CATEGORY ADAPTOR
        ArrayAdapter<String> IncomeCategory = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, IncomeCat);
        addinccat.setAdapter(IncomeCategory);

        //PAYMENT OPTION ADAPTOR
        ArrayAdapter<String> paymentCat = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PayMethod);
        addincPM.setAdapter(paymentCat);

        //LISTENER FOR DATE
        addincdte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar inc_cal = Calendar.getInstance();
                final int ic_year = inc_cal.get(Calendar.YEAR);
                final int ic_month = inc_cal.get(Calendar.MONTH);
                final int ic_day = inc_cal.get(Calendar.DAY_OF_MONTH);

                incdatePicker = new DatePickerDialog(AddIncome.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        addincdte.setText(date);
                    }
                },ic_year,ic_month,ic_day);
                incdatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                incdatePicker.show();
            }
        });

        //TEXT CHANGE LISTENER
        addincamt.addTextChangedListener(addincometextwatcher);
        addinccat.addTextChangedListener(addincometextwatcher);
        addincPM.addTextChangedListener(addincometextwatcher);
        addincdte.addTextChangedListener(addincometextwatcher);

        AddInc();
    }

    @Override
    public void onBackPressed() {
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else {
            Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(MainIntent);
            super.onBackPressed();
        }
    }

    private TextWatcher addincometextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String incmeamt = addincamt.getText().toString().trim();
            String incmesrc = addinccat.getText().toString().trim();
            String incmepm = addincPM.getText().toString().trim();
            String incmedte = addincdte.getText().toString().trim();

            addincomebtn.setEnabled(!incmeamt.isEmpty() && !incmesrc.isEmpty() && !incmepm.isEmpty() && !incmedte.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void AddInc(){
        addincomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = income_db.insertincomedata(addinccat.getText().toString(), addincamt.getText().toString(), addincdte.getText().toString(), addincPM.getText().toString(), incdesc.getText().toString());
                if(isInserted == true)
                    Toast.makeText(AddIncome.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddIncome.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                Intent startIntentIncme = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(startIntentIncme);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.expense_nav:
                Intent startexpense = new Intent(getApplicationContext(),ExpendActivity.class);
                startActivity(startexpense);
                finish();
                break;
            case R.id.income_nav:
                Intent startincome = new Intent(getApplicationContext(),IncomeActivity.class);
                startActivity(startincome);
                finish();
                break;
            case R.id.home_nav:
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
                finish();
                break;
            case R.id.addexpense_nav:
                Intent startaddexpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startaddexpense);
                finish();
                break;
            case R.id.settings_nav:
                Intent startsettings = new Intent(getApplicationContext(),Settings.class);
                startActivity(startsettings);
                finish();
                break;
            case R.id.addincome_nav:
                navigation.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}

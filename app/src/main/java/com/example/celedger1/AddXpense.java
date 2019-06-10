package com.example.celedger1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

public class AddXpense extends AppCompatActivity {

    //DECLARATIONS
    DatabaseHelper xpense_db;
    SQLiteDatabase expdb;
    EditText addexpamt, addexpdte, expdesc;
    MaterialBetterSpinner addexpcat, addexpPM;
    Button addexpensebtn;
    DatePickerDialog expdatePicker;

    String[] XpenseCat = {"Food", "Travel", "Fees", "Bills", "Shopping", "Rent", "Others"}, PayMethodxp = {"Cash", "Online Transfer", "Card"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xpense);

        //DECLARATION AND DEFINITION
        Button addincme = findViewById(R.id.incmeimg);
        ImageView Home = findViewById(R.id.goHome);
        xpense_db = new DatabaseHelper(this);
        expdb = xpense_db.getWritableDatabase();
        addexpamt = findViewById(R.id.expamt);
        addexpcat = findViewById(R.id.expsrc);
        addexpPM = findViewById(R.id.exppm);
        addexpdte = findViewById(R.id.expdte);
        expdesc = findViewById(R.id.expdesc);
        addexpensebtn = findViewById(R.id.addexpensebtn);

        //Income Add Image
        addincme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentaddincome = new Intent(getApplicationContext(),AddIncome.class);
                startActivity(startIntentaddincome);
                finish();
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainIntent);
                finish();
            }
        });

        //EXPENSE CATEGORY ADAPTOR
        ArrayAdapter<String> XpenseCategory = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, XpenseCat);
        addexpcat.setAdapter(XpenseCategory);

        //PAYMENT OPTION ADAPTOR
        ArrayAdapter<String> paymentCatxp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PayMethodxp);
        addexpPM.setAdapter(paymentCatxp);

        //LISTENER FOR DATE
        addexpdte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar exp_cal = Calendar.getInstance();
                final int xp_year = exp_cal.get(Calendar.YEAR);
                final int xp_month = exp_cal.get(Calendar.MONTH);
                final int xp_day = exp_cal.get(Calendar.DAY_OF_MONTH);

                expdatePicker = new DatePickerDialog(AddXpense.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth+"-"+month+"-"+year;
                        addexpdte.setText(date);
                    }
                },xp_year,xp_month,xp_day);
                expdatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                expdatePicker.show();
            }
        });

        //TEXT CHANGE LISTENER
        addexpamt.addTextChangedListener(addxpensetextwatcher);
        addexpcat.addTextChangedListener(addxpensetextwatcher);
        addexpPM.addTextChangedListener(addxpensetextwatcher);
        addexpdte.addTextChangedListener(addxpensetextwatcher);

        AddExp();
    }

    @Override
    public void onBackPressed() {
        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainIntent);
        super.onBackPressed();
    }

    private TextWatcher addxpensetextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String xpnseamt = addexpamt.getText().toString().trim();
            String xpnsecat = addexpcat.getText().toString().trim();
            String xpnsepm = addexpPM.getText().toString().trim();
            String xpnsedte = addexpdte.getText().toString().trim();

            addexpensebtn.setEnabled(!xpnseamt.isEmpty() && !xpnsecat.isEmpty() && !xpnsepm.isEmpty() && !xpnsedte.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void AddExp(){
        addexpensebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = xpense_db.insertxpnsedata(addexpcat.getText().toString(), addexpamt.getText().toString(), addexpdte.getText().toString(), addexpPM.getText().toString(), expdesc.getText().toString());
                if(isInserted == true)
                    Toast.makeText(AddXpense.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddXpense.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                Intent startIntentxpnd = new Intent(getApplicationContext(),ExpendActivity.class);
                startActivity(startIntentxpnd);
                finish();
            }
        });
    }
}

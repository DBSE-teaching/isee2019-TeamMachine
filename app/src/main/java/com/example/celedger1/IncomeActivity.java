package com.example.celedger1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//CLASS FOR INCOME ACTIVITY
public class IncomeActivity extends AppCompatActivity {

    //DECLARATIONS
    DatabaseHelper income_db;
    SQLiteDatabase incdb;
    IncmeAdaptor icAdaptor;
    float icTotal;

    //CREATE LAYOUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        //DECLARATION & DEFINITION
        RecyclerView IncmelistRCV = findViewById(R.id.IncmelistRCV);
        TextView Totalincome = findViewById(R.id.Totalincome);
        ImageView Home = findViewById(R.id.goHome);
        ImageView IcCat = findViewById(R.id.incmefilter);
        income_db = new DatabaseHelper(this);
        incdb = income_db.getWritableDatabase();

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainIntent);
                finish();
            }
        });

        //SHOWS SCROLLABLE INCOME LIST
        IncmelistRCV.setLayoutManager(new LinearLayoutManager(this));
        icAdaptor = new IncmeAdaptor(this, getAllIncome());
        IncmelistRCV.setAdapter(icAdaptor);

        //FILTER BY CATEGORIES
        IcCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenicFilter = new Intent(getApplicationContext(),SortIncbyCat.class);
                startActivity(OpenicFilter);
            }
        });

        //SHOW TOTAL INCOME
        Cursor dcursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as Total FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE, null);
        if (dcursor.moveToFirst()) {
            icTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
            }
        dcursor.close();
        Totalincome.setText(String.valueOf(icTotal));
    }

    @Override
    public void onBackPressed() {
        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainIntent);
        super.onBackPressed();
    }

    private Cursor getAllIncome(){
        Cursor cursor = incdb.query(CeledgerContract.IncomeEntry.INCOME_TABLE,
                null,
                null,
                null,
                null,
                null,
                CeledgerContract.IncomeEntry.COL_6 +" DESC");
        return cursor;
        }
}

package com.example.celedger1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//CLASS FOR INCOME ACTIVITY
public class IncomeActivity extends AppCompatActivity {

    //DECLARATIONS
    DatabaseHelper income_db;
    SQLiteDatabase incdb;
    IncmeAdaptor icAdaptor;
    float icTotal;
    int Sosoi;
    String rq1,rq2, rq3, rq;
    ArrayList<String> SOI;
    private static Integer state = 0;
    public static String TAG = IncomeActivity.class.getSimpleName();

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
                if(SOI != null) {
                    SOI.clear();
                }
                Sosoi = 0;
                SortIncbyCat.Sizeof = 0;
                Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainIntent);
                finish();
            }
        });

        //FIND NUMBER OF FILTERS
        SOI = SortIncbyCat.IncCat;
        Log.d(TAG, "SOI: " + SOI);
        if(state!=0) {
            Sosoi = SortIncbyCat.Sizeof;
            Log.d(TAG, "Size of SOI: " + Sosoi);
        }

        //CREATE A QUERY TO FILTER DATABASE
        StringBuilder sb = new StringBuilder();
        if(Sosoi!=0) {
            if (Sosoi > 1) {
                rq1 = CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Sosoi; i++) {
                    rq2 = " OR " + CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rq = rq1 + rq3;
            }
            else if(Sosoi == 1){
                rq = CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(0) + "')";
            }
        }
        Log.d(TAG, "query: " + rq);

        //SHOWS SCROLLABLE INCOME LIST
        if(Sosoi == 0) {
        IncmelistRCV.setLayoutManager(new LinearLayoutManager(this));
        icAdaptor = new IncmeAdaptor(this, getAllIncome());
        IncmelistRCV.setAdapter(icAdaptor);
        }
        else {
            IncmelistRCV.setLayoutManager(new LinearLayoutManager(this));
            icAdaptor = new IncmeAdaptor(this, getFilteredIncme());
            IncmelistRCV.setAdapter(icAdaptor);
        }

        //FILTER BY CATEGORIES
        IcCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenicFilter = new Intent(getApplicationContext(),SortIncbyCat.class);
                startActivity(OpenicFilter);
                state++;
                finish();
            }
        });

        //SHOW TOTAL INCOME
        if(Sosoi == 0) {
        Cursor dcursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as Total FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE, null);
        if (dcursor.moveToFirst()) {
            icTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
            }
        dcursor.close();
        Totalincome.setText(String.valueOf(icTotal));
        }
        else {
            Cursor dcursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as Total FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + rq, null);
            if (dcursor.moveToFirst()) {
                icTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
            }
            dcursor.close();
            Totalincome.setText(String.valueOf(icTotal));
        }
    }

    @Override
    public void onBackPressed() {

        if(SOI != null){
            SOI.clear();
        }
        Sosoi = 0;
        SortExpbyCat.Sizeof = 0;
        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainIntent);
        finish();
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

    private Cursor getFilteredIncme(){
        Cursor cursor = incdb.rawQuery("SELECT * FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + rq, null);
        return cursor;
    }
}

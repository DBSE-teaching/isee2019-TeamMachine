package com.example.celedger1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//CLASS FOR INCOME ACTIVITY
public class IncomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARATIONS
    DatabaseHelper income_db;
    SQLiteDatabase incdb;
    IncmeAdaptor icAdaptor;
    float icTotal;
    int Sosoi, Soipm;
    String rq1,rq2, rq3, rq4, rq5, rq6, rq, rqsoi, rqipm;
    ArrayList<String> SOI, iPM;
    private static Integer state = 0;
    DrawerLayout navigation;
    public static String TAG = IncomeActivity.class.getSimpleName();

    //CREATE LAYOUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        //DECLARATION & DEFINITION
        RecyclerView IncmelistRCV = findViewById(R.id.IncmelistRCV);
        TextView Totalincome = findViewById(R.id.Totalincome);
        ImageView IcCat = findViewById(R.id.incmefilter);
        ImageView incChart = findViewById(R.id.incchart);
        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        income_db = new DatabaseHelper(this);
        incdb = income_db.getWritableDatabase();

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        //CHART VIEW
        incChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openchart = new Intent(getApplicationContext(), IncomeChart.class);
                startActivity(openchart);
                finish();
            }
        });

        //FIND NUMBER OF FILTERS
        SOI = SortIncbyCat.IncCat;
        iPM = SortIncbyCat.IncPM;
        Log.d(TAG, "SOI: " + SOI);
        Log.d(TAG, "Inc PayMethod: " + iPM);
        if(state!=0) {
            Sosoi = SortIncbyCat.Sizeof;
            Soipm = SortIncbyCat.pmsize;
            Log.d(TAG, "Size of SOI: " + Sosoi);
            Log.d(TAG, "Size of Inc PayMethod: " + Soipm);
        }

        //CREATE A QUERY TO FILTER DATABASE
        StringBuilder sb = new StringBuilder();
        //Filter by source of Income
        if(Sosoi !=0 && Soipm == 0) {
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
        //Filter by Payment Method
        if(Soipm !=0 && Sosoi == 0) {
            if (Soipm > 1) {
                rq1 = CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Soipm; i++) {
                    rq2 = " OR " + CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rq = rq1 + rq3;
            }
            else if(Soipm == 1){
                rq = CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(0) + "')";
            }
        }
        //Filter by both source of Income & payment method
        if(Sosoi !=0 && Soipm !=0){
            //make query for source of Income
            if (Sosoi > 1) {
                rq1 = CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Sosoi; i++) {
                    rq2 = " OR " + CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rqsoi = rq1 + rq3;
            }
            else if(Sosoi == 1){
                rqsoi = CeledgerContract.IncomeEntry.COL_4 + " = ('" + SOI.get(0) + "')";
            }
            //make query for payment method
            if (Soipm > 1) {
                rq4 = CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(0) + "')";
                Log.d(TAG, "rq4: " + rq4 + "\n");
                for (int i = 1; i < Soipm; i++) {
                    rq5 = " OR " + CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(i) + "')";
                    rq6 = sb.append(rq5).toString();
                    Log.d(TAG, "rq5: " + rq5 + "\n");
                    Log.d(TAG, "rq6: " + rq6 + "\n");
                }
                rqipm = rq4 + rq6;
            }
            else if(Soipm == 1){
                rqipm = CeledgerContract.IncomeEntry.COL_3 + " = ('" + iPM.get(0) + "')";
            }
            rq = "(" + rqsoi + ") AND " + "(" + rqipm + ")";
        }

        Log.d(TAG, "query: " + rq);

        //SHOWS SCROLLABLE INCOME LIST
        if(Sosoi == 0 && Soipm == 0) {
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
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else {
            if (SOI != null) {
                SOI.clear();
            }
            Sosoi = 0;
            SortExpbyCat.Sizeof = 0;
            Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(MainIntent);
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.expense_nav:
                if(SOI != null) {
                    SOI.clear();
                }
                if(iPM != null){
                    iPM.clear();
                }
                Sosoi = 0;
                Soipm = 0;
                SortIncbyCat.Sizeof = 0;
                SortIncbyCat.pmsize = 0;
                Intent startexpense = new Intent(getApplicationContext(),ExpendActivity.class);
                startActivity(startexpense);
                finish();
                break;
            case R.id.home_nav:
                if(SOI != null) {
                    SOI.clear();
                }
                if(iPM != null){
                    iPM.clear();
                }
                Sosoi = 0;
                Soipm = 0;
                SortIncbyCat.Sizeof = 0;
                SortIncbyCat.pmsize = 0;
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
                finish();
                break;
            case R.id.addexpense_nav:
                if(SOI != null) {
                    SOI.clear();
                }
                if(iPM != null){
                    iPM.clear();
                }
                Sosoi = 0;
                Soipm = 0;
                SortIncbyCat.Sizeof = 0;
                SortIncbyCat.pmsize = 0;
                Intent startaddexpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startaddexpense);
                finish();
                break;
            case R.id.addincome_nav:
                if(SOI != null) {
                    SOI.clear();
                }
                if(iPM != null){
                    iPM.clear();
                }
                Sosoi = 0;
                Soipm = 0;
                SortIncbyCat.Sizeof = 0;
                SortIncbyCat.pmsize = 0;
                Intent startaddincome = new Intent(getApplicationContext(),AddIncome.class);
                startActivity(startaddincome);
                finish();
                break;
            case R.id.settings_nav:
                break;
            case R.id.about_nav:
                break;
            case R.id.income_nav:
                navigation.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
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
        Cursor cursor = incdb.rawQuery("SELECT * FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + rq + " ORDER BY " + CeledgerContract.IncomeEntry.COL_6 + " DESC", null);
        return cursor;
    }
}

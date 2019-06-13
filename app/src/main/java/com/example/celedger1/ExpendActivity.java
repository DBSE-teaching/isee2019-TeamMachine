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

//CLASS FOR EXPEND ACTIVITY
public class ExpendActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARATIONS
    DatabaseHelper xpense_db;
    SQLiteDatabase expdb;
    XpnseAdaptor xpAdaptor;
    float xpTotal;
    int Sopoe, Soepm;
    String rq1,rq2, rq3, rq4, rq5, rq6, rq, rqpoe, rqepm;
    ArrayList<String> POE, ePM;
    private static Integer state = 0;
    DrawerLayout navigation;
    public static String TAG = ExpendActivity.class.getSimpleName();

    //CREATE LAYOUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expend);

        //DECLARATION & DEFINITION
        RecyclerView ExpenselistRCV = findViewById(R.id.ExpenselistRCV);
        TextView Totalxpense = findViewById(R.id.Totalxpense);
        ImageView XpCat = findViewById(R.id.xpnsefilter);
        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        xpense_db = new DatabaseHelper(this);
        expdb = xpense_db.getWritableDatabase();

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        //FIND NUMBER OF FILTERS
        POE = SortExpbyCat.ExpCat;
        ePM = SortExpbyCat.ExpPM;
        Log.d(TAG, "POE: " + POE);
        Log.d(TAG, "Exp PayMethod: " + ePM);
        if(state!=0) {
            Sopoe = SortExpbyCat.Sizeof;
            Soepm = SortExpbyCat.pmsize;
            Log.d(TAG, "Size of POE: " + Sopoe);
            Log.d(TAG, "Size of Exp PayMethod: " + Soepm);
        }

        //CREATE A QUERY TO FILTER DATABASE
        StringBuilder sb = new StringBuilder();
        //Filter by Purpose of Expense
        if(Sopoe!=0 && Soepm == 0) {
            if (Sopoe > 1) {
                rq1 = CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Sopoe; i++) {
                    rq2 = " OR " + CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rq = rq1 + rq3;
            }
            else if(Sopoe == 1){
                rq = CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(0) + "')";
            }
        }
        //Filter by Payment Method
        if(Soepm!=0 && Sopoe == 0) {
            if (Soepm > 1) {
                rq1 = CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Soepm; i++) {
                    rq2 = " OR " + CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rq = rq1 + rq3;
            }
            else if(Soepm == 1){
                rq = CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(0) + "')";
            }
        }
        //Filter by both Purpose of Expense & Payment Method
        if(Sopoe!=0 && Soepm!=0) {
            //make query for purpose of expense
            if (Sopoe > 1) {
                rq1 = CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(0) + "')";
                Log.d(TAG, "rq1: " + rq1 + "\n");
                for (int i = 1; i < Sopoe; i++) {
                    rq2 = " OR " + CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(i) + "')";
                    rq3 = sb.append(rq2).toString();
                    Log.d(TAG, "rq2: " + rq2 + "\n");
                    Log.d(TAG, "rq3: " + rq3 + "\n");
                }
                rqpoe = rq1 + rq3;
            }
            else if(Sopoe == 1){
                rqpoe = CeledgerContract.XpenseEntry.CATEGORY + " = ('" + POE.get(0) + "')";
            }
            //make query for payment method
            if (Soepm > 1) {
                rq4 = CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(0) + "')";
                Log.d(TAG, "rq4: " + rq4 + "\n");
                for (int i = 1; i < Soepm; i++) {
                    rq5 = " OR " + CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(i) + "')";
                    rq6 = sb.append(rq5).toString();
                    Log.d(TAG, "rq5: " + rq5 + "\n");
                    Log.d(TAG, "rq6: " + rq6 + "\n");
                }
                rqepm = rq4 + rq6;
            }
            else if(Soepm == 1){
                rqepm = CeledgerContract.XpenseEntry.PAYMENTMETHOD + " = ('" + ePM.get(0) + "')";
            }
            rq = "(" + rqpoe + ") AND " + "(" + rqepm + ")";
        }

        Log.d(TAG, "query: " + rq);

        //SHOWS SCROLLABLE EXPENSE LIST
        if(Sopoe == 0 && Soepm == 0) {
            ExpenselistRCV.setLayoutManager(new LinearLayoutManager(this));
            xpAdaptor = new XpnseAdaptor(this, getAllXpense());
            ExpenselistRCV.setAdapter(xpAdaptor);
        }
        else {
            ExpenselistRCV.setLayoutManager(new LinearLayoutManager(this));
            xpAdaptor = new XpnseAdaptor(this, getFilteredXpensepm());
            ExpenselistRCV.setAdapter(xpAdaptor);
        }

        //GOTO FILTER BY CATEGORIES
        XpCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenxpFilter = new Intent(getApplicationContext(), SortExpbyCat.class);
                startActivity(OpenxpFilter);
                state++;
                finish();
            }
        });

        //SHOW TOTAL EXPENSE
        if(Sopoe == 0 && Soepm == 0) {
            Cursor dcursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as Total FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE, null);
            if (dcursor.moveToFirst()) {
                xpTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
            }
            dcursor.close();
            Totalxpense.setText(String.valueOf(xpTotal));
        }
        else {
            Cursor dcursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") AS Total FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + rq, null);
            if (dcursor.moveToFirst()) {
                xpTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
            }
            dcursor.close();
            Totalxpense.setText(String.valueOf(xpTotal));
        }
    }

    @Override
    public void onBackPressed() {
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else {
            if (POE != null) {
                POE.clear();
            }
            Sopoe = 0;
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
            case R.id.home_nav:
                if(POE != null) {
                    POE.clear();
                }
                if(ePM != null){
                    ePM.clear();
                }
                Sopoe = 0;
                Soepm = 0;
                SortExpbyCat.Sizeof = 0;
                SortExpbyCat.pmsize = 0;
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
                finish();
                break;
            case R.id.income_nav:
                if(POE != null) {
                    POE.clear();
                }
                if(ePM != null){
                    ePM.clear();
                }
                Sopoe = 0;
                Soepm = 0;
                SortExpbyCat.Sizeof = 0;
                SortExpbyCat.pmsize = 0;
                Intent startincome = new Intent(getApplicationContext(),IncomeActivity.class);
                startActivity(startincome);
                finish();
                break;
            case R.id.addexpense_nav:
                if(POE != null) {
                    POE.clear();
                }
                if(ePM != null){
                    ePM.clear();
                }
                Sopoe = 0;
                Soepm = 0;
                SortExpbyCat.Sizeof = 0;
                SortExpbyCat.pmsize = 0;
                Intent startaddexpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startaddexpense);
                finish();
                break;
            case R.id.addincome_nav:
                if(POE != null) {
                    POE.clear();
                }
                if(ePM != null){
                    ePM.clear();
                }
                Sopoe = 0;
                Soepm = 0;
                SortExpbyCat.Sizeof = 0;
                SortExpbyCat.pmsize = 0;
                Intent startaddincome = new Intent(getApplicationContext(),AddIncome.class);
                startActivity(startaddincome);
                finish();
                break;
            case R.id.settings_nav:
                break;
            case R.id.about_nav:
                break;
            case R.id.expense_nav:
                navigation.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private Cursor getAllXpense(){
        Cursor cursor = expdb.query(CeledgerContract.XpenseEntry.XPENSE_TABLE,
                null,
                null,
                null,
                null,
                null,
                CeledgerContract.XpenseEntry.TIMESTAMP +" DESC");
        return cursor;
    }

    private Cursor getFilteredXpensepm(){
        Cursor cursor = expdb.rawQuery("SELECT * FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + rq + " ORDER BY " + CeledgerContract.XpenseEntry.TIMESTAMP + " DESC ", null);
        return cursor;
    }

}

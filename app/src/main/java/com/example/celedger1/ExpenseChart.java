package com.example.celedger1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ExpenseChart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String[] XpenseCat = {"Food", "Travel", "Fees", "Bills", "Shopping", "Rent", "Others"};
    Float[] CatTotal;
    ArrayList<Float> catTot;
    PieChart exppie;
    SQLiteDatabase expdb;
    DatabaseHelper xpense_db;
    DrawerLayout navigation;
    Float xTotal, foodTotal, feesTotal, billsTotal, travelTotal, shopTotal, rentTotal, othersxTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_chart);

        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        ImageView explist = findViewById(R.id.explist);
        exppie = findViewById(R.id.exppiechart);
        xpense_db = new DatabaseHelper(this);
        expdb = xpense_db.getWritableDatabase();

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        //LIST VIEW
        explist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openlist = new Intent(getApplicationContext(), ExpendActivity.class);
                startActivity(openlist);
                finish();
            }
        });

        //Expense Total
        Cursor ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as Total FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE, null);
        if (ecursor.moveToFirst()) {
            xTotal = ecursor.getFloat(ecursor.getColumnIndex("Total"));// get final total
        }
        ecursor.close();
        //Food Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as foodTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Food')" , null);
        if (ecursor.moveToFirst()) {
            foodTotal = ecursor.getFloat(ecursor.getColumnIndex("foodTotal"));// get final total
        }
        ecursor.close();

        //Fees Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as feesTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Fees')", null);
        if (ecursor.moveToFirst()) {
            feesTotal = ecursor.getFloat(ecursor.getColumnIndex("feesTotal"));// get final total
        }
        ecursor.close();
        //Bills Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as billsTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Bills')" , null);
        if (ecursor.moveToFirst()) {
            billsTotal = ecursor.getFloat(ecursor.getColumnIndex("billsTotal"));// get final total
        }
        ecursor.close();
        //Shopping Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as shopTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Shopping')" , null);
        if (ecursor.moveToFirst()) {
            shopTotal = ecursor.getFloat(ecursor.getColumnIndex("shopTotal"));// get final total
        }
        ecursor.close();
        //Rent Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as rentTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Rent')" , null);
        if (ecursor.moveToFirst()) {
            rentTotal = ecursor.getFloat(ecursor.getColumnIndex("rentTotal"));// get final total
        }
        ecursor.close();
        //Travel Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as travelTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Travel')" , null);
        if (ecursor.moveToFirst()) {
            travelTotal = ecursor.getFloat(ecursor.getColumnIndex("travelTotal"));// get final total
        }
        ecursor.close();
        //Others Total
        ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as othersxTotal FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE + " WHERE " + CeledgerContract.XpenseEntry.CATEGORY + " = ('Others')" , null);
        if (ecursor.moveToFirst()) {
            othersxTotal = ecursor.getFloat(ecursor.getColumnIndex("othersxTotal"));// get final total
        }
        ecursor.close();

        //Fill the Array-list
        catTot = new ArrayList<>();
        catTot.add(foodTotal);
        catTot.add(travelTotal);
        catTot.add(feesTotal);
        catTot.add(billsTotal);
        catTot.add(shopTotal);
        catTot.add(rentTotal);
        catTot.add(othersxTotal);
        //Transfer elements of Array-list to an Array
        CatTotal = new Float[catTot.size()];
        catTot.toArray(CatTotal);
        setupcatPieChart();

        //DRAW PIE CHART
        exppie.setRotationEnabled(true);
        //exppie.setUsePercentValues(true);
        //exppie.setHoleColor(Color.BLUE);
        //exppie.setCenterTextColor(Color.BLACK);
        exppie.setHoleRadius(25f);
        exppie.setTransparentCircleAlpha(0);
        exppie.setCenterText("Total Expenditure: " + xTotal + " €");
        exppie.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

    }

    public void setupcatPieChart(){
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i=0; i < CatTotal.length; i++){
            pieEntries.add( new PieEntry(CatTotal[i], XpenseCat[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, null);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);

        exppie.setData(data);
        exppie.animateY(1000);
        exppie.invalidate();
    }

    @Override
    public void onBackPressed() {
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else {
            Intent Expendact = new Intent(getApplicationContext(), ExpendActivity.class);
            startActivity(Expendact);
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.home_nav:
                Intent goHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(goHome);
                finish();
                break;
            case R.id.income_nav:
                Intent startincome = new Intent(getApplicationContext(),IncomeActivity.class);
                startActivity(startincome);
                finish();
                break;
            case R.id.addexpense_nav:
                Intent startaddexpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startaddexpense);
                finish();
                break;
            case R.id.addincome_nav:
                Intent startaddincome = new Intent(getApplicationContext(),AddIncome.class);
                startActivity(startaddincome);
                finish();
                break;
            case R.id.settings_nav:
                break;
            case R.id.about_nav:
                break;
            case R.id.expense_nav:
                Intent startexpense = new Intent(getApplicationContext(),ExpendActivity.class);
                startActivity(startexpense);
                finish();
                break;
        }
        return true;
    }
}


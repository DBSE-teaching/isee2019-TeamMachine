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

public class IncomeChart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String[] IncmeCat = {"Salary", "Payment", "Others"};
    Float[] CatTotal;
    ArrayList<Float> catTot;
    PieChart incpie;
    SQLiteDatabase incdb;
    DatabaseHelper incme_db;
    DrawerLayout navigation;
    Float iTotal, salaryTotal, paymentTotal, othersiTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_chart);

        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        ImageView inclist = findViewById(R.id.inclist);
        incpie = findViewById(R.id.incpiechart);
        incme_db = new DatabaseHelper(this);
        incdb = incme_db.getWritableDatabase();

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        //LIST VIEW
        inclist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openlist = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(openlist);
                finish();
            }
        });

        //Expense Total
        Cursor ecursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as Total FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE, null);
        if (ecursor.moveToFirst()) {
            iTotal = ecursor.getFloat(ecursor.getColumnIndex("Total"));// get final total
        }
        ecursor.close();
        //Salary Total
        ecursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as salaryTotal FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + CeledgerContract.IncomeEntry.COL_4 + " = ('Salary')" , null);
        if (ecursor.moveToFirst()) {
            salaryTotal = ecursor.getFloat(ecursor.getColumnIndex("salaryTotal"));// get final total
        }
        ecursor.close();

        //Payment Total
        ecursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as paymentTotal FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + CeledgerContract.IncomeEntry.COL_4 + " = ('Payment')", null);
        if (ecursor.moveToFirst()) {
            paymentTotal = ecursor.getFloat(ecursor.getColumnIndex("paymentTotal"));// get final total
        }
        ecursor.close();
        //Others Total
        ecursor = incdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as othersiTotal FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE + " WHERE " + CeledgerContract.IncomeEntry.COL_4 + " = ('Others')" , null);
        if (ecursor.moveToFirst()) {
            othersiTotal = ecursor.getFloat(ecursor.getColumnIndex("othersiTotal"));// get final total
        }
        ecursor.close();

        //Fill the Array-list
        catTot = new ArrayList<>();
        catTot.add(salaryTotal);
        catTot.add(paymentTotal);
        catTot.add(othersiTotal);
        //Transfer elements of Array-list to an Array
        CatTotal = new Float[catTot.size()];
        catTot.toArray(CatTotal);
        setupcatPieChart();

        //DRAW PIE CHART
        incpie.setRotationEnabled(true);
        //exppie.setUsePercentValues(true);
        //exppie.setHoleColor(Color.BLUE);
        //exppie.setCenterTextColor(Color.BLACK);
        incpie.setHoleRadius(25f);
        incpie.setTransparentCircleAlpha(0);
        incpie.setCenterText("Total Expenditure: " + iTotal + " €");
        incpie.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!
    }

    public void setupcatPieChart(){
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i=0; i < CatTotal.length; i++){
            pieEntries.add( new PieEntry(CatTotal[i], IncmeCat[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, null);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.BLUE);

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);

        incpie.setData(data);
        incpie.animateY(500);
        incpie.invalidate();
    }

    @Override
    public void onBackPressed() {
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else {
            Intent Incomeact = new Intent(getApplicationContext(), IncomeActivity.class);
            startActivity(Incomeact);
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

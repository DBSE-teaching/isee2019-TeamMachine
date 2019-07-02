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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//CLASS FOR MAIN ACTIVITY
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARATIONS
    String limit = "3";
    float xTotal, iTotal, sTotal;
    DatabaseHelper xpense_db;
    SQLiteDatabase expdb;
    XpnseAdaptor xpAdaptor;
    IncmeAdaptor icAdaptor;
    DrawerLayout navigation;

    //CREATE LAYOUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DECLARATION & DEFINITION
        LinearLayout linearLayoutIncme = findViewById(R.id.linearLayoutIncme);
        LinearLayout linearLayoutxpnd = findViewById(R.id.linearLayoutxpnd);
        TextView ExpenseAmount = findViewById(R.id.expendamt);
        TextView IncomeAmount = findViewById(R.id.incomeamt);
        TextView SavingAmount = findViewById(R.id.savingamt);
        ImageView addXpense = findViewById(R.id.addxpnseimg);
        RecyclerView Incmelist = findViewById(R.id.Incmelist);
        RecyclerView Xpnselist = findViewById(R.id.Xpnselist);
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

        //Linear Layout Income
        linearLayoutIncme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentIncme = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(startIntentIncme);
                finish();
            }
        });
        //Show Total Income
        Cursor icursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.IncomeEntry.COL_5 + ") as Total FROM " + CeledgerContract.IncomeEntry.INCOME_TABLE, null);
        if (icursor.moveToFirst()) {
            iTotal = icursor.getFloat(icursor.getColumnIndex("Total"));             // get final total
        }
        icursor.close();
        IncomeAmount.setText(String.valueOf(iTotal));
        //Latest Income List
        Incmelist.setLayoutManager(new LinearLayoutManager(this));
        icAdaptor = new IncmeAdaptor(this, getAllIncome());
        Incmelist.setAdapter(icAdaptor);

        //Linear Layout Expenditure
        linearLayoutxpnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentxpnd = new Intent(getApplicationContext(),ExpendActivity.class);
                startActivity(startIntentxpnd);
                finish();
            }
        });
        //Show Total Expense
        Cursor ecursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as Total FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE, null);
        if (ecursor.moveToFirst()) {
            xTotal = ecursor.getFloat(ecursor.getColumnIndex("Total"));// get final total
        }
        ecursor.close();
        ExpenseAmount.setText(String.valueOf(xTotal));
        //Latest Expense List
        Xpnselist.setLayoutManager(new LinearLayoutManager(this));
        xpAdaptor = new XpnseAdaptor(this, getAllXpense());
        Xpnselist.setAdapter(xpAdaptor);

        //Add Expense button
        addXpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentaddxpense = new Intent(getApplicationContext(),AddXpense.class);
                startActivity(startIntentaddxpense);
                finish();
            }
        });

        //Show Savings
        sTotal = iTotal - xTotal;
        sTotal = (float) (Math.round(sTotal*100.0)/100.0);
        SavingAmount.setText(String.valueOf(sTotal));
    }

    @Override
    public void onBackPressed() {
        if(navigation.isDrawerOpen(GravityCompat.START)){
            navigation.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }

    @Override
    public void recreate() {
        super.recreate();
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
                Intent startsettings = new Intent(getApplicationContext(),Settings.class);
                startActivity(startsettings);
                finish();
                break;
            case R.id.home_nav:
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
                CeledgerContract.XpenseEntry.TIMESTAMP +" DESC",
                limit);
        return cursor;
    }

    private Cursor getAllIncome(){
        Cursor cursor = expdb.query(CeledgerContract.IncomeEntry.INCOME_TABLE,
                null,
                null,
                null,
                null,
                null,
                CeledgerContract.IncomeEntry.COL_6 +" DESC",
                limit);
        return cursor;
    }
}

package com.example.celedger1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class IncDetailView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //DECLARATIONS
    DrawerLayout navigation;
    TextView incamttv, inccattv, incpmtv, incdtetv, incdesctv;
    String cat, amt, pm, dte, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_detail_view);

        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        incamttv = findViewById(R.id.incamttv);
        inccattv = findViewById(R.id.inccattv);
        incpmtv = findViewById(R.id.incpmtv);
        incdtetv = findViewById(R.id.incdtetv);
        incdesctv = findViewById(R.id.incdesctv);

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        Intent xpdesc = getIntent();
        cat = xpdesc.getStringExtra("icategory");
        amt = xpdesc.getStringExtra("iamount");
        dte = xpdesc.getStringExtra("idate");
        pm = xpdesc.getStringExtra("iPaymethod");
        desc = xpdesc.getStringExtra("idescription");

        incamttv.setText(amt);
        inccattv.setText(cat);
        incpmtv.setText(pm);
        incdtetv.setText(dte);
        incdesctv.setText(desc);
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
                Intent startsettings = new Intent(getApplicationContext(),Settings.class);
                startActivity(startsettings);
                finish();
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

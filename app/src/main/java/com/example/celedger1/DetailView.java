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

public class DetailView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //DECLARATIONS
    DrawerLayout navigation;
    TextView expamttv, expcattv, exppmtv, expdtetv, expdesctv;
    String cat, amt, pm, dte, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        NavigationView menu_navig = findViewById(R.id.menu_navig);
        navigation = findViewById(R.id.navig);
        ImageView menu = findViewById(R.id.menu);
        expamttv = findViewById(R.id.expamttv);
        expcattv = findViewById(R.id.expcattv);
        exppmtv = findViewById(R.id.exppmtv);
        expdtetv = findViewById(R.id.expdtetv);
        expdesctv = findViewById(R.id.expdesctv);

        //Navigation Drawer
        menu_navig.setNavigationItemSelectedListener(this);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation.openDrawer(GravityCompat.START);
            }
        });

        Intent xpdesc = getIntent();
        cat = xpdesc.getStringExtra("category");
        amt = xpdesc.getStringExtra("amount");
        dte = xpdesc.getStringExtra("date");
        pm = xpdesc.getStringExtra("Paymethod");
        desc = xpdesc.getStringExtra("description");

        expamttv.setText(amt);
        expcattv.setText(cat);
        exppmtv.setText(pm);
        expdtetv.setText(dte);
        expdesctv.setText(desc);
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

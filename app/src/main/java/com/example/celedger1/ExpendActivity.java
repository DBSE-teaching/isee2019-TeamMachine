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

//CLASS FOR EXPEND ACTIVITY
public class ExpendActivity extends AppCompatActivity {

    DatabaseHelper xpense_db;
    SQLiteDatabase expdb;
    XpnseAdaptor xpAdaptor;
    float xpTotal;

    //CREATE LAYOUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expend);

        //DECLARATION & DEFINITION
        RecyclerView ExpenselistRCV = findViewById(R.id.ExpenselistRCV);
        TextView Totalxpense = findViewById(R.id.Totalxpense);
        ImageView Home = findViewById(R.id.goHome);
        ImageView XpCat = findViewById(R.id.xpnsefilter);
        xpense_db = new DatabaseHelper(this);
        expdb = xpense_db.getWritableDatabase();

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntentmain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntentmain);
            }
        });

        //SHOWS SCROLLABLE EXPENSE LIST
        ExpenselistRCV.setLayoutManager(new LinearLayoutManager(this));
        xpAdaptor = new XpnseAdaptor(this, getAllXpense());
        ExpenselistRCV.setAdapter(xpAdaptor);

        //FILTER BY CATEGORIES

        //SHOW TOTAL EXPENSE
        Cursor dcursor = expdb.rawQuery("SELECT SUM(" + CeledgerContract.XpenseEntry.AMOUNT + ") as Total FROM " + CeledgerContract.XpenseEntry.XPENSE_TABLE, null);
        if (dcursor.moveToFirst()) {
            xpTotal = dcursor.getFloat(dcursor.getColumnIndex("Total"));// get final total
        }
        Totalxpense.setText(String.valueOf(xpTotal));

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
}

package com.example.celedger1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddincomeCat extends AppCompatActivity {

    EditText iccat;
    Button addicatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addincome_cat);

        addicatbtn = findViewById(R.id.addincomecatbtn);
        iccat = findViewById(R.id.addinccat);
        ImageView Back = findViewById(R.id.goBack);

        //GO BACK
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TEXT CHANGE LISTENER
        iccat.addTextChangedListener(addicattextwatcher);

        addicatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddincomeCat.this, "Category Not Added", Toast.LENGTH_LONG).show();
            }
        });
    }

    private TextWatcher addicattextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String xpnsecat = iccat.getText().toString().trim();

            addicatbtn.setEnabled(!xpnsecat.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}

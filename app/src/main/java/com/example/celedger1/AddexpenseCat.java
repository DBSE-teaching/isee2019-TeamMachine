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

public class AddexpenseCat extends AppCompatActivity {

    EditText xpcat;
    Button addxcatbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense_cat);

        addxcatbtn = findViewById(R.id.addexpensecatbtn);
        xpcat = findViewById(R.id.addexpcat);
        ImageView Back = findViewById(R.id.goBack);

        //GO BACK
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TEXT CHANGE LISTENER
        xpcat.addTextChangedListener(addxcattextwatcher);

        addxcatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddexpenseCat.this, "Category Not Added", Toast.LENGTH_LONG).show();
            }
        });
    }

    private TextWatcher addxcattextwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String xpnsecat = xpcat.getText().toString().trim();

            addxcatbtn.setEnabled(!xpnsecat.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}

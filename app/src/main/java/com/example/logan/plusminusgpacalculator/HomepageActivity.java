package com.example.logan.plusminusgpacalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomepageActivity extends AppCompatActivity {

    Button btnSettings;
    Button btnhomeCalc;
    TextView tvClickHere;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnhomeCalc = (Button) findViewById(R.id.homeCalculateButton);
        //btnSettings = (Button) findViewById(R.id.homeSettingsButton);
        //tvClickHere = (TextView) findViewById(R.id.ClickableText);

        btnhomeCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,CalculatorInputActivity.class));
            }
        });
        /**
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,SettingsActivity.class));
            }
        });
        tvClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepageActivity.this,HelpUsOutActivity.class));
            }
        });
         */
    }
}

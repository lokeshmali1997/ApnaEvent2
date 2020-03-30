package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MyOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.apna1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }
}

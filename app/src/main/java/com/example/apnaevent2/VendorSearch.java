package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VendorSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

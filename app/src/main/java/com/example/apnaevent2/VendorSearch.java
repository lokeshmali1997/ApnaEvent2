package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class VendorSearch extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        String cat = extra.getString("cat");






    }
}

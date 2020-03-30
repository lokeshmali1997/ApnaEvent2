package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class full_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.apna1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        ImageView imageView = findViewById(R.id.img_full);
        int img_id = getIntent().getExtras().getInt("img_id");
        imageView.setImageResource(img_id);




    }
}

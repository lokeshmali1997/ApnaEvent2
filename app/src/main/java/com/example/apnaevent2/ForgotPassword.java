package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ForgotPassword extends AppCompatActivity {

    LinearLayout layout1;
    LinearLayout layout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        layout1 = (LinearLayout) findViewById(R.id.linyer1);
        layout2 = findViewById(R.id.linyer2);


    }


    public void btnSendEmaul(View view) {

        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);

    }
/*
    public void btnVerifyOtp(View view) {

        LinearLayout layout2  = findViewById(R.id.linyer2);
        LinearLayout layout3  = findViewById(R.id.linyer3);

        layout3.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);

    }
    */

    public void btnsave(View view) {
    }
}

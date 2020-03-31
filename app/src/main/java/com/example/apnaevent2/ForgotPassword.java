package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ForgotPassword extends AppCompatActivity {

    EditText etemail,verifyotp,etnewpass,etconfpass;
    Button btnsendemail,btnverifyitp,btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        etemail = findViewById(R.id.etEmailsend);
        verifyotp = findViewById(R.id.etotp);
        etnewpass = findViewById(R.id.etnewpass);
        etconfpass = findViewById(R.id.etcomfnewPass);
        btnsendemail = findViewById(R.id.btnsendotp);
        btnsendemail = findViewById(R.id.btnsendotp);
        btnverifyitp = findViewById(R.id.btnverifyotp);
        btnsave = findViewById(R.id.btnforgotsave);

    }


    public void btnSendEmaul(View view) {

        etemail.setVisibility(View.INVISIBLE);
        btnsendemail.setVisibility(View.INVISIBLE);
        verifyotp.setVisibility(View.VISIBLE);
        btnverifyitp.setVisibility(View.VISIBLE);


    }

    public void btnVerifyOtp(View view) {


        verifyotp.setVisibility(View.INVISIBLE);
        btnverifyitp.setVisibility(View.INVISIBLE);
        etnewpass.setVisibility(View.VISIBLE);
        etconfpass.setVisibility(View.VISIBLE);
        btnsave.setVisibility(View.VISIBLE);

    }


    public void btnsave(View view) {
    }


}

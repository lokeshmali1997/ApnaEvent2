package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ForgotPassword extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    public void btnSendEmaul(View view) {
        LinearLayout layout1  = (LinearLayout) this.findViewById(R.id.linyer1);
        LinearLayout layout2  = (LinearLayout)this.findViewById(R.id.linyer2);



       layout1.setVisibility(LinearLayout.INVISIBLE);
        layout2.setVisibility(LinearLayout.VISIBLE);





    }

    public void btnVerifyOtp(View view) {
/*
        LinearLayout layout2  = findViewById(R.id.linyer2);
        LinearLayout layout3  = findViewById(R.id.linyer3);

        layout3.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);
*/
    }

    public void btnsave(View view) {
    }
}

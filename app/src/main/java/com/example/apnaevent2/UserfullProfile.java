package com.example.apnaevent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UserfullProfile extends AppCompatActivity {

    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    Button etbtn,savebtn,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfull_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



    }


    public void backbutton(View view){

        ImageButton imageButton = findViewById(R.id.imgbtnback);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });


    }

    public  void editprofile(View view){

        etbtn = findViewById(R.id.btnedit);
        savebtn = findViewById(R.id.btnsave);
        btnlogout= findViewById(R.id.btnlogout);


         textView = findViewById(R.id.txtusername);
         textView1 = findViewById(R.id.txtemail);
         textView2 = findViewById(R.id.txtpassword);
         textView3 = findViewById(R.id.txtnumbaer);
         textView4 = findViewById(R.id.etUser);
         textView5 = findViewById(R.id.etEmail);
         textView6 = findViewById(R.id.etPass);
         textView7 = findViewById(R.id.etPhone);


        etbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnlogout.setVisibility(view.INVISIBLE);
                etbtn.setVisibility(view.INVISIBLE);
                textView.setVisibility(view.INVISIBLE);
                textView1.setVisibility(view.INVISIBLE);
                textView2.setVisibility(view.INVISIBLE);
                textView3.setVisibility(view.INVISIBLE);
                textView4.setVisibility(view.VISIBLE);
                textView5.setVisibility(view.VISIBLE);
                textView6.setVisibility(view.VISIBLE);
                textView7.setVisibility(view.VISIBLE);
                savebtn.setVisibility(view.VISIBLE);



            }
        });



    }


    public void saveprofile(View view) {

         savebtn = findViewById(R.id.btnsave);
         etbtn = findViewById(R.id.btnedit);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                savebtn.setVisibility(view.INVISIBLE);
                btnlogout.setVisibility(view.VISIBLE);
                etbtn.setVisibility(view.INVISIBLE);
                textView4.setVisibility(view.INVISIBLE);
                textView5.setVisibility(view.INVISIBLE);
                textView6.setVisibility(view.INVISIBLE);
                textView7.setVisibility(view.INVISIBLE);
                textView.setVisibility(view.VISIBLE);
                textView1.setVisibility(view.VISIBLE);
                textView2.setVisibility(view.VISIBLE);
                textView3.setVisibility(view.VISIBLE);

                etbtn.setVisibility(view.VISIBLE);

            }
        });


    }
}

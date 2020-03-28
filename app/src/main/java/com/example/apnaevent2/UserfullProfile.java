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

        Button etbtn = findViewById(R.id.btnedit);
        Button savebtn = findViewById(R.id.btnsave);
        Button btnlogout= findViewById(R.id.btnlogout);


        TextView textView = findViewById(R.id.txtusername);
        TextView textView1 = findViewById(R.id.txtemail);
        TextView textView2 = findViewById(R.id.txtpassword);
        TextView textView3 = findViewById(R.id.txtnumbaer);
        TextView textView4 = findViewById(R.id.etUser);
        TextView textView5 = findViewById(R.id.etEmail);
        TextView textView6 = findViewById(R.id.etPass);
        TextView textView7 = findViewById(R.id.etPhone);


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

        Button savebtn = findViewById(R.id.btnsave);
        Button etbtn = findViewById(R.id.btnedit);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savebtn.setVisibility(view.INVISIBLE);
                etbtn.setVisibility(view.VISIBLE);
            }
        });


    }
}

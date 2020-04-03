package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferObserverSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText etUsername,etPassword;
    Button btnLogin,btnForgot;
    String toast = "";

    DatabaseReference mUserMaster = FirebaseDatabase.getInstance().getReference().child("UserMaster");
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        etUsername  = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgot = (Button)findViewById(R.id.btnForgot);


        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               nextCall();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUserMaster.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            if(data.child("email").getValue().equals(etUsername.getText().toString()) ||
                                    data.child("phone").getValue().equals(etUsername.getText().toString()))
                            {
                                if(data.child("pass").getValue().equals(etPassword.getText().toString()))
                                {
                                    sharedUserLogin(data.getKey().toString());
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



    }


    public void checkValidation() {
        if (etUsername.getText().toString().trim().equalsIgnoreCase("")) {
            toast = "Please Enter Your Email and Number";
        } else if (etPassword.getText().toString().equalsIgnoreCase("")) {
            toast = "Please Enter password";
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
            SharedPreferences sh =  getSharedPreferences("UserData",MODE_PRIVATE);

            if(sh.contains("UserId"))
            {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }

    }

    private void sharedUserLogin(String id)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();


        myEdit.putString("UserId",id);
        myEdit.commit();
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }


    public void Signup(View view) {
                Intent i = new Intent(this,SignUp.class);
                startActivity(i);

    }

    /*
    public void  ForgotPassword(View view)
    {
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
    }

     */

    public void nextCall()
    {
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
    }
}

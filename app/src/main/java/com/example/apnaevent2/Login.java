package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    Button btnLogin;

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


    @Override
    protected void onStart() {
        super.onStart();
            SharedPreferences sh =  getSharedPreferences("UserData",MODE_PRIVATE);

            if(sh.contains("UserId"))
            {
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

    public void forgotpassword(View view) {

        Button button = findViewById(R.id.btnforgot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);



            }
        });

    }

    public void Signup(View view) {

        Button button = findViewById(R.id.btnloginsignip);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });

    }
}

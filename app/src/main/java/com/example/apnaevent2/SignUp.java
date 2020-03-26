package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText etUser,etEmail,etPhone,etPass;
    Button btnSignUp;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUserMasterRef = mRootRef.child("UserMaster");
    UserMaster user;
    String toast = "";
    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        etUser = findViewById(R.id.etUser);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPass);
        btnSignUp = findViewById(R.id.btnSignUp);

        mUserMasterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkValidation();
                    if(!toast.isEmpty())
                    {
                        Toast.makeText(SignUp.this, toast, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String username = etUser.getText().toString();
                        String mail = etEmail.getText().toString();
                        String pass = etPass.getText().toString();
                        long phone = Long.parseLong(etPhone.getText().toString());

                        user = new UserMaster();
                        user.setUsername(username);
                        user.setEmail(mail);
                        user.setPass(pass);
                        user.setPhone(phone);

                        mUserMasterRef.child(String.valueOf(maxId + 1)).setValue(user);
                        Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignUp.this,Login.class);
                        startActivity(intent);
                    }
                }
            });



    }


    public void checkValidation()
    {

        if(etUser.getText().toString().equalsIgnoreCase(""))
        {
            toast = "Please Enter Your Name";
        }
        else if(etUser.getText().toString().matches("[a-zA-Z][a-zA-Z ]*") != true)
        {
            toast = "Please Enter Valid Name";
        }
        else {
            if (etEmail.getText().toString().equalsIgnoreCase("")) {
                toast = "Please Enter Email Address";
            } else{
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                                    "[a-zA-Z0-9_+&*-]+)*@" +
                                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                    "A-Z]{2,7}$";
                Pattern pat = Pattern.compile(emailRegex);
                if(!pat.matcher(etEmail.getText().toString()).matches())
                {
                    toast = "Please Enter Valid Email Address";
                }
                else
                {
                    if(etPass.getText().toString().equalsIgnoreCase(""))
                    {
                        toast = "Please Enter Password";
                    }
                    else if(etPass.getText().toString().length() < 8)
                    {
                        toast = "Password should be length of minimum 8";
                    }
                    else
                    {
                        if(etPhone.getText().toString().equalsIgnoreCase(""))
                        {
                            toast = "Please Enter Phone Number";
                        }
                        else
                        {
                            if(!etPhone.getText().toString().matches("\\d{10}"))
                            {
                                toast = "Please Enter Valid Phone Number";
                            }
                        }
                    }
                }
            }
        }
    }
}

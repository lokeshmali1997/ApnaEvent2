package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserfullProfile extends AppCompatActivity {

    TextView txtUser,txtEmail,txtPass,txtMob;
    EditText etUser,etPass,etMob,etEmail;
    Button etbtn,savebtn,btnlogout;
    SharedPreferences sharedPreferences;
    DatabaseReference userMasterRef = FirebaseDatabase.getInstance().getReference("UserMaster");
    String uid;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfull_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        initializeAllViews();
        loadUserData();



    }

    public void initializeAllViews()
    {
        etbtn = findViewById(R.id.btnedit);
        savebtn = findViewById(R.id.btnsave);
        btnlogout= findViewById(R.id.btnlogout);

        txtUser = findViewById(R.id.txtusername);
        txtEmail = findViewById(R.id.txtemail);
        txtPass = findViewById(R.id.txtpassword);
        txtMob = findViewById(R.id.txtnumbaer);
        etUser = findViewById(R.id.etUser);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etMob = findViewById(R.id.etPhone);

    }

    public void loadUserData()
    {
        sharedPreferences = getSharedPreferences("UserData",MODE_PRIVATE);
        uid = sharedPreferences.getString("UserId","");

        userMasterRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                txtUser.setText(ds.child("username").getValue().toString());
                txtEmail.setText(ds.child("email").getValue().toString());
                txtPass.setText(ds.child("pass").getValue().toString());
                txtMob.setText(ds.child("phone").getValue().toString());

                etUser.setText(ds.child("username").getValue().toString());
                etEmail.setText(ds.child("email").getValue().toString());
                etPass.setText(ds.child("pass").getValue().toString());
                etMob.setText(ds.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void backbutton(View view){

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    public  void editprofile(View view){



         btnlogout.setVisibility(view.INVISIBLE);
                etbtn.setVisibility(view.INVISIBLE);
                txtUser.setVisibility(view.INVISIBLE);
                txtEmail.setVisibility(view.INVISIBLE);
                txtPass.setVisibility(view.INVISIBLE);
                txtMob.setVisibility(view.INVISIBLE);
                etUser.setVisibility(view.VISIBLE);
                etEmail.setVisibility(view.VISIBLE);
                etPass.setVisibility(view.VISIBLE);
                etMob.setVisibility(view.VISIBLE);
                savebtn.setVisibility(view.VISIBLE);

                loadUserData();



    }


    public void saveprofile(View view) {

                builder = new AlertDialog.Builder(this);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to update the Profile ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                savebtn.setVisibility(view.INVISIBLE);
                                btnlogout.setVisibility(view.VISIBLE);
                                etbtn.setVisibility(view.INVISIBLE);
                                etUser.setVisibility(view.INVISIBLE);
                                etEmail.setVisibility(view.INVISIBLE);
                                etPass.setVisibility(view.INVISIBLE);
                                etMob.setVisibility(view.INVISIBLE);
                                txtUser.setVisibility(view.VISIBLE);
                                txtEmail.setVisibility(view.VISIBLE);
                                txtPass.setVisibility(view.VISIBLE);
                                txtMob.setVisibility(view.VISIBLE);

                                etbtn.setVisibility(view.VISIBLE);


                                userMasterRef.child(uid).child("username").setValue(etUser.getText().toString());
                                userMasterRef.child(uid).child("email").setValue(etEmail.getText().toString());
                                userMasterRef.child(uid).child("phone").setValue(etMob.getText().toString());
                                userMasterRef.child(uid).child("pass").setValue(etPass.getText().toString());
                                loadUserData();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();


    }

    public void logout(View view) {

                sharedPreferences = getSharedPreferences("UserData",MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent i = new Intent(UserfullProfile.this,Login.class);
                startActivity(i);

    }
}

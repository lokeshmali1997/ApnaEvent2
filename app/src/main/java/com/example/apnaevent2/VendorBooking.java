package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class VendorBooking extends AppCompatActivity {

    //UI References
    private EditText fromDateET;
    private EditText toDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private NonScrollListView list_product;
    private ProductAdaptorForBooking productselected;
    private TextView txtuser,txtvendor,txtTotalAmount;
    private String username = "",vid, uid;
    long Totalamount,days;
    ArrayList<Product> selected;
    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserMaster");
    DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference("Bookings");

    long maxId =0, bookid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_booking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fromDateET = findViewById(R.id.fromDate);
        toDateEtxt = findViewById(R.id.toDate);
        fromDateET.setInputType(InputType.TYPE_NULL);

        toDateEtxt.setInputType(InputType.TYPE_NULL);
        list_product = findViewById(R.id.list_product);
        txtuser = findViewById(R.id.txtViewCustomer);
        txtvendor = findViewById(R.id.txtViewVender);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        fillListView();

        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        toDateEtxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changeTotalAmountByDateSelection();
            }
        });

        toDateEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    // date picker dialog
                    toDatePickerDialog = new DatePickerDialog(VendorBooking.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    toDateEtxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }, year, month, day);
                    toDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()+24*60*60*1000*2);
                    toDatePickerDialog.show();
                }

            }
        });


         fromDateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                fromDatePickerDialog = new DatePickerDialog(VendorBooking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromDateET.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                fromDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 24*60*60*1000);
                fromDatePickerDialog.show();
                }

            }
        });
    }

    public void btnBook(View view)
    {
        if(fromDateET.getText().equals("") || toDateEtxt.getText().equals("") || days <=0)
        {
            Toast.makeText(this, "Please Select Date", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Bookings nb = new Bookings();
            nb.setBooking_UserId(uid);
            nb.setBooking_VendorId(vid);
            nb.setBooking_FromDate(fromDateET.getText().toString());
            nb.setBooking_ToDate(toDateEtxt.getText().toString());
            nb.setBooking_TotalAmount(txtTotalAmount.getText().toString());

            bookRef.child(String.valueOf(maxId+1)).setValue(nb);
            bookid = maxId+1;
            DatabaseReference book_ProductRef = FirebaseDatabase.getInstance().getReference("Booking_Product");
            for(Product p : selected)
            {
                book_ProductRef.child(String.valueOf(bookid)).child(p.getpId()).setValue(p.getpName());
            }
            Intent i = new Intent();


        }
    }

    public void changeTotalAmountByDateSelection()
    {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date dF = new Date();
        Date dT = new Date();
        try {
            dF = formatter1.parse(String.valueOf(fromDateET.getText()));
            dT = formatter1.parse(String.valueOf(toDateEtxt.getText()));
        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, fromDateET.getText().toString(), Toast.LENGTH_SHORT).show();
        long diff = Math.abs(dT.getTime()-dF.getTime());
        days = diff/(24*60*60*1000);
        txtTotalAmount.setText(String.valueOf(Totalamount*days));


    }

    public void fillListView()
    {
        Intent i = getIntent();
        selected = (ArrayList<Product>) i.getSerializableExtra("selected");

        for(Product p : selected)
        {
            Totalamount += Integer.parseInt(p.getpPrice());
        }

        txtTotalAmount.setText(String.valueOf(Totalamount));
        productselected = new ProductAdaptorForBooking(this, selected);
        list_product.setAdapter(productselected);
        vid = i.getStringExtra("vid");
        txtvendor.setText(i.getStringExtra("vname").toString());

        SharedPreferences sh = getSharedPreferences("UserData",MODE_PRIVATE);
        uid = sh.getString("UserId","");

        userRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.child("username").getValue().toString();
                txtuser.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}

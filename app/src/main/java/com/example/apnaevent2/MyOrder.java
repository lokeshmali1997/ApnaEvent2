package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MyOrder extends AppCompatActivity {

    RecyclerView orderList;
    ArrayList<OrderListElements> myorderList;
    MyOrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        orderList = findViewById(R.id.orderList);
        myorderList = new ArrayList<OrderListElements>();
        orderList.setHasFixedSize(true);
        orderList.setLayoutManager(new LinearLayoutManager(this));



        orderAdapter = new MyOrderAdapter(this,myorderList);
        orderList.setAdapter(orderAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();


    SharedPreferences sh = getSharedPreferences("UserData",MODE_PRIVATE);
        String uid = sh.getString("UserId","");


        DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference("Bookings");
        bookRef.orderByChild("booking_UserId").equalTo(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    OrderListElements orderListElements = new OrderListElements();
                    orderListElements.setBookingId(dataSnapshot.getKey());
                    orderListElements.setDateFrom(dataSnapshot.child("booking_FromDate").getValue().toString());
                    orderListElements.setDateTo(dataSnapshot.child("booking_ToDate").getValue().toString());
                    orderListElements.setTotalAmount(dataSnapshot.child("booking_TotalAmount").getValue().toString());
                    String vid = dataSnapshot.child("booking_VendorId").getValue().toString();
                    DatabaseReference venderRef = FirebaseDatabase.getInstance().getReference("Vendor");
                    venderRef.child(vid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            orderListElements.setVendorName(dataSnapshot.child("name").getValue().toString());
                            orderListElements.setVendorPhone(dataSnapshot.child("mob").getValue().toString());
                            orderAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    DatabaseReference bookProductRef = FirebaseDatabase.getInstance().getReference("Booking_Product");
                    String bid = dataSnapshot.getKey();
                    bookProductRef.child(bid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String products = "";
                            for(DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                products += ds.getValue().toString() +", ";
                            }
                            orderListElements.setProductNames(products);
                            orderAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    myorderList.add(orderListElements);

                    orderAdapter.notifyDataSetChanged();




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

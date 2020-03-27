package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VendorSearch extends AppCompatActivity {

    ListView listView;

    DatabaseReference vendorRoot = FirebaseDatabase.getInstance().getReference("Vendor");
    Query query;
    ArrayList<Vender> vendorList = new ArrayList<>();;
    VendorAdaptor vendorAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extra = getIntent().getExtras();
        String cat = extra.getString("cat");
        if(cat == null)
        {
            Intent intent = new Intent(this,FragmentCategory.class);
            startActivity(intent);
        }





        query = vendorRoot.orderByChild("category").equalTo(Integer.parseInt(cat));
        listView = (ListView) findViewById(R.id.lstVendor);


        vendorList = new ArrayList<>();

        vendorAdaptor = new VendorAdaptor(this,vendorList);

        listView.setAdapter(vendorAdaptor);
        listView.setItemsCanFocus(true);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                        Vender vender = new Vender();
                        //Toast.makeText(VendorSearch.this, (CharSequence) data.child("name").getValue(), Toast.LENGTH_SHORT).show();
                        vender.setName(dataSnapshot.child("name").getValue().toString());
                        vender.setMob(dataSnapshot.child("mob").getValue().toString());
                        vender.setEmail(dataSnapshot.child("email").getValue().toString());
                        vender.setId(dataSnapshot.getKey());
                        vender.setRating(Integer.parseInt(dataSnapshot.child("rating").getValue().toString()));
                        vendorList.add(vender);

                vendorAdaptor.notifyDataSetChanged();
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



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),VendorFullProfile.class);
                    intent.putExtra("v_id",((TextView)findViewById(R.id.txtVendorName)).getTag().toString());
                    startActivity(intent);
            }
        });



    }


}

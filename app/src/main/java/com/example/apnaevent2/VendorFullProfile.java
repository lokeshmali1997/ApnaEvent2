package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class VendorFullProfile extends AppCompatActivity {

    TextView txtVName,txtVEmail,txtVPhone,txtVAdd;
    String v_id;
    DatabaseReference vendorproductRoot = FirebaseDatabase.getInstance().getReference("Vendor_Product");
    DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("Product");
    Query query;
    ArrayList<Product> productList;
    ProductAdaptor productAdaptor;
    ListView list_product;
    ArrayList<String> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_full_profile);
        txtVName = findViewById(R.id.txtVName);
        txtVEmail = findViewById(R.id.txtVEmail);
        txtVPhone = findViewById(R.id.txtVPhone);
        txtVAdd = findViewById(R.id.txtVAdd);
        Bundle extra = getIntent().getExtras();
        txtVName.setText(extra.getString("v_name"));
        txtVEmail.setText(extra.getString("v_email"));
        txtVAdd.setText(extra.getString("v_add"));
        txtVPhone.setText(extra.getString("v_mob"));
        v_id = extra.getString("v_id");



        query = vendorproductRoot.child(v_id);
        list_product = (ListView)findViewById(R.id.list_product);


        productList = new ArrayList<Product>();
        productAdaptor = new ProductAdaptor(this, productList);

        list_product.setAdapter(productAdaptor);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    products.add(ds.getKey());
                    Toast.makeText(VendorFullProfile.this, ds.getKey(), Toast.LENGTH_SHORT).show();


                }
                productAdaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    if(products.contains(ds.getKey()))
                    {

                        Product product = new Product();
                        product.setpId(ds.getKey());
                        product.setpName(ds.child("pname").getValue().toString());
                        product.setpPrice(ds.child("pprice").getValue().toString());
                        product.setpPer(ds.child("pper").getValue().toString());

                        productList.add(product);
                        Toast.makeText(VendorFullProfile.this, ds.child("pname").getValue().toString(), Toast.LENGTH_SHORT).show();
                        System.out.println(ds.child("pname").getValue());

                    }

                }
                productAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }
}

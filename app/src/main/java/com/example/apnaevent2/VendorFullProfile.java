package com.example.apnaevent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class VendorFullProfile extends AppCompatActivity implements ProductAdaptor.CheckBoxCheckedListener{

    TextView txtVName,txtVEmail,txtVPhone,txtVAdd;
    String v_id;
    DatabaseReference vendorproductRoot = FirebaseDatabase.getInstance().getReference("Vendor_Product");
    DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("Product");
    Query query;
    ArrayList<Product> productList;
    ArrayList<Product> selectedList = new ArrayList<Product>();
    ProductAdaptor productAdaptor;
    NonScrollListView list_product;
    ArrayList<String> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_full_profile);







        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.apna1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



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
        list_product = (NonScrollListView) findViewById(R.id.list_product);


        productList = new ArrayList<Product>();
        productAdaptor = new ProductAdaptor(this, productList);

        list_product.setAdapter(productAdaptor);

        productAdaptor.setCheckedListener(this);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    products.add(ds.getKey());
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

                    }

                }
                productAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void CheckOut(View view)
    {
        if(selectedList.isEmpty())
        {
            Toast.makeText(this, "Please select at least one product to Checkout", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i = new Intent(this,VendorBooking.class);
            Bundle args = new Bundle();
            args.putSerializable("selected",(Serializable)selectedList);
            i.putExtra("bundle",args);
            startActivity(i);
        }
    }

    @Override
    public void getCheckBoxCheckedListener(int position) {
        Product product = productList.get(position);
        if(selectedList.contains(product))
        {
            selectedList.remove(product);
        }
        else
        {
            selectedList.add(product);
        }

    }

}

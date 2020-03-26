package com.example.apnaevent2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCategory extends Fragment {
View view;

    public FragmentCategory(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_category, container, false);

        decorationevent();
        foodevent();
        logisticevent();

        
        return view;
                
    }

    private void logisticevent() {
        ImageButton imageButton = view.findViewById(R.id.imgbtnlogistics);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(),VendorSearch.class);
                startActivity(intent);
            }
        });
    }

    private void decorationevent() {
        ImageButton imageButton = view.findViewById(R.id.imgbtndecoration);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(),VendorSearch.class);
                startActivity(intent);
            }
        });
    }

    private void foodevent(){
        ImageButton imageButton = view.findViewById(R.id.imgbtncatfoodi);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(),VendorSearch.class);
                startActivity(intent);
            }
        });
    }




}
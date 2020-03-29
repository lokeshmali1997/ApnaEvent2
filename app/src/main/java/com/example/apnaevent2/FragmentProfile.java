package com.example.apnaevent2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

 public class FragmentProfile extends Fragment {

     View view;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


                view = inflater.inflate(R.layout.fragment_profile, container, false);


        LinearLayout linearLayout = view.findViewById(R.id.linyer1);


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),UserfullProfile.class);
                startActivity(i);


            }
        });


        googlemap();
        shareapp();

        return view;


    }

     private void shareapp() {

        LinearLayout layout = view.findViewById(R.id.linyer3);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                String app_url = "http://play.google.com/store/apps/details?id=com.example.administrator";
                shareIntent.putExtra(Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent,"Share via"));
            }
        });

     }

     private void googlemap() {

        String uri = "geo:18.5177542,73.8126939?q=MIT World Peace University, Paud Road, Rambaug Colony, Kothrud, Pune, Maharashtra";
         LinearLayout layout  = view.findViewById(R.id.linyer2);

         layout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 
                 Uri i =  Uri.parse(uri);
                 Intent intent = new Intent(Intent.ACTION_VIEW,i);
                 intent.setPackage("com.google.android.apps.maps");
                 if(intent.resolveActivity(getActivity().getPackageManager()) != null)
                 {
                     startActivity(intent);
                 }
                 
             }
         });

     }


 }




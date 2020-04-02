package com.example.apnaevent2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class FragmentGallery extends Fragment {

    View view;
    GridView gridView;

    ArrayList<Integer> mImageIds = new ArrayList<>(Arrays.asList(
            R.drawable.deco5,R.drawable.dj0,R.drawable.food5,R.drawable.h3,R.drawable.dj7,R.drawable.dj4,R.drawable.food2,R.drawable.dj5
            ,R.drawable.food3
    ));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_gallery,container,false);

        gallerygridview();
        return view;
    }

    private void gallerygridview() {

        gridView = view.findViewById(R.id.myGrid);
       gridView.setAdapter(new ImageAdapter(mImageIds,this.getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item_pos = mImageIds.get(position);

                ShowDialogBox(item_pos);
            }
        });

    }

    public void ShowDialogBox(final int item_pos){
        final Dialog dialog = new Dialog(this.getActivity());

        dialog.setContentView(R.layout.custom_dialog);

        //Getting custom dialog views
        TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView Image = dialog.findViewById(R.id.img);
        Button btn_Full = dialog.findViewById(R.id.btn_full);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        String title = getResources().getResourceName(item_pos);

        //extracting name
        int index = title.indexOf("/");
        String name = title.substring(index+1,title.length());
        Image_name.setText(name);

        Image.setImageResource(item_pos);

        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  =  new Intent(view.getContext(),full_view.class);
                i.putExtra("img_id",item_pos);
                startActivity(i);

            }
        });

        dialog.show();

    }



}

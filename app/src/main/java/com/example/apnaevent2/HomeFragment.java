package com.example.apnaevent2;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
   // ViewFlipper viewFlipper;
   // private int[] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
ViewPager viewPager;
CustomImageAdapter adapter;




    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        adapter = new CustomImageAdapter(this.getActivity());
        viewPager.setAdapter(adapter);

        initImageSlider();
        return view;
    }

    private void initImageSlider() {

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if(viewPager.getCurrentItem() == 0){
                    viewPager.setCurrentItem(1);
                } else if(viewPager.getCurrentItem() == 1){
                    viewPager.setCurrentItem(2);
                } else if(viewPager.getCurrentItem() == 2) {
                    viewPager.setCurrentItem(3);
                }else if(viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);

                } else {
                    viewPager.setCurrentItem(0);
                }
                //viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);

        // Pager listener over indicator

    }

}


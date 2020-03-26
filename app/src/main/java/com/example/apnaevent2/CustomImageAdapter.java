package com.example.apnaevent2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.apnaevent2.R;

public class CustomImageAdapter extends PagerAdapter {

   private int[] image_resource = {R.drawable.deco5,R.drawable.dj0,R.drawable.food5,R.drawable.h3,R.drawable.dj7};
   private Context ctx;
   private LayoutInflater layoutInflater;

    public CustomImageAdapter(Context ctx)
    {
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    public Object instantiateItem(ViewGroup container,int position){


        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.imageslider,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_slide);

        imageView.setImageResource(image_resource[position]);

        container.addView(item_view);
        return item_view;
    }


    public  void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((LinearLayout)object);
    }
}

package com.example.apnaevent2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class VendorAdaptor extends BaseAdapter {
    Context context;
    ArrayList<Vender> venderArray;

    public VendorAdaptor(Context context, ArrayList<Vender> venderArray)
    {
        this.context = context;
        this.venderArray = venderArray;
    }

    @Override
    public int getCount() {
        return this.venderArray.size();
    }

    @Override
    public Object getItem(int position) {
        return venderArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.vendor_search_row,null);

        }
        return null;
    }
}

package com.example.apnaevent2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdaptorForBooking extends BaseAdapter {
    Context context;
    ArrayList<Product> productArray;

    public ProductAdaptorForBooking(Context context, ArrayList<Product> productArray)
    {
        this.context = context;
        this.productArray = productArray;
    }

    @Override
    public int getCount() {
        return this.productArray.size();
    }

    @Override
    public Object getItem(int position) {
        return productArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //convertView = inflater.inflate(R.layout)
                convertView = inflater.inflate(R.layout.product_selected_list_row, null);


                Product product = productArray.get(position);
                TextView pname = convertView.findViewById(R.id.p_name);
                TextView pper = convertView.findViewById(R.id.p_per);
                TextView pprice = convertView.findViewById(R.id.p_price);

                pname.setText(product.getpName());
                pper.setText("per/"+product.getpPer());
                pprice.setText(product.getpPrice());
                pname.setTag(product.getpId());




            return convertView;
    }


}



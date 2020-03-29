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

public class ProductAdaptor extends BaseAdapter {
    Context context;
    ArrayList<Product> productArray;
    private CheckBoxCheckedListener checkedListener;

    public ProductAdaptor(Context context, ArrayList<Product> productArray)
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

            ViewHolder holder = null;

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //convertView = inflater.inflate(R.layout)
                convertView = inflater.inflate(R.layout.product_list_row, null);
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

                Product product = productArray.get(position);
                holder.pname.setText(product.getpName());
                holder.pper.setText("per/"+product.getpPer());
                holder.pprice.setText(product.getpPrice());
                holder.pname.setTag(product.getpId());
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(checkedListener != null)
                        {
                            checkedListener.getCheckBoxCheckedListener(position);
                        }

                    }
                });




            return convertView;
    }

    public interface CheckBoxCheckedListener{
        void getCheckBoxCheckedListener(int position);
    }

    public  void  setCheckedListener(CheckBoxCheckedListener checkedListener)
    {
        this.checkedListener = checkedListener;
    }
}


class  ViewHolder
{
    TextView pname,pper,pprice;
    CheckBox checkBox;

    ViewHolder(View v)
    {
        pname = v.findViewById(R.id.p_name);
        pprice = v.findViewById(R.id.p_price);
        pper = v.findViewById(R.id.p_per);
        checkBox = v.findViewById(R.id.chkBox);
    }

}

package com.example.apnaevent2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {
    Context context;
    private ArrayList<OrderListElements> myorderList;

    public MyOrderAdapter(Context context, ArrayList<OrderListElements> myorderList)
    {
        this.context = context;
        this.myorderList = myorderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.myorder_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderListElements element = myorderList.get(position);
        holder.txtVName.setText(element.getVendorName());
        holder.txtVPhone.setText(element.getVendorPhone());
        holder.txtBID.setText(element.getBookingId());
        holder.txtProducts.setText(element.getProductNames());
        holder.txtfrom.setText(element.getDateFrom());
        holder.txtTo.setText(element.getDateTo());
        holder.txtAmount.setText(element.getTotalAmount());

    }

    @Override
    public int getItemCount() {
        return myorderList.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtVName,txtVPhone,txtBID,txtProducts,txtfrom,txtTo,txtAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtVName  = itemView.findViewById(R.id.txtVendorNameOrder);
            this.txtVPhone = itemView.findViewById(R.id.txtVendorPhone);
            this.txtBID = itemView.findViewById(R.id.txtBookingId);
            this.txtProducts = itemView.findViewById(R.id.txtorderproductname);
            this.txtfrom = itemView.findViewById(R.id.txtstartdate);
            this.txtTo = itemView.findViewById(R.id.txtenddate);
            this.txtAmount = itemView.findViewById(R.id.txtTotalAmount);

        }
    }
}

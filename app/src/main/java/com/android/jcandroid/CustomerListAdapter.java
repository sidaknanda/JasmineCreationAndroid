package com.android.jcandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.jcandroid.Model.CustomerModel;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerListViewHolder> {

    private ArrayList<CustomerModel> customersList;
    private Context context;

    public CustomerListAdapter(Context context, ArrayList<CustomerModel> customersList) {
        this.context = context;
        this.customersList = customersList;
    }

    @Override
    public CustomerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new CustomerListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerListViewHolder holder, final int position) {
        holder.textViewName.setText(getItem(position).getName());
        holder.textViewPhoneNo.setText(getItem(position).getPhone());
        holder.cardViewEmployeeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerDetailActivity.class);
                intent.putExtra(Utility.SELECTED_CUSTOMER, getItem(position));
                context.startActivity(intent);
            }
        });
    }

    public CustomerModel getItem(int position) {
        return customersList.get(position);
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }

    public class CustomerListViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewEmployeeDetail;
        TextView textViewName;
        TextView textViewPhoneNo;

        public CustomerListViewHolder(View itemView) {

            super(itemView);
            cardViewEmployeeDetail = (CardView) itemView.findViewById(R.id.card_view_detail);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPhoneNo = (TextView) itemView.findViewById(R.id.textViewPhone);
        }
    }
}

package com.example.ehotelmanagerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterData1 extends RecyclerView.Adapter<AdapterData1.ViewDataHolder1> {

    private ArrayList<CustomerData> list1;

    public AdapterData1(ArrayList<CustomerData> list1) {
        this.list1 = list1;
    }

    @NonNull
    @Override
    public AdapterData1.ViewDataHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customerentry, parent, false);
        return new AdapterData1.ViewDataHolder1(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData1.ViewDataHolder1 holder, int position) {
        CustomerData customerData = list1.get(position);
        holder.fullName.setText(customerData.getFullName());
        holder.Email.setText(customerData.getEmail());
        holder.Password.setText(customerData.getPassword());
        holder.Phone.setText(customerData.getPhone());
        holder.Address.setText(customerData.getAddress());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public static class ViewDataHolder1 extends RecyclerView.ViewHolder{

        TextView fullName, Email, Password, Phone, Address;
        public ViewDataHolder1(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            Email = itemView.findViewById(R.id.Email);
            Password = itemView.findViewById(R.id.Password);
            Phone = itemView.findViewById(R.id.Phone);
            Address = itemView.findViewById(R.id.Address);
        }
    }

}

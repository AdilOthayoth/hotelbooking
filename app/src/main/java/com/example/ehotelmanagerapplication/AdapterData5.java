package com.example.ehotelmanagerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterData5 extends RecyclerView.Adapter<AdapterData5.ViewHolder5> {

    private ArrayList<PaymentData> list;
    private Context context;

    public AdapterData5(ArrayList<PaymentData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterData5.ViewHolder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentinfo, parent, false);
        return new AdapterData5.ViewHolder5(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData5.ViewHolder5 holder, int position) {
        PaymentData paymentData = list.get(position);
        holder.customerName.setText(paymentData.getCustomerName());
        holder.Email.setText(paymentData.getEmail());
        holder.roomName.setText(paymentData.getRoomName());
        holder.Phone.setText(paymentData.getPhone());
        holder.numberOfRooms.setText(paymentData.getNumberOfRooms());

        holder.stayDuration.setText(paymentData.getStayDuration());
        holder.totalCost.setText(paymentData.getTotalCost());
        holder.startDate.setText(paymentData.getStartDate());
        holder.endDate.setText(paymentData.getEndDate());
        holder.paymentStatus.setText(paymentData.getPaymentStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder5 extends RecyclerView.ViewHolder{

        TextView customerName, Email, roomName, Phone, numberOfRooms, stayDuration, totalCost, startDate, endDate, paymentStatus;
        public ViewHolder5(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerName);
            Email = itemView.findViewById(R.id.Email);
            roomName = itemView.findViewById(R.id.roomName);
            Phone = itemView.findViewById(R.id.Phone);
            numberOfRooms = itemView.findViewById(R.id.numberOfRooms);
            stayDuration = itemView.findViewById(R.id.stayDuration);
            totalCost = itemView.findViewById(R.id.totalCost);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            paymentStatus = itemView.findViewById(R.id.paymentStatus);
        }
    }

}

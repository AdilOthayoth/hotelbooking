package com.example.ehotelmanagerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDataHome extends RecyclerView.Adapter<AdapterDataHome.ViewDataHolder> {

    private ArrayList<ComplaintData> list1;

    public AdapterDataHome(ArrayList<ComplaintData> list1) {
        this.list1 = list1;
    }

    @NonNull
    @Override
    public ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaintentry, parent, false);
        return new ViewDataHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataHome.ViewDataHolder holder, int position) {
        ComplaintData rescue = list1.get(position);
        holder.fullName.setText(rescue.getFullName());
        holder.Email.setText(rescue.getEmail());
        holder.complaintDetail.setText(rescue.getComplaintDetail());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public static class ViewDataHolder extends RecyclerView.ViewHolder{

        TextView fullName, Email, complaintDetail;
        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            Email = itemView.findViewById(R.id.Email);
            complaintDetail = itemView.findViewById(R.id.complaintDetail);

        }
    }



}

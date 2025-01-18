package com.example.ehotelmanagerapplication;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolder> {

    private ArrayList<RoomDatainfo> list;
    private Context context;

    public AdapterData(Context context, ArrayList<RoomDatainfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roominfo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomDatainfo roomDatainfo = list.get(position);


        holder.room_Name.setText(roomDatainfo.getRoom_name());
        String mainPrice = "£ " + roomDatainfo.getRoom_price() + " (per night)";
        holder.room_Price.setText(mainPrice);
        String mainNumRooms = "Room Left: " + roomDatainfo.getNum_room() + " (" + roomDatainfo.getRoom_status() + ")";
        holder.num_Room.setText(mainNumRooms);


        String imageUrl = roomDatainfo.getImage_url();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .into(holder.image_Url);
        }


        holder.bookButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, CustomerLogin.class);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView room_Name, room_Price, num_Room;
        Button bookButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Url = itemView.findViewById(R.id.image_Url);
            room_Name = itemView.findViewById(R.id.room_Name);
            room_Price = itemView.findViewById(R.id.room_Price);
            num_Room = itemView.findViewById(R.id.num_Room);
            bookButton = itemView.findViewById(R.id.bookButton);
        }
    }


}

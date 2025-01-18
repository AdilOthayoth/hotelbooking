package com.example.ehotelmanagerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterData2 extends RecyclerView.Adapter<AdapterData2.ViewHolder2> {

    private ArrayList<RoomDatainfo> list;
    private Context context;

    public AdapterData2(Context context, ArrayList<RoomDatainfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterData2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roominfo1, parent, false);
        return new AdapterData2.ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData2.ViewHolder2 holder, int position) {
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView room_Name, room_Price, num_Room;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            image_Url = itemView.findViewById(R.id.image_Url);
            room_Name = itemView.findViewById(R.id.room_Name);
            room_Price = itemView.findViewById(R.id.room_Price);
            num_Room = itemView.findViewById(R.id.num_Room);
        }
    }

}

package com.example.ehotelmanagerapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdapterData3 extends RecyclerView.Adapter<AdapterData3.ViewHolder3> {


    private ArrayList<RoomDatainfo> list;
    private Context context;

    public AdapterData3(Context context, ArrayList<RoomDatainfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterData3.ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roominfo2, parent, false);
        return new AdapterData3.ViewHolder3(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData3.ViewHolder3 holder, int position) {
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


        holder.updateButton.setOnClickListener(v -> showCustomerNameDialog(roomDatainfo));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder3 extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView room_Name, room_Price, num_Room;
        Button updateButton;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            image_Url = itemView.findViewById(R.id.image_Url);
            room_Name = itemView.findViewById(R.id.room_Name);
            room_Price = itemView.findViewById(R.id.room_Price);
            num_Room = itemView.findViewById(R.id.num_Room);
            updateButton = itemView.findViewById(R.id.updateButton);
        }
    }

    private void showCustomerNameDialog(RoomDatainfo roomDatainfo) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Room Status");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 20);


        final EditText roomStatusInput = new EditText(context);
        roomStatusInput.setHint("Enter New Room Status");
        layout.addView(roomStatusInput);

        builder.setView(layout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String roomStatus = roomStatusInput.getText().toString().trim();


            if (!roomStatus.isEmpty()) {

                    showBookingPopup(roomDatainfo, roomStatus);

            } else {
                Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showBookingPopup(RoomDatainfo roomDatainfo, String roomStatus) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Room Status Detail");


        String message = "New Room Status\n\n" +
                "Room Name: " + roomDatainfo.getRoom_name() + "\n" +
                "Old Status: " + roomDatainfo.getRoom_status() + "\n" +
                "New Status: " + roomStatus;

        builder.setMessage(message);



        builder.setNegativeButton("UPDATE", (dialog, which) -> {
            updateRoomStatus(roomDatainfo.getRoom_name(), roomStatus);

            dialog.dismiss();


        });

        builder.setPositiveButton("CANCEL", (dialog, which) -> {

            dialog.dismiss();
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void updateRoomStatus(String roomName, String newStatus) {
        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference("Room");


        roomRef.orderByChild("room_name").equalTo(roomName).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    snapshot.getRef().child("room_status").setValue(newStatus).addOnCompleteListener(updateTask -> {
                        if (updateTask.isSuccessful()) {
                            Toast.makeText(context, "Room status updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to update room status", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Failed to retrieve room data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

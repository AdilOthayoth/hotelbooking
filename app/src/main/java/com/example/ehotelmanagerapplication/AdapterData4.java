package com.example.ehotelmanagerapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import java.util.HashMap;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterData4 extends RecyclerView.Adapter<AdapterData4.ViewHolder4> {

    private ArrayList<RoomDatainfo> list;
    private Context context;

    public AdapterData4(Context context, ArrayList<RoomDatainfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterData4.ViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roominfo3, parent, false);
        return new AdapterData4.ViewHolder4(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData4.ViewHolder4 holder, int position) {
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


        holder.bookButton.setOnClickListener(v -> showDateRangePicker(roomDatainfo));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder4 extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView room_Name, room_Price, num_Room;
        Button bookButton;

        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            image_Url = itemView.findViewById(R.id.image_Url);
            room_Name = itemView.findViewById(R.id.room_Name);
            room_Price = itemView.findViewById(R.id.room_Price);
            num_Room = itemView.findViewById(R.id.num_Room);
            bookButton = itemView.findViewById(R.id.bookButton);
        }
    }

    private void showDateRangePicker(RoomDatainfo roomDatainfo) {

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());


        MaterialDatePicker.Builder<androidx.core.util.Pair<Long, Long>> builder =
                MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select From-To Dates");
        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<androidx.core.util.Pair<Long, Long>> dateRangePicker = builder.build();
        dateRangePicker.show(((AppCompatActivity) context).getSupportFragmentManager(), "DATE_PICKER");


        dateRangePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String startDate = sdf.format(selection.first);
            String endDate = sdf.format(selection.second);


            showCustomerNameDialog(roomDatainfo, startDate, endDate);
        });
    }

    private void showCustomerNameDialog(RoomDatainfo roomDatainfo, String startDate, String endDate) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter Booking Details");


        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 20);


        final EditText customerNameInput = new EditText(context);
        customerNameInput.setHint("Enter Your Name");
        layout.addView(customerNameInput);


        final EditText numRoomsInput = new EditText(context);
        numRoomsInput.setHint("Enter Number of Rooms");
        numRoomsInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        layout.addView(numRoomsInput);

        final EditText emailInput = new EditText(context);
        emailInput.setHint("Enter Your Email");
        emailInput.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        layout.addView(emailInput);

        final EditText phoneInput = new EditText(context);
        phoneInput.setHint("Enter Your Phone Number");
        phoneInput.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        layout.addView(phoneInput);

        builder.setView(layout);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String customerName = customerNameInput.getText().toString().trim();
            String numRooms = numRoomsInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String phone = phoneInput.getText().toString().trim();

            int availableRooms = Integer.parseInt(roomDatainfo.getNum_room());
            if (!customerName.isEmpty() && !numRooms.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                int requestedRooms = Integer.parseInt(numRooms);
                if (requestedRooms > 0 && requestedRooms <= availableRooms) {

                    showBookingPopup(roomDatainfo, startDate, endDate, customerName, requestedRooms, email, phone);
                } else {
                    Toast.makeText(context, "Invalid number of rooms. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Please fill in all details", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showBookingPopup(RoomDatainfo roomDatainfo, String startDate, String endDate, String customerName, int requestedRooms, String email, String phone) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        long stayDuration = 0;
        try {
            long startMillis = sdf.parse(startDate).getTime();
            long endMillis = sdf.parse(endDate).getTime();
            stayDuration = (endMillis - startMillis) / (1000 * 60 * 60 * 24); // Convert milliseconds to days
        } catch (Exception e) {
            e.printStackTrace();
        }

        int roomPrice = Integer.parseInt(roomDatainfo.getRoom_price());
        long totalCost = stayDuration * requestedRooms * roomPrice;


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("My Room Booking Details");


        String message = "Purchase Details\n\n" +
                "Customer Name: " + customerName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Room Name: " + roomDatainfo.getRoom_name() + "\n" +
                "Price per night: £" + roomPrice + "\n" +
                "Number of Rooms: " + requestedRooms + "\n" +
                "Stay date from " + startDate + " to " + endDate + "\n" +
                "Stay Duration: " + stayDuration + " nights\n" +
                "Total Cost: £" + totalCost;

        builder.setMessage(message);



        builder.setNegativeButton("PAY AND CHECK OUT", (dialog, which) -> {
            saveBookingToDatabase(customerName, startDate, endDate, roomDatainfo, requestedRooms, totalCost, email, phone);

            Intent intent = new Intent(context, CheckoutPage.class);
            context.startActivity(intent);


            if (context instanceof AppCompatActivity) {
                ((AppCompatActivity) context).finish();
            }


        });

        builder.setPositiveButton("CANCEL", (dialog, which) -> {

            dialog.dismiss();
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveBookingToDatabase(String customerName, String startDate, String endDate, RoomDatainfo roomDatainfo, int requestedRooms, long totalCost, String email, String phone) {

        DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("Booking");


        String bookingId = bookingRef.push().getKey();

        String paymentStatus = "Paid";


        HashMap<String, String> bookingData = new HashMap<>();
        bookingData.put("CustomerName", customerName);
        bookingData.put("Email", email);
        bookingData.put("Phone", phone);
        bookingData.put("RoomName", roomDatainfo.getRoom_name());
        bookingData.put("PricePerNight", roomDatainfo.getRoom_price());
        bookingData.put("NumberOfRooms", String.valueOf(requestedRooms));
        bookingData.put("StayDuration", String.valueOf(totalCost / (Integer.parseInt(roomDatainfo.getRoom_price()) * requestedRooms)));
        bookingData.put("TotalCost", String.valueOf(totalCost));
        bookingData.put("StartDate", startDate);
        bookingData.put("EndDate", endDate);
        bookingData.put("PaymentStatus", paymentStatus);


        if (bookingId != null) {
            bookingRef.child(bookingId).setValue(bookingData).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    updateRoomAvailability(roomDatainfo.getRoom_name(), requestedRooms);
                    Toast.makeText(context, "Booking saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to save booking: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void updateRoomAvailability(String roomName, int requestedRooms) {

        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference("Room");


        roomRef.orderByChild("room_name").equalTo(roomName).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String currentNumRooms = snapshot.child("num_room").getValue(String.class);
                    if (currentNumRooms != null) {
                        int currentRooms = Integer.parseInt(currentNumRooms);
                        int updatedRooms = currentRooms - requestedRooms;


                        snapshot.getRef().child("num_room").setValue(String.valueOf(updatedRooms)).addOnCompleteListener(updateTask -> {
                            if (updateTask.isSuccessful()) {

                                Toast.makeText(context, "Room availability updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Failed to update room availability", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Failed to retrieve room data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

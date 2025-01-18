package com.example.ehotelmanagerapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookRoom extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RoomDatainfo> list;
    DatabaseReference databaseReference;
    AdapterData4 adapterData4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_room);

        recyclerView = findViewById(R.id.view_data1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        adapterData4 = new AdapterData4(this, list);
        recyclerView.setAdapter(adapterData4);


        databaseReference = FirebaseDatabase.getInstance().getReference("Room");


        fetchRooms();

    }

    private void fetchRooms() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists() && snapshot.hasChildren()) {
                    for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                        RoomDatainfo roomDatainfo = noteSnapshot.getValue(RoomDatainfo.class);
                        if (roomDatainfo != null) {
                            list.add(roomDatainfo);
                        }
                    }
                    adapterData4.notifyDataSetChanged();

                } else {
                    Toast.makeText(BookRoom.this, "No room present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BookRoom.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
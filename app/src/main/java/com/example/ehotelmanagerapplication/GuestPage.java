package com.example.ehotelmanagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class GuestPage extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RoomDatainfo> list;
    DatabaseReference databaseReference;
    AdapterData adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_page);

        recyclerView = findViewById(R.id.view_data1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        adapterData = new AdapterData(this, list);
        recyclerView.setAdapter(adapterData);


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
                    adapterData.notifyDataSetChanged();

                } else {
                    Toast.makeText(GuestPage.this, "No room present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GuestPage.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
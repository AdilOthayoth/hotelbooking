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

public class ViewComplaint extends AppCompatActivity {

    RecyclerView recyclerView1;
    ArrayList<ComplaintData> list1;
    DatabaseReference databaseReference;

    AdapterDataHome adapterDataHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_complaint);

        recyclerView1 = findViewById(R.id.view_data1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        list1 = new ArrayList<>();
        adapterDataHome = new AdapterDataHome(list1);
        recyclerView1.setAdapter(adapterDataHome);

        databaseReference = FirebaseDatabase.getInstance().getReference("Complaint");

        fetchComplaintData();


    }

    private void fetchComplaintData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1.clear();
                if (snapshot.exists() && snapshot.hasChildren()) {
                    for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                        ComplaintData complaintData = noteSnapshot.getValue(ComplaintData.class);
                        if (complaintData != null) {
                            list1.add(complaintData);
                        }
                    }
                    adapterDataHome.notifyDataSetChanged();
                } else {

                    Toast.makeText(ViewComplaint.this, "No data present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewComplaint.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
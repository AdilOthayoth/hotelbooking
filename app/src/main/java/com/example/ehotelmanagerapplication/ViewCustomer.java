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

public class ViewCustomer extends AppCompatActivity {

    RecyclerView recyclerView1;
    ArrayList<CustomerData> list1;
    DatabaseReference databaseReference;

    AdapterData1 adapterData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_customer);

        recyclerView1 = findViewById(R.id.view_data1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        list1 = new ArrayList<>();
        adapterData1 = new AdapterData1(list1);
        recyclerView1.setAdapter(adapterData1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");

        fetchCustomerData();

    }

    private void fetchCustomerData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1.clear();
                if (snapshot.exists() && snapshot.hasChildren()) {
                    for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                        CustomerData customerData = noteSnapshot.getValue(CustomerData.class);
                        if (customerData != null) {
                            list1.add(customerData);
                        }
                    }
                    adapterData1.notifyDataSetChanged();
                } else {

                    Toast.makeText(ViewCustomer.this, "No data present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewCustomer.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
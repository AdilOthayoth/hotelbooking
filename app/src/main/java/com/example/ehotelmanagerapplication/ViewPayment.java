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

public class ViewPayment extends AppCompatActivity {

    RecyclerView recyclerView1;
    ArrayList<PaymentData> list;
    DatabaseReference databaseReference;

    AdapterData5 adapterData5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_payment);

        recyclerView1 = findViewById(R.id.view_data1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterData5 = new AdapterData5(list);
        recyclerView1.setAdapter(adapterData5);

        databaseReference = FirebaseDatabase.getInstance().getReference("Booking");

        fetchPaymentData();

    }

    private void fetchPaymentData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists() && snapshot.hasChildren()) {
                    for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
                        PaymentData paymentData = noteSnapshot.getValue(PaymentData.class);
                        if (paymentData != null) {
                            list.add(paymentData);
                        }
                    }
                    adapterData5.notifyDataSetChanged();
                } else {

                    Toast.makeText(ViewPayment.this, "No data present", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewPayment.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
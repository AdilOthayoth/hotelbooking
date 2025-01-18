package com.example.ehotelmanagerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Complain extends AppCompatActivity {

    private EditText fullname_data, email_data, desc_val_data;
    private Button subButton;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complain);

        fullname_data = findViewById(R.id.full_name);
        email_data = findViewById(R.id.email);
        desc_val_data = findViewById(R.id.desc_val);
        subButton = findViewById(R.id.sub_button);

        databaseRef = FirebaseDatabase.getInstance().getReference("Complaint");

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subComplaint();
            }
        });

    }

    private void subComplaint() {
        String name = fullname_data.getText().toString().trim();
        String eml = email_data.getText().toString().trim();
        String desc = desc_val_data.getText().toString().trim();

        if (!name.isEmpty() && !eml.isEmpty() && !desc.isEmpty()) {

            String userId = databaseRef.push().getKey();
            databaseRef.child(userId).child("FullName").setValue(name);
            databaseRef.child(userId).child("Email").setValue(eml);
            databaseRef.child(userId).child("ComplaintDetail").setValue(desc);

            Toast.makeText(this, "Complaint Submitted Successfully", Toast.LENGTH_SHORT).show();

            fullname_data.setText("");
            email_data.setText("");
            desc_val_data.setText("");

            Intent intent = new Intent(Complain.this, CustomerHome.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

}
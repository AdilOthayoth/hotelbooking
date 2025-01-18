package com.example.ehotelmanagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegistration extends AppCompatActivity {

    private EditText fullname_data, email_data, password_data, phone_data, address_data;
    private Button regButton, backButton;
    private DatabaseReference databaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        fullname_data = findViewById(R.id.full_name);
        email_data = findViewById(R.id.email);
        password_data = findViewById(R.id.password);
        phone_data = findViewById(R.id.phone);
        address_data = findViewById(R.id.address);
        regButton = findViewById(R.id.reg_button);
        backButton = findViewById(R.id.back_button);

        databaseRef = FirebaseDatabase.getInstance().getReference("Customer");

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCustomer();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerRegistration.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerCustomer() {
        String name = fullname_data.getText().toString().trim();
        String eml = email_data.getText().toString().trim();
        String pass = password_data.getText().toString().trim();
        String ph = phone_data.getText().toString().trim();
        String add = address_data.getText().toString().trim();

        if (!name.isEmpty() && !eml.isEmpty() && !pass.isEmpty() && !ph.isEmpty() && !add.isEmpty()) {

            String userId = databaseRef.push().getKey();
            databaseRef.child(userId).child("FullName").setValue(name);
            databaseRef.child(userId).child("Email").setValue(eml);
            databaseRef.child(userId).child("Password").setValue(pass);
            databaseRef.child(userId).child("Phone").setValue(ph);
            databaseRef.child(userId).child("Address").setValue(add);

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

            fullname_data.setText("");
            email_data.setText("");
            password_data.setText("");
            phone_data.setText("");
            address_data.setText("");

            Intent intent = new Intent(CustomerRegistration.this, CustomerLogin.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }


}
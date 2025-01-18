package com.example.ehotelmanagerapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerLogin extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    private Button bBtn;

    private Button bBtn1;

    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        bBtn = findViewById(R.id.login_button);
        bBtn1 = findViewById(R.id.reg_button);

        databaseRef = FirebaseDatabase.getInstance().getReference("Customer");

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        bBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogin.this, CustomerRegistration.class);
                startActivity(intent);
            }
        });

    }

    private void checkLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(CustomerLogin.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }


        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean found = false;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String dbEmail = dataSnapshot.child("Email").getValue(String.class);
                    String dbPassword = dataSnapshot.child("Password").getValue(String.class);

                    if (dbEmail != null && dbPassword != null && dbEmail.equals(email) && dbPassword.equals(password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    Intent intent = new Intent(CustomerLogin.this, CustomerHome.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(CustomerLogin.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();

                    emailEditText.setText("");
                    passwordEditText.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerLogin.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
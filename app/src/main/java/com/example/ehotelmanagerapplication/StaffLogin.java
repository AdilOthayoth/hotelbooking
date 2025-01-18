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
import com.google.firebase.database.ValueEventListener;

public class StaffLogin extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    private Button bBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        bBtn = findViewById(R.id.login_button);

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    private void checkLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(StaffLogin.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.equals("admin@gmail.com") && password.equals("12345678")) {
            Intent intent = new Intent(StaffLogin.this, StaffHome.class);
            startActivity(intent);
        } else {
            Toast.makeText(StaffLogin.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();

            emailEditText.setText("");
            passwordEditText.setText("");
        }
    }

}
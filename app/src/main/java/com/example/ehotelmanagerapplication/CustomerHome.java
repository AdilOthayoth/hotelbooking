package com.example.ehotelmanagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerHome extends AppCompatActivity {

    private Button logoutBtn, nxtBtn, nxtBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        nxtBtn1 = findViewById(R.id.next);
        nxtBtn = findViewById(R.id.next1);
        logoutBtn = findViewById(R.id.logout);

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHome.this, Complain.class);
                startActivity(intent);
            }
        });

        nxtBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHome.this, BookRoom.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
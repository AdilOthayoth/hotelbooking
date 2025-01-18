package com.example.ehotelmanagerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffHome extends AppCompatActivity {

    private Button logoutBtn, nxtBtn, nxtBtn1, nxtBtn2, nxtBtn3, nxtBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        nxtBtn4 = findViewById(R.id.next);
        nxtBtn3 = findViewById(R.id.next1);
        nxtBtn2 = findViewById(R.id.next4);
        nxtBtn1 = findViewById(R.id.next3);
        nxtBtn = findViewById(R.id.next2);
        logoutBtn = findViewById(R.id.logout);

        nxtBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, ViewPayment.class);
                startActivity(intent);
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, ViewComplaint.class);
                startActivity(intent);
            }
        });

        nxtBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, ViewCustomer.class);
                startActivity(intent);
            }
        });

        nxtBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, NumRoom.class);
                startActivity(intent);
            }
        });

        nxtBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, CheckRoom.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHome.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
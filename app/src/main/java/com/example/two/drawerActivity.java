package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

public class drawerActivity extends AppCompatActivity {

    Button btnPay;
    Button btnId;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        btnId = findViewById(R.id.btnId);
        btnPay = findViewById(R.id.btnPay);
    }
}
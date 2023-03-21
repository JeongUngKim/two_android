package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;

import com.example.two.model.MessageItem;

import java.util.HashMap;
import java.util.HashSet;

public class drawerActivity extends AppCompatActivity {

    Button btnPay;
    Button btnId;



    @SuppressLint("MissingInflatedId")
    HashSet<HashMap<String,String>> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        btnId = findViewById(R.id.btnId);
        btnPay = findViewById(R.id.btnPay);
        set = ((PartyChatActivity)PartyChatActivity.context).set;


    }
}
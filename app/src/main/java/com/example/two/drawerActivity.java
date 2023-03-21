package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.example.two.adapter.DrawerAdapter;
import com.example.two.model.MessageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class drawerActivity extends AppCompatActivity {

    Button btnPay;
    Button btnId;

    RecyclerView recyclerView;
    DrawerAdapter Draweradapter;

    @SuppressLint("MissingInflatedId")
    HashSet<HashMap<String,String>> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        btnId = findViewById(R.id.btnId);
        btnPay = findViewById(R.id.btnPay);
        recyclerView = findViewById(R.id.drawerRecyclerView);
        set = ((PartyChatActivity)PartyChatActivity.context).set;

        ArrayList<HashMap<String,String>> dataset = new ArrayList<>();
        for (HashMap<String,String> data : set){
            dataset.add(data);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(drawerActivity.this));

        Draweradapter = new DrawerAdapter(drawerActivity.this,dataset);
        recyclerView.setAdapter(Draweradapter);

        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test213213","asdasd");
            }
        });

    }
}
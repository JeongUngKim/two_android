package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.two.model.MessageItem;

import java.util.HashMap;
import java.util.HashSet;

public class drawerActivity extends AppCompatActivity {

    HashSet<HashMap<String,String>> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        set = ((PartyChatActivity)PartyChatActivity.context).set;
    }
}
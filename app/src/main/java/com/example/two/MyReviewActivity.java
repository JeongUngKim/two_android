package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyReviewActivity extends AppCompatActivity {

    Button btnCommunity;
    Button btnHome;
    Button btnFilter;
    Button btnParty;
    Button btnMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        btnCommunity = findViewById(R.id.btnCommunity);
        btnHome = findViewById(R.id.btnHome);
        btnParty = findViewById(R.id.btnParty);
        btnFilter = findViewById(R.id.btnFilter);
        btnMy = findViewById(R.id.btnMy);

        // 메인 액티비티 넘어가기
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReviewActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 커뮤니티 액티비티 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReviewActivity.this,CommunityActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 필터검색 액티비티 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReviewActivity.this,FilterSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 파티매칭 액티비티 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReviewActivity.this,PartyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 내정보 액티비티 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyReviewActivity.this,MyMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
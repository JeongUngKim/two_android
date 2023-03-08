package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyMenuActivity extends AppCompatActivity {

    Button btnCommunity;
    Button btnHome;
    Button btnFilter;
    Button btnParty;
    Button btnMy;

    CardView cvChoice;
    CardView cvDisLike;
    CardView cvMyReview;
    CardView cvUseOTT;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        cvChoice = findViewById(R.id.cvChoice);
        cvDisLike = findViewById(R.id.cvDisLike);
        cvMyReview = findViewById(R.id.cvMyReview);
        cvUseOTT = findViewById(R.id.cvUseOTT);

        btnCommunity = findViewById(R.id.btnCommunity);
        btnHome = findViewById(R.id.btnHome);
        btnParty = findViewById(R.id.btnParty);
        btnFilter = findViewById(R.id.btnFilter);
        btnMy = findViewById(R.id.btnMy);

        // 메인 액티비티 넘어가기
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 커뮤니티 액티비티 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,CommunityActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 필터검색 액티비티 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,FilterSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 파티매칭 액티비티 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,PartyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 찜한 작품 관리 넘어가기
        cvChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,ChoiceActivity.class);
                startActivity(intent);
            }
        });

        // 관심없는 작품 관리 넘어가기
        cvDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,DisLikeActivity.class);
                startActivity(intent);
            }
        });

        // 작성한 리뷰 관리 넘어가기
        cvMyReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,MyReviewActivity.class);
                startActivity(intent);
            }
        });

        // 이용중인 서비스 넘어가기
        cvUseOTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenuActivity.this,UseOTTActivity.class);
                startActivity(intent);
            }
        });
    }
}
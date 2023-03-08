package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MovieContentActivity extends AppCompatActivity {

    Button btnChoice;
    Button btnDisLike;
    Button btnReview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_content);

        btnChoice = findViewById(R.id.btnChoice);
        btnDisLike = findViewById(R.id.btnDisLike);
        btnReview = findViewById(R.id.btnReview);

        // 찜했어요 버튼 클릭 처리
        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 관심없어요 버튼 클릭 처리
        btnDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // 리뷰작성 버튼 클릭 처리
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ReviewAdd 액티비티로 이동
                Intent intent = new Intent(MovieContentActivity.this,ReviewAddActivity.class);
                startActivity(intent);

            }
        });
    }
}
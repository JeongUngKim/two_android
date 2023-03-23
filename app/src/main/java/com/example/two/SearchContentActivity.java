package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SearchContentActivity extends AppCompatActivity {
    ImageView posterView;

    TextView txtTitle;
    TextView txtContent;
    TextView txtDate;
    TextView txtRate;

    int Id;
    String title;
    String date;
    String content;
    String rating;
    String imgurl;

    String gerne;

    ImageButton btnChoice;
    ImageButton btnReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_content);

        posterView = findViewById(R.id.posterView);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);
        txtDate = findViewById(R.id.txtDate);
        txtRate = findViewById(R.id.txtRate);
        btnChoice = findViewById(R.id.btnChoice);
        btnReview = findViewById(R.id.btnReview);

        Id = getIntent().getIntExtra("Id",0);
        title =getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("year");
        content = getIntent().getStringExtra("content");
        rating = getIntent().getStringExtra("rating");
        imgurl = getIntent().getStringExtra("ImgUrl");
        gerne = getIntent().getStringExtra("genre");

        txtTitle.setText(title);
        txtDate.setText(date);
        txtContent.setText(content);
        txtRate.setText(rating);
        Glide.with(SearchContentActivity.this)
                .load(imgurl)
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(posterView);

        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLike();
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchContentActivity.this,ReviewAddActivity.class);
                intent.putExtra("Id",Id);
                intent.putExtra("title",title);
                intent.putExtra("date",date);
                intent.putExtra("imgUrl",imgurl);
                intent.putExtra("genre",gerne);
                startActivity(intent);
            }
        });

    }

    public void isLike(){
        //되라라
    }


}
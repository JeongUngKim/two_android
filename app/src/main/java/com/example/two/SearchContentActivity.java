package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.two.Api.ChatApi;
import com.example.two.Api.ContentApi;
import com.example.two.Api.NetworkClient2;
import com.example.two.Api.ReviewApi;
import com.example.two.adapter.ReviewAdapter;
import com.example.two.config.Config;
import com.example.two.model.ChatRoomList;
import com.example.two.model.Res;
import com.example.two.model.reView;
import com.example.two.model.reViewList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    String token;

    ImageButton btnChoice;
    ImageButton btnReview;

    FrameLayout frame1;

    RecyclerView recyclerView;

    int count=0;

    ReviewAdapter adapter;

    ArrayList<reView> reViewArrayList = new ArrayList<>();
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
        frame1= findViewById(R.id.frame1);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchContentActivity.this));

        Id = getIntent().getIntExtra("Id",0);
        title =getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("year");
        content = getIntent().getStringExtra("content");
        rating = getIntent().getStringExtra("rating");
        imgurl = getIntent().getStringExtra("ImgUrl");
        gerne = getIntent().getStringExtra("genre");

        getReview(Id);
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

                    if (count == 0) {

                        isLike();
                        Toast.makeText(SearchContentActivity.this, "찜했습니다", Toast.LENGTH_SHORT).show();
                        frame1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF737E")));
                        btnChoice.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF737E")));
                        btnChoice.setImageResource(R.drawable.baseline_bookmark_24);
                        count = 1;

                    }else{

                        disLike();
                        Toast.makeText(SearchContentActivity.this, "찜취소했습니다", Toast.LENGTH_SHORT).show();
                        frame1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                        btnChoice.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                        btnChoice.setImageResource(R.drawable.baseline_bookmark_border_24);
                        count=0;
                    }


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
        Retrofit retrofit = NetworkClient2.getRetrofitClient(SearchContentActivity.this);

        ContentApi api = retrofit.create(ContentApi.class);

        Log.i("AAA", api.toString());
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");
        Call<Res> call =api.contentLike("Bearer "+token,Id);

        call.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                if (response.isSuccessful()) {
                    return;



                } else {
                    Toast.makeText(SearchContentActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {

            }
        });
    }

    public void disLike(){

        Retrofit retrofit = NetworkClient2.getRetrofitClient(SearchContentActivity.this);

        ContentApi api = retrofit.create(ContentApi.class);

        Log.i("AAA", api.toString());
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");
        Call<Res> call =api.contentDisLike("Bearer "+token,Id);

        call.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                if (response.isSuccessful()) {
                    return;



                } else {
                    Toast.makeText(SearchContentActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {

            }
        });



    }

    public void getReview(int id){
        Retrofit retrofit = NetworkClient2.getRetrofitClient(SearchContentActivity.this);

        ReviewApi api = retrofit.create(ReviewApi.class);

        Call<reViewList> call = api.getReview(Id,0);

        call.enqueue(new Callback<reViewList>() {
            @Override
            public void onResponse(Call<reViewList> call, Response<reViewList> response) {

                reViewArrayList.clear();

                reViewArrayList.addAll(response.body().getContentReviewList());

                adapter = new ReviewAdapter(SearchContentActivity.this,reViewArrayList);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<reViewList> call, Throwable t) {

            }
        });
    }


}
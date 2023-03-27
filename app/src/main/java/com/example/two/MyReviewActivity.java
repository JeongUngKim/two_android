package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.two.Api.NetworkClient2;
import com.example.two.Api.ReviewApi;
import com.example.two.adapter.ReviewAdapter;
import com.example.two.config.Config;
import com.example.two.model.MyReviewList;
import com.example.two.model.reView;
import com.example.two.model.reViewList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<reView> reViewArrayList = new ArrayList<>();

    String token;

    ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyReviewActivity.this));
        getMyreview();


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }

    public void getMyreview(){
        Retrofit retrofit = NetworkClient2.getRetrofitClient(MyReviewActivity.this);

        ReviewApi api = retrofit.create(ReviewApi.class);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");
        Call<MyReviewList> call = api.myReview("Bearer "+token,0);

        call.enqueue(new Callback<MyReviewList>() {
            @Override
            public void onResponse(Call<MyReviewList> call, Response<MyReviewList> response) {

                reViewArrayList.clear();

                reViewArrayList.addAll(response.body().getReviewList());

                adapter = new ReviewAdapter(MyReviewActivity.this,reViewArrayList);

                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MyReviewList> call, Throwable t) {
                Log.i("원인", String.valueOf(t));
            }
        });
    }
}
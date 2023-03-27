package com.example.two;

import androidx.annotation.NonNull;
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

    int page=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyReviewActivity.this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 맨 마지막 데이터가 화면에 보이면!!!!
                // 네트워크 통해서 데이터를 추가로 받아와라!!
                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                // 스크롤을 데이터 맨 끝까지 한 상태.
                if (lastPosition + 1 == totalCount) {
                    // 네트워크 통해서 데이터를 받아오고, 화면에 표시!

                    addgetMyreview();

                }
            }
        });

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

    public void addgetMyreview(){
        Retrofit retrofit = NetworkClient2.getRetrofitClient(MyReviewActivity.this);

        ReviewApi api = retrofit.create(ReviewApi.class);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");
        Call<MyReviewList> call = api.myReview("Bearer "+token,page+1);

        call.enqueue(new Callback<MyReviewList>() {
            @Override
            public void onResponse(Call<MyReviewList> call, Response<MyReviewList> response) {



                reViewArrayList.addAll(response.body().getReviewList());

                adapter = new ReviewAdapter(MyReviewActivity.this,reViewArrayList);

                recyclerView.setAdapter(adapter);
                page=page+1;

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MyReviewList> call, Throwable t) {
                Log.i("원인", String.valueOf(t));
            }
        });
    }
}
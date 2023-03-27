package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.two.Api.ContentApi;
import com.example.two.Api.NetworkClient2;
import com.example.two.Api.ReviewApi;
import com.example.two.config.Config;
import com.example.two.model.Res;
import com.example.two.model.reView;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReviewUpdateActivity extends AppCompatActivity {

    TextView reviewTitle;
    EditText editContent;

    Button btnSave;
    Button btnDelete;

    RatingBar ratingBar4;

    int Id;
    int contentId;

    int reviewId;
    String title;
    String content;

    String reviewContent;

    String token;

    float rating;
    float ratingscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_update);

        reviewTitle = findViewById(R.id.reviewTitle);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        ratingBar4 = findViewById(R.id.ratingBar4);
        Id = getIntent().getIntExtra("Id",0);
        contentId = getIntent().getIntExtra("contentId",0);
        reviewId = getIntent().getIntExtra("reviewId",0);
        Log.i("NUMBER",String.valueOf(reviewId));
        title =getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        rating = getIntent().getFloatExtra("rating",0);

        reviewTitle.setText(title);
        editContent.setText(content);
        ratingBar4.setRating(rating);
        ratingBar4.setOnRatingBarChangeListener(new ReviewUpdateActivity.Listener());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewContent = editContent.getText().toString().trim();

                reviewUpdate(reviewContent);

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviewDelete();

                finish();
            }
        });


    }

    class Listener implements RatingBar.OnRatingBarChangeListener
    {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            ratingBar4.setRating(rating);
            ratingscore =rating;
            Log.i("RANK", String.valueOf(rating));

        }
    }

    public void reviewUpdate(String review){

        Map<String, String> map = new LinkedHashMap<>();
        map.put("title", title );
        map.put("content", reviewContent);
        map.put("userRating", String.valueOf(ratingscore));

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");

        Retrofit retrofit = NetworkClient2.getRetrofitClient(ReviewUpdateActivity.this);

        ReviewApi api = retrofit.create(ReviewApi.class);

        Call<reView> call =api.UpdateReview("Bearer "+token,contentId,reviewId,map);

        call.enqueue(new Callback<reView>() {
            @Override
            public void onResponse(Call<reView> call, Response<reView> response) {
                    return;
            }

            @Override
            public void onFailure(Call<reView> call, Throwable t) {
                    return;
            }
        });
    }

    public void reviewDelete(){

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        token = sp.getString("AccessToken","");

        Retrofit retrofit = NetworkClient2.getRetrofitClient(ReviewUpdateActivity.this);

        ReviewApi api = retrofit.create(ReviewApi.class);

        Call<reView> call =api.DeleteReview("Bearer "+token,contentId,reviewId);

        call.enqueue(new Callback<reView>() {
            @Override
            public void onResponse(Call<reView> call, Response<reView> response) {
                return;
            }

            @Override
            public void onFailure(Call<reView> call, Throwable t) {
                return;
            }
        });

    }
}
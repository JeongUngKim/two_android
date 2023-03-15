package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.two.Api.NetworkClient1;
import com.example.two.config.Config;
import com.example.two.config.SearchApi;
import com.example.two.model.Seach;
import com.example.two.model.SeachList;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {

    ImageButton btnCommunity;
    ImageButton btnHome;
    ImageButton btnFilter;
    ImageButton btnParty;
    ImageButton btnMy;

    ArrayList<Seach>seachArrayList1 = new ArrayList<>();

    ArrayList<Seach>seachArrayList2 = new ArrayList<>();

    ImageView poster1;
    ImageView poster2;
    ImageView poster3;
    ImageView poster4;
    ImageView poster5;

    TextView titleSearchMovie1;
    TextView titleSearchMovie2;
    TextView titleSearchMovie3;

    TextView rateSearchMovie1;
    TextView rateSearchMovie2;
    TextView rateSearchMovie3;

    TextView genreSearchMovie1;
    TextView genreSearchMovie2;
    TextView genreSearchMovie3;

    TextView dateSearchMovie1;
    TextView dateSearchMovie2;
    TextView dateSearchMovie3;

    TextView titleSearchTv1;
    TextView titleSearchTv2;

    TextView rateSearchTv1;
    TextView rateSearchTv2;

    TextView genreSearchTv1;
    TextView genreSearchTv2;

    TextView dateSearchTv1;
    TextView dateSearchTv2;

    EditText txtSearch;


    String language = "ko-KR";
    String desc = "popularity.desc";

    String Keyword;


    ImageView btntitleSearch;


    String T1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnCommunity = findViewById(R.id.btnCommunity);
        btnHome = findViewById(R.id.btnHome);
        btnParty = findViewById(R.id.btnParty);
        btnFilter = findViewById(R.id.btnFilter);
        btnMy = findViewById(R.id.btnMy);

        poster1=findViewById(R.id.poster1);
        poster2=findViewById(R.id.poster2);
        poster3=findViewById(R.id.poster3);
        poster4=findViewById(R.id.poster4);
        poster5=findViewById(R.id.poster5);

        titleSearchMovie1 = findViewById(R.id.titleSearchMovie1);
        titleSearchMovie2 = findViewById(R.id.titleSearchMovie2);
        titleSearchMovie3 = findViewById(R.id.titleSearchMovie3);

        rateSearchMovie1 = findViewById(R.id.rateSearchMovie1);
        rateSearchMovie2 = findViewById(R.id.rateSearchMovie2);
        rateSearchMovie3 = findViewById(R.id.rateSearchMovie3);

        genreSearchMovie1 = findViewById(R.id.genreSearchMovie1);
        genreSearchMovie2 = findViewById(R.id.genreSearchMovie2);
        genreSearchMovie3 = findViewById(R.id.genreSearchMovie3);

        dateSearchMovie1 = findViewById(R.id.dateSearchMovie1);
        dateSearchMovie2 = findViewById(R.id.dateSearchMovie2);
        dateSearchMovie3 = findViewById(R.id.dateSearchMovie3);

        titleSearchTv1 = findViewById(R.id.titleSearchTv1);
        titleSearchTv2 = findViewById(R.id.titleSearchTv2);

        rateSearchTv1 = findViewById(R.id.rateSearchTv1);
        rateSearchTv2 = findViewById(R.id.rateSearchTv2);

        genreSearchTv1 = findViewById(R.id.genreSearchTv1);
        genreSearchTv2 = findViewById(R.id.genreSearchTv2);

        dateSearchTv1 = findViewById(R.id.dateSearchTv1);
        dateSearchTv2 = findViewById(R.id.dateSearchTv2);

        txtSearch = findViewById(R.id.txtSearch);

        btntitleSearch = findViewById(R.id.btntitleSearch);




        btntitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyword = txtSearch.getText().toString().trim();
                getNetworkSearchMovieData(Keyword);
                getNetworkSearchTvData(Keyword);



            }
        });





        // 메인 액티비티 넘어가기
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 커뮤니티 액티비티 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,CommunityActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 검색 액티비티 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 파티매칭 액티비티 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,PartyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 내정보 액티비티 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,MyMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getNetworkSearchTvData(String Keyword) {
        Retrofit retrofit = NetworkClient1.getRetrofitClient(SearchActivity.this);

        SearchApi api = retrofit.create(SearchApi.class);

        Log.i("AAA", api.toString());

        Call<SeachList> call = api.getSeachTv(Config.key,language,Keyword);

        call.enqueue(new Callback<SeachList>() {
            @Override
            public void onResponse(Call<SeachList> call, Response<SeachList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요
                    seachArrayList1.clear();

                    // 데이터를 받았으니 리사이클러 표시

                    seachArrayList1.addAll(response.body().getResults());
                    Glide.with(SearchActivity.this)
                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList1.get(0).getPoster_path())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(poster4);
                    titleSearchTv1.setText(seachArrayList1.get(0).getName());
                    rateSearchTv1.setText(seachArrayList1.get(0).getVote_average().substring(0,2));
                    List tvgenre1 = seachArrayList1.get(0).getGenre_ids();
                    T1=String.valueOf(tvgenre1.get(0));
                    Log.i("TEN",T1);

                    switch (T1) {
                        case "10759.0":
                            genreSearchTv1.setText("액션&어드벤쳐");
                            break;
                        case "16.0":
                            genreSearchTv1.setText("애니메이션");
                            break;
                        case "35.0":
                            genreSearchTv1.setText("코미디");
                            break;
                        case "80.0":
                            genreSearchTv1.setText("범죄");
                            break;
                        case "99.0":
                            genreSearchTv1.setText("다큐멘터리");
                            break;
                        case "18.0":
                            genreSearchTv1.setText("드라마");
                            break;
                        case"10751.0":
                            genreSearchTv1.setText("가족");
                            break;
                        case"10762.0":
                            genreSearchTv1.setText("어린이");
                            break;
                        case"9648.0":
                            genreSearchTv1.setText("미스터리");
                            break;
                        case"10763.0":
                            genreSearchTv1.setText("뉴스");
                            break;
                        case "10764.0":
                            genreSearchTv1.setText("리얼리티");
                            break;
                        case"10765.0":
                            genreSearchTv1.setText("SF&판타지");
                            break;
                        case"10766.0":
                            genreSearchTv1.setText("Soap");
                            break;
                        case"10767.0":
                            genreSearchTv1.setText("토크쇼");
                            break;
                        case "10768.0":
                            genreSearchTv1.setText("전쟁&정치");
                            break;
                        case "37.0":
                            genreSearchTv1.setText("서부");
                            break;
                        case "none":
                            genreSearchTv1.setText("장르없음");
                            break;
                    }
                    //제발
//                    if (T=="10759.0"){
//                        genreSearchTv1.setText("액션&어드벤쳐");
//                    }else if (T=="16.0") {
//                        genreSearchTv1.setText("애니메이션");
//                        Log.i("TEN",T);
//                    }
//                    else if (T=="35.0") {
//                        genreSearchTv1.setText("코미디");
//                    }
//                    else if (tvgenre1.get(0)=="80") {
//                        genreSearchTv1.setText("범죄");
//                    }
//                    else if (tvgenre1.get(0)=="99") {
//                        genreSearchTv1.setText("다큐멘터리");
//                    }
//                    else if (tvgenre1.get(0)=="18") {
//                        genreSearchTv1.setText("애니메이션");
//                    }
//                    else if (tvgenre1.get(0)=="16") {
//                        genreSearchTv1.setText("드라마");
//                    }
//
//                    else if (tvgenre1.get(0)=="10751") {
//                        genreSearchTv1.setText("가족");
//                    }
//                    else if (tvgenre1.get(0)=="10762") {
//                        genreSearchTv1.setText("어린이");
//                    }
//                    else if (tvgenre1.get(0)=="9648") {
//                        genreSearchTv1.setText("뉴스");
//                    }
//                    else if (tvgenre1.get(0)=="10764") {
//                        genreSearchTv1.setText("리얼리티");
//                    }
//                    else if (tvgenre1.get(0)=="10765") {
//                        genreSearchTv1.setText("SF&판타지");
//                    }
//                    else if (tvgenre1.get(0)=="10766") {
//                        genreSearchTv1.setText("Soap");
//                    }
//                    else if (tvgenre1.get(0)=="10767") {
//                        genreSearchTv1.setText("토크쇼");
//                    }
//                    else if (tvgenre1.get(0)=="10768") {
//                        genreSearchTv1.setText("전쟁&정치");
//                    }
//                    else if (tvgenre1.get(0)=="37") {
//                        genreSearchTv1.setText("서부");
//                    }
//                    else {
//                        genreSearchTv1.setText("장르없음");
//                    }

                    dateSearchTv1.setText(seachArrayList1.get(0).getFirst_air_date().substring(0,4));

                    Glide.with(SearchActivity.this)
                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList1.get(1).getPoster_path())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(poster5);
                    titleSearchTv2.setText(seachArrayList1.get(1).getName());
                    rateSearchTv2.setText(seachArrayList1.get(1).getVote_average().substring(0,2));
                    List tvgenre2 = seachArrayList1.get(1).getGenre_ids();
                    String T2=String.valueOf(tvgenre2.get(0));
                    switch (T2) {
                        case "10759.0":
                            genreSearchTv2.setText("액션&어드벤쳐");
                            break;

                        case "16.0":
                            genreSearchTv2.setText("애니메이션");
                            break;
                        case "35.0":
                            genreSearchTv2.setText("코미디");
                            break;
                        case "80.0":
                            genreSearchTv2.setText("범죄");
                            break;
                        case "99.0":
                            genreSearchTv2.setText("다큐멘터리");
                            break;
                        case "18.0":
                            genreSearchTv2.setText("드라마");
                            break;
                        case"10751.0":
                            genreSearchTv2.setText("가족");
                            break;
                        case"10762.0":
                            genreSearchTv2.setText("어린이");
                            break;

                        case"9648.0":
                            genreSearchTv2.setText("미스터리");
                            break;
                        case"10763.0":
                            genreSearchTv2.setText("뉴스");
                            break;
                        case "10764.0":
                            genreSearchTv2.setText("리얼리티");
                            break;
                        case"10765.0":
                            genreSearchTv2.setText("SF&판타지");
                            break;
                        case"10766.0":
                            genreSearchTv2.setText("Soap");
                            break;
                        case"10767.0":
                            genreSearchTv2.setText("토크쇼");
                            break;
                        case "10768.0":
                            genreSearchTv2.setText("전쟁&정치");
                            break;
                        case "37.0":
                            genreSearchTv2.setText("서부");
                            break;
                        case "none":
                            genreSearchTv2.setText("장르없음");
                            break;
                    }

                    dateSearchTv2.setText(seachArrayList1.get(1).getFirst_air_date().substring(0,4));




                    // 오프셋 처리하는 코드

                } else {
                    Toast.makeText(SearchActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<SeachList> call, Throwable t) {


            }


        });
    }

    private void getNetworkSearchMovieData(String Keyword) {
        Retrofit retrofit = NetworkClient1.getRetrofitClient(SearchActivity.this);

        SearchApi api = retrofit.create(SearchApi.class);

        Log.i("AAA", api.toString());

        Call<SeachList> call = api.getSeachMovie(Config.key,language,Keyword);

        call.enqueue(new Callback<SeachList>() {
            @Override
            public void onResponse(Call<SeachList> call, Response<SeachList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요
                    seachArrayList2.clear();

                    // 데이터를 받았으니 리사이클러 표시

                    seachArrayList2.addAll(response.body().getResults());


                    // 오프셋 처리하는 코드
                    Glide.with(SearchActivity.this)
                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList2.get(0).getPoster_path())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(poster1);
                    titleSearchMovie1.setText(seachArrayList2.get(0).getTitle());
                    rateSearchMovie1.setText(seachArrayList2.get(0).getVote_average().substring(0,2));
                    List genre1 = seachArrayList2.get(0).getGenre_ids();
                    String G1 = String.valueOf((genre1.get(0)));
                    Log.i("GEN", G1.getClass().getName());
                    switch (G1) {
                        case "28.0":
                            genreSearchMovie1.setText("액션");
                            break;

                        case "12.0":
                            genreSearchMovie1.setText("모험");
                            break;
                        case "16.0":
                            genreSearchMovie1.setText("애니메이션");
                            break;
                        case "35.0":
                            genreSearchMovie1.setText("코미디");
                            break;
                        case "80.0":
                            genreSearchMovie1.setText("범죄");
                            break;
                        case "99":
                            genreSearchMovie1.setText("다큐멘터리");
                            break;
                        case "18.0":
                            genreSearchMovie1.setText("드라마");
                            break;
                        case "10751.0":
                            genreSearchMovie1.setText("가족");
                            break;
                        case "14.0":
                            genreSearchMovie1.setText("판타지");
                            break;
                        case "36.0":
                            genreSearchMovie1.setText("역사");
                            break;
                        case "27.0":
                            genreSearchMovie1.setText("공포");
                            break;
                        case "10402.0":
                            genreSearchMovie1.setText("음악");
                            break;
                        case "9648.0":
                            genreSearchMovie1.setText("미스터리");
                            break;
                        case "10749.0":
                            genreSearchMovie1.setText("로맨스");
                            break;
                        case "878.0":
                            genreSearchMovie1.setText("SF");
                            break;
                        case "10770.0":
                            genreSearchMovie1.setText("TV 영화");
                            break;
                        case "53.0":
                            genreSearchMovie1.setText("스릴러");
                            break;
                        case "10752.0":
                            genreSearchMovie1.setText("전쟁");
                            break;
                        case "37.0":
                            genreSearchMovie1.setText("서부");
                            break;
//                        case "none":
//                            genreSearchMovie1.setText("장르없음");
//                            break;
                    }
                    //좀돠라
                    dateSearchMovie1.setText(seachArrayList2.get(0).getRelease_date().substring(0,4));

                    Glide.with(SearchActivity.this)
                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList2.get(1).getPoster_path())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(poster2);
                    titleSearchMovie2.setText(seachArrayList2.get(1).getTitle());
                    rateSearchMovie2.setText(seachArrayList2.get(1).getVote_average());
                    List genre2 = seachArrayList2.get(1).getGenre_ids();
                    String G2 = String.valueOf((genre2.get(0)));
                    Log.i("GEN", G2);
                    switch (G2) {
                        case "28.0":
                            genreSearchMovie2.setText("액션");
                            break;
                        case "12.0":
                            genreSearchMovie2.setText("모험");
                            break;
                        case "16.0":
                            genreSearchMovie2.setText("애니메이션");
                            break;
                        case "35.0":
                            genreSearchMovie2.setText("코미디");
                            break;
                        case "80.0":
                            genreSearchMovie2.setText("범죄");
                            break;
                        case "99":
                            genreSearchMovie2.setText("다큐멘터리");
                            break;
                        case "18.0":
                            genreSearchMovie2.setText("드라마");
                            break;
                        case "10751.0":
                            genreSearchMovie2.setText("가족");
                            break;
                        case "14.0":
                            genreSearchMovie2.setText("판타지");
                            break;
                        case "36.0":
                            genreSearchMovie2.setText("역사");
                            break;
                        case "27.0":
                            genreSearchMovie2.setText("공포");
                            break;
                        case "10402.0":
                            genreSearchMovie2.setText("음악");
                            break;
                        case "9648.0":
                            genreSearchMovie2.setText("미스터리");
                            break;
                        case "10749.0":
                            genreSearchMovie2.setText("로맨스");
                            break;
                        case "878.0":
                            genreSearchMovie2.setText("SF");
                            break;
                        case "10770.0":
                            genreSearchMovie2.setText("TV 영화");
                            break;
                        case "53.0":
                            genreSearchMovie2.setText("스릴러");
                            break;
                        case "10752.0":
                            genreSearchMovie2.setText("전쟁");
                            break;
                        case "37.0":
                            genreSearchMovie2.setText("서부");
                            break;
                        case "none":
                            genreSearchMovie2.setText("장르없음");
                            break;
                    }
                    dateSearchMovie2.setText(seachArrayList2.get(1).getRelease_date().substring(0,4));

                    Glide.with(SearchActivity.this)
                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList2.get(2).getPoster_path())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(poster3);
                    titleSearchMovie3.setText(seachArrayList2.get(2).getTitle());
                    rateSearchMovie3.setText(seachArrayList2.get(2).getVote_average());
                    List genre3 = seachArrayList2.get(2).getGenre_ids();
                    String G3 = String.valueOf((genre3.get(0)));
                    Log.i("GEN", G3);
                    switch (G3) {
                        case "28.0":
                            genreSearchMovie3.setText("액션");
                            break;
                        case "12.0":
                            genreSearchMovie3.setText("모험");
                            break;
                        case "16.0":
                            genreSearchMovie3.setText("애니메이션");
                            break;
                        case "35.0":
                            genreSearchMovie3.setText("코미디");
                            break;
                        case "80.0":
                            genreSearchMovie3.setText("범죄");
                            break;
                        case "99":
                            genreSearchMovie3.setText("다큐멘터리");
                            break;
                        case "18.0":
                            genreSearchMovie3.setText("드라마");
                            break;
                        case "10751.0":
                            genreSearchMovie3.setText("가족");
                            break;
                        case "14.0":
                            genreSearchMovie3.setText("판타지");
                            break;
                        case "36.0":
                            genreSearchMovie3.setText("역사");
                            break;
                        case "27.0":
                            genreSearchMovie3.setText("공포");
                            break;
                        case "10402.0":
                            genreSearchMovie3.setText("음악");
                            break;
                        case "9648.0":
                            genreSearchMovie3.setText("미스터리");
                            break;
                        case "10749.0":
                            genreSearchMovie3.setText("로맨스");
                            break;
                        case "878.0":
                            genreSearchMovie3.setText("SF");
                            break;
                        case "10770.0":
                            genreSearchMovie3.setText("TV 영화");
                            break;
                        case "53.0":
                            genreSearchMovie3.setText("스릴러");
                            break;
                        case "10752.0":
                            genreSearchMovie3.setText("전쟁");
                            break;
                        case "37.0":
                            genreSearchMovie3.setText("서부");
                            break;
                        case "none":
                            genreSearchMovie3.setText("장르없음");
                            break;
                    }
                    dateSearchMovie3.setText(seachArrayList2.get(2).getRelease_date().substring(0,4));


                } else {
                    Toast.makeText(SearchActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<SeachList> call, Throwable t) {


            }


        });
    }
}
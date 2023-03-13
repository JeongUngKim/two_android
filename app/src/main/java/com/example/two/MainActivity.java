package com.example.two;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.two.Api.NetworkClient1;
import com.example.two.adapter.MainAdapter;
import com.example.two.config.Config;
import com.example.two.config.MovieApi;
import com.example.two.model.Movie;
import com.example.two.model.MovieList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText editTitle;
    ImageView imgSearch;
    Button btnCommunity;
    Button btnHome;
    Button btnFilter;
    Button btnParty;
    Button btnMy;

    RecyclerView recyclerView;

    MainAdapter adapter;

    ArrayList<Movie> movieArrayList = new ArrayList<>();

    int page;

    String language = "ko-KR";
    String desc = "popularity.desc";

    String input;

    private Movie selectedMovie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnCommunity = findViewById(R.id.btnCommunity);
        btnHome = findViewById(R.id.btnHome);
        btnParty = findViewById(R.id.btnParty);
        btnFilter = findViewById(R.id.btnFilter);
        btnMy = findViewById(R.id.btnMy);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        getNetworkData();
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
                                                     if(page != -1) {
                                                         addNetworkData();
                                                     }
                                                 }
                                             }
                                         });



//        ActionBar ab = getSupportActionBar();
//        // 액션바 타이틀
//        ab.setTitle("    TWO");
//        // 액션바 아이콘
//        ab.setIcon(R.drawable._915979_logo_media_social_viddler_icon) ;
//        ab.setDisplayUseLogoEnabled(true) ;
//        ab.setDisplayShowHomeEnabled(true) ;


        // 검색화면으로 넘어가기
//        editTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Log.i("keyword",input);
////                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
////                startActivity(intent);
//            }
//        });

//        imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(intent);
//            }
//        });

        // 커뮤니티 액티비티 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CommunityActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 파티매칭 액티비티 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PartyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 필터검색 액티비티 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FilterSearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 내 정보 액티비티 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getNetworkData() {
        Retrofit retrofit = NetworkClient1.getRetrofitClient(MainActivity.this);

        MovieApi api = retrofit.create(MovieApi.class);

        Log.i("AAA", api.toString());

        Call<MovieList> call = api.getMovie(Config.key,language,1,desc);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요
                    movieArrayList.clear();

                    // 데이터를 받았으니 리사이클러 표시

                    movieArrayList.addAll(response.body().getResults());
                    movieArrayList.get(page);
                    Log.i("page",String.valueOf(page));
                    // 오프셋 처리하는 코드


                    adapter = new MainAdapter(MainActivity.this,movieArrayList);
                    recyclerView.setAdapter(adapter);
                    Log.i("RECYCLE", adapter.toString());

                } else {
                    Toast.makeText(MainActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {


            }


        });
    }

    private void addNetworkData() {
        Retrofit retrofit = NetworkClient1.getRetrofitClient(MainActivity.this);

        MovieApi api = retrofit.create(MovieApi.class);

        Log.i("AAA", api.toString());
        if(page == 0){
            page = 1;
        }else{
            page = page+1;
        }
        Call<MovieList> call = api.getMovie(Config.key,language,page+1,desc);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요

                    // 데이터를 받았으니 리사이클러 표시

                    movieArrayList.addAll(response.body().getResults());
                    movieArrayList.get(page);
                    Log.i("page",String.valueOf(page));

                    // 오프셋 처리하는 코드


                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {


            }


        });
    }

    public void isDetail(int index){
        selectedMovie = movieArrayList.get(index);
        int Id = movieArrayList.get(index).getId();
        Log.i("ID",String.valueOf(Id));
        Intent intent = new Intent(MainActivity.this,MovieContentActivity.class);
        intent.putExtra("id",Id);
        startActivity(intent);

    }

    // 액션바 활성화
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

    // 액션바 클릭 이벤트 처리
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//        startActivity(intent);
//        return super.onOptionsItemSelected(item);
//    }
}
package com.example.two;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.two.Api.NetworkClient1;
import com.example.two.Api.NetworkClient2;
import com.example.two.adapter.MainAdapter;
import com.example.two.config.Config;
import com.example.two.Api.MovieApi;
import com.example.two.fragment.CommunityFragment;
import com.example.two.fragment.MyFragment;
import com.example.two.fragment.PartyFragment;
import com.example.two.fragment.SearchFragment;
import com.example.two.model.Movie;
import com.example.two.model.MovieList;
import com.example.two.model.MovieRank;
import com.example.two.model.MovieRankList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText editTitle;
    ImageView imgSearch;
    ImageButton btnCommunity;
    ImageButton btnHome;
    ImageButton btnFilter;
    ImageButton btnParty;
    ImageButton btnMy;

    ImageView rankPoster1;
    ImageView rankPoster2;
    ImageView rankPoster3;

    TextView rankTitle1;
    TextView rankTitle2;
    TextView rankTitle3;

    TextView rankRate1;
    TextView rankRate2;
    TextView rankRate3;

    ImageView imgLogout;
    ImageView imgRank;

    // 프래그먼트 관련 멤버변수
    PartyFragment partyFragment;
    CommunityFragment communityFragment;
    SearchFragment searchFragment;
    MyFragment myFragment;





    RecyclerView recyclerView;

    MainAdapter adapter;

    ArrayList<Movie> movieArrayList = new ArrayList<>();

    ArrayList<MovieRank> movierankArrayList = new ArrayList<>();

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

        rankPoster1 = findViewById(R.id.rankPoster1);
        rankPoster2 = findViewById(R.id.rankPoster2);
        rankPoster3 = findViewById(R.id.rankPoster3);

        rankTitle1 = findViewById(R.id.rankTitle1);
        rankTitle2 = findViewById(R.id.rankTitle2);
        rankTitle3 = findViewById(R.id.rankTitle3);

        rankRate1 = findViewById(R.id.rankRate1);
        rankRate2 = findViewById(R.id.rankRate2);
        rankRate3 = findViewById(R.id.rankRate3);

        imgLogout = findViewById(R.id.imgLogout);
        imgRank = findViewById(R.id.imgRank);


        // 파티 프래그먼트 생성
        partyFragment = new PartyFragment();
        // 커뮤니티 프래그먼트 생성
        communityFragment = new CommunityFragment();
        // 검색 프래그먼트 생성
        searchFragment = new SearchFragment();
        // 내 정보 프래그먼트 생성
        myFragment = new MyFragment();
        // 홈 프래그먼트 생성


        //프래그먼트 매니저 획득
        FragmentManager fragmentManager = getSupportFragmentManager();

        //프래그먼트 Transaction 획득 프래그먼트를 실행하거나 교체 작업할때 사용
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 프래그먼트를 FragmentFrame의 자식으로 등록
        fragmentTransaction.add(R.id.fragmentFrame,communityFragment);
        fragmentTransaction.add(R.id.fragmentFrame,partyFragment);
        fragmentTransaction.add(R.id.fragmentFrame,searchFragment);
        fragmentTransaction.add(R.id.fragmentFrame,myFragment);


        getrankMovieData();

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

        // 통합 랭킹 전체보기 이미지 이벤트 처리
        imgRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RankALLActivity.class);
                startActivity(intent);

            }
        });

        // 로그아웃 이미지 이벤트 처리
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 커뮤니티 프래그먼트 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, communityFragment).commit();
            }
        });

        // 파티매칭 프래그먼트 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, partyFragment).commit();
            }
        });

        // 검색 프래그먼트 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, searchFragment).commit();

            }
        });

        // 내 정보 프래그먼트 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, myFragment).commit();

            }
        });
    }

    public void getNetworkData() {
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

    public void addNetworkData() {
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

    public void getrankMovieData() {
        Retrofit retrofit = NetworkClient2.getRetrofitClient(MainActivity.this);

        MovieApi api = retrofit.create(MovieApi.class);

//        Log.i("AAA", api.toString());

        Call<MovieRankList> call = api.getrankMovie();
        Log.i("CCC", call.toString());
        call.enqueue(new Callback<MovieRankList>() {

            @Override
            public void onResponse(Call<MovieRankList> call, Response<MovieRankList> response) {

                if (response.isSuccessful()) {

                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로

                    // 데이터를 받았으니 리사이클러 표시

                    movierankArrayList.addAll(response.body().getRank());
                    Log.i("AAA", movierankArrayList.toString());
                    // 오프셋 처리하는 코드
                    Glide.with(MainActivity.this)
                            .load(movierankArrayList.get(0).getImgUrl())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(rankPoster1);
                    rankTitle1.setText(movierankArrayList.get(0).getTitle());
                    rankRate1.setText(movierankArrayList.get(0).getContentRating());

                    Glide.with(MainActivity.this)
                            .load(movierankArrayList.get(1).getImgUrl())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(rankPoster2);
                    rankTitle2.setText(movierankArrayList.get(1).getTitle());
                    rankRate2.setText(movierankArrayList.get(1).getContentRating());

                    Glide.with(MainActivity.this)
                            .load(movierankArrayList.get(2).getImgUrl())
                            .placeholder(R.drawable.baseline_person_outline_24)
                            .into(rankPoster3);
                    rankTitle3.setText(movierankArrayList.get(2).getTitle());
                    rankRate3.setText(movierankArrayList.get(2).getContentRating());




                } else {
                    Toast.makeText(MainActivity.this, "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<MovieRankList> call, Throwable t) {
                Log.i("DDD", String.valueOf(t));


            }


        });
    }

    // 프래그먼트 간 화면 전환 시켜주기위한 함수
    public void onFragmentChange(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame,CommunityFragment.class, null).setReorderingAllowed(true).commit();
        }
        if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame,PartyFragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
        }
        if (index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame,SearchFragment.class,null).setReorderingAllowed(true).commit();
        }
        if (index == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, MyFragment.class,null).setReorderingAllowed(true).commit();
        }
    }


}

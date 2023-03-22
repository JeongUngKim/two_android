package com.example.two.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.two.Api.NetworkClient2;
import com.example.two.Api.SearchApi;
import com.example.two.MainActivity;
import com.example.two.MovieALLActivity;
import com.example.two.R;
import com.example.two.TvALLActivity;
import com.example.two.adapter.SeachAdapter;
import com.example.two.model.Seach;
import com.example.two.model.SeachData;
import com.example.two.model.SeachList;
import com.example.two.model.SeachList2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    MainActivity activity;

    ImageButton btnCommunity;
    ImageButton btnHome;
    ImageButton btnFilter;
    ImageButton btnParty;
    ImageButton btnMy;

    ArrayList<Seach> seachArrayList1 = new ArrayList<>();

    ArrayList<Seach> seachArrayList2 = new ArrayList<>();

    String Keyword;

    SeachAdapter adapter;

    ImageView btntitleSearch;

    TextView txtSearch;

    RecyclerView movieListView;
    RecyclerView dramaListView;

    ImageView btnIntoMovie;
    ImageView btnIntoTv;

    SeachData seachData;
    Layout layoutMovie;
    Layout layoutDrama;


    String T1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        btnCommunity = view.findViewById(R.id.btnCommunity);
        btnHome = view.findViewById(R.id.btnHome);
        btnParty = view.findViewById(R.id.btnParty);
        btnFilter = view.findViewById(R.id.btnFilter);
        btnMy = view.findViewById(R.id.btnMy);
        btntitleSearch = view.findViewById(R.id.btntitleSearch);
        txtSearch = view.findViewById(R.id.txtSearch);
        movieListView = view.findViewById(R.id.movieListView);
        dramaListView = view.findViewById(R.id.dramaListView);
        btnIntoMovie = view.findViewById(R.id.btnIntoMovie);
        btnIntoTv = view.findViewById(R.id.btnIntoTv);
//        layoutMovie = view.findViewById(R.id.layoutMovie);
//        layoutDrama = view.findViewById(R.id.layoutDrama);

        movieListView.setHasFixedSize(true);
        movieListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dramaListView.setHasFixedSize(true);
        dramaListView.setLayoutManager(new LinearLayoutManager(getActivity()));


        btntitleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyword = txtSearch.getText().toString().trim();
//                layoutMovie.setVisibility(View.VISIBLE);
//                layoutMovie1.setVisibility(View.VISIBLE);
//                layoutMovie2.setVisibility(View.VISIBLE);
//                layoutMovie3.setVisibility(View.VISIBLE);
//                layoutDrama.setVisibility(View.VISIBLE);
//                layoutDrama1.setVisibility(View.VISIBLE);
//                layoutDrama2.setVisibility(View.VISIBLE);
                getNetworkSearchMovieData(Keyword);
                getNetworkSearchDramaData(Keyword);



            }
        });

        btnIntoMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MovieALLActivity.class);
                intent.putExtra("keyword",Keyword);
                startActivity(intent);
            }
        });

        btnIntoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TvALLActivity.class);
                intent.putExtra("keyword",Keyword);
                startActivity(intent);
            }
        });







        // 메인 액티비티 넘어가기
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        // 커뮤니티 프래그먼트 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.onFragmentChange(0);

            }
        });


        // 파티매칭 프래그먼트 넘어가기
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.onFragmentChange(1);
            }
        });

        // 내정보 프래그먼트 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3);
            }
        });



        return view;



    }



    private void getNetworkSearchMovieData(String Keyword) {
        Retrofit retrofit = NetworkClient2.getRetrofitClient(getActivity());

        SearchApi api = retrofit.create(SearchApi.class);

        Log.i("AAA", api.toString());

        String type = "movie";


        Map<String, String> map = new LinkedHashMap<>();
        map.put("keyword", Keyword);
        map.put("genre", "");
        map.put("limit", "3");
        map.put("rating","");
        map.put("year","");
        map.put("offset","0");
        map.put("filtering","");
        map.put("sort","");




        Call<SeachList> call = api.getSeach(type,map);

        call.enqueue(new Callback<SeachList>() {
            @Override
            public void onResponse(Call<SeachList> call, Response<SeachList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요
                    seachArrayList1.clear();

                    Log.i("SIGN","OK");

                    // 데이터를 받았으니 리사이클러 표시

                    seachArrayList1.addAll(response.body().getMovie());




                    adapter = new SeachAdapter(getActivity(),seachArrayList1);

                    movieListView.setAdapter(adapter);



                    //

//                    List genre1 = seachArrayList1.get(0).getGenre();
//                    String G1 = String.valueOf((genre1.get(0)));
//                    Log.i("GEN", G1.getClass().getName());
//                    switch (G1) {
//                        case "28.0":
//                            genreSearchMovie1.setText("액션");
//                            break;
//
//                        case "12.0":
//                            genreSearchMovie1.setText("모험");
//                            break;
//                        case "16.0":
//                            genreSearchMovie1.setText("애니메이션");
//                            break;
//                        case "35.0":
//                            genreSearchMovie1.setText("코미디");
//                            break;
//                        case "80.0":
//                            genreSearchMovie1.setText("범죄");
//                            break;
//                        case "99":
//                            genreSearchMovie1.setText("다큐멘터리");
//                            break;
//                        case "18.0":
//                            genreSearchMovie1.setText("드라마");
//                            break;
//                        case "10751.0":
//                            genreSearchMovie1.setText("가족");
//                            break;
//                        case "14.0":
//                            genreSearchMovie1.setText("판타지");
//                            break;
//                        case "36.0":
//                            genreSearchMovie1.setText("역사");
//                            break;
//                        case "27.0":
//                            genreSearchMovie1.setText("공포");
//                            break;
//                        case "10402.0":
//                            genreSearchMovie1.setText("음악");
//                            break;
//                        case "9648.0":
//                            genreSearchMovie1.setText("미스터리");
//                            break;
//                        case "10749.0":
//                            genreSearchMovie1.setText("로맨스");
//                            break;
//                        case "878.0":
//                            genreSearchMovie1.setText("SF");
//                            break;
//                        case "10770.0":
//                            genreSearchMovie1.setText("TV 영화");
//                            break;
//                        case "53.0":
//                            genreSearchMovie1.setText("스릴러");
//                            break;
//                        case "10752.0":
//                            genreSearchMovie1.setText("전쟁");
//                            break;
//                        case "37.0":
//                            genreSearchMovie1.setText("서부");
//                            break;
//                        case "none":
//                            genreSearchMovie1.setText("장르없음");
//                            break;
//                    }
//                    //좀돠라
//                    dateSearchMovie1.setText(seachArrayList2.get(0).getRelease_date().substring(0,4));
//
//                    Glide.with(SearchFragment.this)
//                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList2.get(1).getPoster_path())
//                            .placeholder(R.drawable.baseline_person_outline_24)
//                            .into(poster2);
//                    titleSearchMovie2.setText(seachArrayList2.get(1).getTitle());
//                    rateSearchMovie2.setText(seachArrayList2.get(1).getVote_average());
//                    List genre2 = seachArrayList2.get(1).getGenre_ids();
//                    String G2 = String.valueOf((genre2.get(0)));
//                    Log.i("GEN", G2);
//                    switch (G2) {
//                        case "28.0":
//                            genreSearchMovie2.setText("액션");
//                            break;
//                        case "12.0":
//                            genreSearchMovie2.setText("모험");
//                            break;
//                        case "16.0":
//                            genreSearchMovie2.setText("애니메이션");
//                            break;
//                        case "35.0":
//                            genreSearchMovie2.setText("코미디");
//                            break;
//                        case "80.0":
//                            genreSearchMovie2.setText("범죄");
//                            break;
//                        case "99":
//                            genreSearchMovie2.setText("다큐멘터리");
//                            break;
//                        case "18.0":
//                            genreSearchMovie2.setText("드라마");
//                            break;
//                        case "10751.0":
//                            genreSearchMovie2.setText("가족");
//                            break;
//                        case "14.0":
//                            genreSearchMovie2.setText("판타지");
//                            break;
//                        case "36.0":
//                            genreSearchMovie2.setText("역사");
//                            break;
//                        case "27.0":
//                            genreSearchMovie2.setText("공포");
//                            break;
//                        case "10402.0":
//                            genreSearchMovie2.setText("음악");
//                            break;
//                        case "9648.0":
//                            genreSearchMovie2.setText("미스터리");
//                            break;
//                        case "10749.0":
//                            genreSearchMovie2.setText("로맨스");
//                            break;
//                        case "878.0":
//                            genreSearchMovie2.setText("SF");
//                            break;
//                        case "10770.0":
//                            genreSearchMovie2.setText("TV 영화");
//                            break;
//                        case "53.0":
//                            genreSearchMovie2.setText("스릴러");
//                            break;
//                        case "10752.0":
//                            genreSearchMovie2.setText("전쟁");
//                            break;
//                        case "37.0":
//                            genreSearchMovie2.setText("서부");
//                            break;
//                        case "none":
//                            genreSearchMovie2.setText("장르없음");
//                            break;
//                    }
//                    dateSearchMovie2.setText(seachArrayList2.get(1).getRelease_date().substring(0,4));
//
//                    Glide.with(SearchFragment.this)
//                            .load("https://image.tmdb.org/t/p/w92"+seachArrayList2.get(2).getPoster_path())
//                            .placeholder(R.drawable.baseline_person_outline_24)
//                            .into(poster3);
//                    titleSearchMovie3.setText(seachArrayList2.get(2).getTitle());
//                    rateSearchMovie3.setText(seachArrayList2.get(2).getVote_average());
//                    List genre3 = seachArrayList2.get(2).getGenre_ids();
//                    String G3 = String.valueOf((genre3.get(0)));
//                    Log.i("GEN", G3);
//                    switch (G3) {
//                        case "28.0":
//                            genreSearchMovie3.setText("액션");
//                            break;
//                        case "12.0":
//                            genreSearchMovie3.setText("모험");
//                            break;
//                        case "16.0":
//                            genreSearchMovie3.setText("애니메이션");
//                            break;
//                        case "35.0":
//                            genreSearchMovie3.setText("코미디");
//                            break;
//                        case "80.0":
//                            genreSearchMovie3.setText("범죄");
//                            break;
//                        case "99":
//                            genreSearchMovie3.setText("다큐멘터리");
//                            break;
//                        case "18.0":
//                            genreSearchMovie3.setText("드라마");
//                            break;
//                        case "10751.0":
//                            genreSearchMovie3.setText("가족");
//                            break;
//                        case "14.0":
//                            genreSearchMovie3.setText("판타지");
//                            break;
//                        case "36.0":
//                            genreSearchMovie3.setText("역사");
//                            break;
//                        case "27.0":
//                            genreSearchMovie3.setText("공포");
//                            break;
//                        case "10402.0":
//                            genreSearchMovie3.setText("음악");
//                            break;
//                        case "9648.0":
//                            genreSearchMovie3.setText("미스터리");
//                            break;
//                        case "10749.0":
//                            genreSearchMovie3.setText("로맨스");
//                            break;
//                        case "878.0":
//                            genreSearchMovie3.setText("SF");
//                            break;
//                        case "10770.0":
//                            genreSearchMovie3.setText("TV 영화");
//                            break;
//                        case "53.0":
//                            genreSearchMovie3.setText("스릴러");
//                            break;
//                        case "10752.0":
//                            genreSearchMovie3.setText("전쟁");
//                            break;
//                        case "37.0":
//                            genreSearchMovie3.setText("서부");
//                            break;
//                        case "none":
//                            genreSearchMovie3.setText("장르없음");
//                            break;
//                    }
//                    dateSearchMovie3.setText(seachArrayList2.get(2).getRelease_date().substring(0,4));


                } else {
                    Toast.makeText(getActivity(), "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<SeachList> call, Throwable t) {
            Log.i("ERRER", String.valueOf(t));

            }


        });
    }

    private void getNetworkSearchDramaData(String Keyword) {
        Retrofit retrofit = NetworkClient2.getRetrofitClient(getActivity());

        SearchApi api = retrofit.create(SearchApi.class);

        Log.i("AAA", api.toString());

        String type = "tv";


        Map<String, String> map = new LinkedHashMap<>();
        map.put("keyword", Keyword);
        map.put("genre", "");
        map.put("limit", "2");
        map.put("rating","");
        map.put("year","");
        map.put("offset","0");
        map.put("filtering","");
        map.put("sort","");




        Call<SeachList2> call = api.getSeachTv(type,map);

        call.enqueue(new Callback<SeachList2>() {
            @Override
            public void onResponse(Call<SeachList2> call, Response<SeachList2> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요

                    seachArrayList2.clear();
                    Log.i("SIGN","OK");

                    // 데이터를 받았으니 리사이클러 표시

                    seachArrayList2.addAll(response.body().getTv());




                    adapter = new SeachAdapter(getActivity(),seachArrayList2);

                    dramaListView.setAdapter(adapter);





                } else {
                    Toast.makeText(getActivity(), "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<SeachList2> call, Throwable t) {
                Log.i("ERRER", String.valueOf(t));

            }


        });
    }
}

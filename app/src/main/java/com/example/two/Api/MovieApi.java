package com.example.two.Api;

import com.example.two.model.MovieList;
import com.example.two.model.MovieRankList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie")
    Call<MovieList>getMovie(@Query("api_key") String keys,
                            @Query("language")String language,
                            @Query("page")int page,
                            @Query("sort_by")String desc);

    @GET("rank")
    Call<MovieRankList>getrankMovie();


}

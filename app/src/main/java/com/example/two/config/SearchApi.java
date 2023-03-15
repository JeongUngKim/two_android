package com.example.two.config;

import com.example.two.model.MovieList;
import com.example.two.model.Seach;
import com.example.two.model.SeachList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("search/movie")
    Call<SeachList> getSeachMovie(@Query("api_key") String keys,
                                  @Query("language")String language,
                                  @Query("query")String keyword);

    @GET("search/tv")
    Call<SeachList> getSeachTv(@Query("api_key") String keys,
                                  @Query("language")String language,
                                  @Query("query")String keyword);
}

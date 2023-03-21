package com.example.two.Api;

import com.example.two.model.SeachList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApi {

    @POST("search")
    Call<SeachList> getSeachMovie(@Query("type") String type,
                                  @Body String keyword);

//    @GET("search/tv")
//    Call<SeachList> getSeachTv(@Query("api_key") String keys,
//                                  @Query("language")String language,
//                                  @Query("query")String keyword);
}

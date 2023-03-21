package com.example.two.Api;

import com.example.two.model.Seach;
import com.example.two.model.SeachData;
import com.example.two.model.SeachList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApi {

    @POST("search")
    Call<SeachList> getSeach(@Query("type") String type,
                             @Body Map<String, String> map);

//    @GET("search/tv")
//    Call<SeachList> getSeachTv(@Query("api_key") String keys,
//                                  @Query("language")String language,
//                                  @Query("query")String keyword);
}

package com.example.two.Api;

import com.example.two.model.ContentWatch;
import com.example.two.model.ContentWatchList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ContentApi {

    @POST("contentWatch/{contentId}")
    Call<ContentWatch> contentWatch(@Header("Authorization") String token, @Path("contentId") int contentId);

    @GET("contentWatch")
    Call<ContentWatchList> getContentWatch(@Header("Authorization") String token, @Query("page") int page);

}

package com.example.two.Api;

import com.example.two.model.ContentWatch;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ContentApi {

    @POST("contentWatch/{contentId}")
    Call<ContentWatch> contentWatch(@Header("Authorization") String token);

}

package com.example.two.Api;

import com.example.two.model.reView;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewApi {

    @POST("content/{contentId}/review")
    Call<reView> makeReview(@Path("contentId") int Id,
                            @Header("Authorization") String token,
                            @Body Map<String, String> map);

}

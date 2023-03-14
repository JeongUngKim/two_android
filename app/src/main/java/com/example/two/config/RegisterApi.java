package com.example.two.config;

import com.example.two.model.User;
import com.example.two.model.UserRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RegisterApi {

    @POST("register")
    Call<UserRes> resgister(@Part MultipartBody.Part profileImg,@Part User user);

    @POST("/user/login")
    Call<UserRes> login(@Body User user);

    // 로그아웃 API
    @POST("/user/logout")
    Call<UserRes> logout(@Header("Authorization") String token);

}

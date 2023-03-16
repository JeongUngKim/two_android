package com.example.two.config;

import com.example.two.model.User;
import com.example.two.model.UserRes;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RegisterApi {

    @Multipart
    @POST("register")
    Call<UserRes> resgister(@PartMap JSONObject data,
                            @Part MultipartBody.Part profileImg);

    @POST("/user/login")
    Call<UserRes> login(@Body User user);

    // 로그아웃 API
    @POST("/user/logout")
    Call<UserRes> logout(@Header("Authorization") String token);

}

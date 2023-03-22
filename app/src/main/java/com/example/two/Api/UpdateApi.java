package com.example.two.Api;

import com.example.two.model.UserRes;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UpdateApi {

    @Multipart
    @PUT("user")
    Call<UserRes> update( @Header("Authorization") String token, @Part("data") RequestBody data, @Part MultipartBody.Part profileImg );

}

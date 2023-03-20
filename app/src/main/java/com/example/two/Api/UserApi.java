package com.example.two.Api;
import com.example.two.model.User;
import com.example.two.model.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserApi {




        @GET("user")
        Call<UserList> getUserInfo(@Header("Authorization") String token);


}

package com.example.two.Api;

import com.example.two.model.ChatRoomList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChatApi {

    @GET("partyBoard")
    Call<ChatRoomList>getChatingList(@Query("page") int page);
}

package com.example.two.Api;

import com.example.two.model.Party;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PartyApi {
    @POST("party")
    Call<HashMap<String,String>> setParty(@Header("Authorization") String token,
                                          @Body Party party);

}

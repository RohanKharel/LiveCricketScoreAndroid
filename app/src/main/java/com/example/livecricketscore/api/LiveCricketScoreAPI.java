package com.example.livecricketscore.api;

import com.example.livecricketscore.model.User;
import com.example.livecricketscore.serverresponse.SignUpResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LiveCricketScoreAPI {

    @POST("registration")
    Call<SignUpResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("login")
    Call<SignUpResponse> checkUser(@Field("email") String email, @Field("password") String password);



}

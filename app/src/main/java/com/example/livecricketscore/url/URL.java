package com.example.livecricketscore.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {

    private final static String BASE_URL = "http://10.0.2.2:3011/";



    public static String token = "Bearer ";

    public static Retrofit getInstance() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

}

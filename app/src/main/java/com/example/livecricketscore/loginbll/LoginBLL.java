package com.example.livecricketscore.loginbll;

import android.util.Log;

import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.serverresponse.SignUpResponse;
import com.example.livecricketscore.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class LoginBLL {


    private boolean isSuccess = false;




    public boolean checkUser(String email, String password){
        LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);

        Call<SignUpResponse> signUpResponseCall = liveCricketScoreAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = signUpResponseCall.execute();
            Log.d("Error", loginResponse.message());
            if (loginResponse.isSuccessful()) {
                loginResponse.body().getStatus().equals("200");
                URL.token += loginResponse.body().getToken();

                isSuccess = true;
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}

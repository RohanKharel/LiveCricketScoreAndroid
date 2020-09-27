package com.example.livecricketscore;

import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.User;
import com.example.livecricketscore.serverresponse.SignUpResponse;
import com.example.livecricketscore.url.URL;


import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;


public class LoginBLL {

    @Test
            public void testLogin() throws IOException
    {

        LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        Call<SignUpResponse> signUpResponseCall = liveCricketScoreAPI.checkUser("rohansharma@gmail.com" ,"rohan123");

        try {
            Response<SignUpResponse> loginResponse = signUpResponseCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                URL.token += loginResponse.body().getToken();



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void registerCheck() throws IOException
    {
        LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        Call<SignUpResponse> signUpResponseCall = liveCricketScoreAPI.registerUser(new User("Rohan kharel", "sujan123", "9811718546",  "rohan123"));

        try {
            Response<SignUpResponse> response = signUpResponseCall.execute();
            SignUpResponse signUpResponse = response.body();
            assertTrue(response.isSuccessful() && signUpResponse.getStatus() == "200");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



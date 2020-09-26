package com.example.livecricketscore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.livecricketscore.R;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.UserUpdate;
import com.example.livecricketscore.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText etUFullname, etPhoneNo;
    private Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        etUFullname = findViewById(R.id.etUFullname);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        btnUpdate = findViewById(R.id.btnUpdate);

        final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        final Call<UserUpdate> userUpdateCall = liveCricketScoreAPI.showUser(URL.token);

        userUpdateCall.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateProfileActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    etUFullname.setText(response.body().getFullname());
                    etPhoneNo.setText(response.body().getPhoneNo());

                }
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {

                Log.d("Error: ", t.getLocalizedMessage());

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<Void> voidCall = liveCricketScoreAPI.updateUser(URL.token,
                        new UserUpdate(
                                etUFullname.getText().toString(),
                                etPhoneNo.getText().toString()
                        ));
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(UpdateProfileActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            Toast.makeText(UpdateProfileActivity.this, "User has been updated successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

    }
    }

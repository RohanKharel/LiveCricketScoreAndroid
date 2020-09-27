package com.example.livecricketscore.ui.home;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livecricketscore.R;
import com.example.livecricketscore.adapter.HomeAdapter;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.Details;
import com.example.livecricketscore.url.URL;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.SENSOR_SERVICE;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView txtFeedback;
    private SensorManager sensorManager;
    private EditText etFeedback;
    private Button btnFeedback,btnLogout;





    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);
        txtFeedback = root.findViewById(R.id.txtFeedback);
        etFeedback = root.findViewById(R.id.etFeedback);
        proximity();
        btnFeedback = root.findViewById(R.id.btnFeedback);
        final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Call<Void> voidCall = liveCricketScoreAPI.addFeedback(etFeedback.getText().toString(), URL.token);
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getActivity(), "Feedback sent successfully", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Error: ", t.getLocalizedMessage());
                    }
                });
            }
        });






        Call<List<Details>> scoreList = liveCricketScoreAPI.getScoreList();

        scoreList.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Details> score = response.body();
                HomeAdapter homeAdapter = new HomeAdapter(getContext(), score);

                recyclerView.setAdapter(homeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }


            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        return root;
    }

    private void proximity() {
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] == 0) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (sensor != null) {
            sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(getContext(), "sensor not found", Toast.LENGTH_SHORT).show();
        }
    }
}



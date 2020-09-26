package com.example.livecricketscore.ui.upcomingmatches;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.livecricketscore.activity.UpdateProfileActivity;
import com.example.livecricketscore.adapter.UpcomingmatchesAdapter;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.Details;
import com.example.livecricketscore.model.UpcomingDetails;
import com.example.livecricketscore.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingmatchesFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnUpdateProfile;


    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_upcomingmatches, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        btnUpdateProfile = root.findViewById(R.id.btnUpdateProfile);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile();
            }
        });


         LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        Call<List<UpcomingDetails>> detailsListCall = liveCricketScoreAPI.getUpcomingmatchesList();

            detailsListCall.enqueue(new Callback<List<UpcomingDetails>>() {
            @Override
            public void onResponse(Call<List<UpcomingDetails>> call, Response<List<UpcomingDetails>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code :" + response.code(), Toast.LENGTH_SHORT).show();
                    return;

                }

                List<UpcomingDetails> upcomingDetails = response.body();
                UpcomingmatchesAdapter upcomingmatchesAdapter = new UpcomingmatchesAdapter(getContext(), upcomingDetails);
                recyclerView.setAdapter(upcomingmatchesAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<UpcomingDetails>> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    public void EditProfile(){
        Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
        startActivity(intent);
    }
}




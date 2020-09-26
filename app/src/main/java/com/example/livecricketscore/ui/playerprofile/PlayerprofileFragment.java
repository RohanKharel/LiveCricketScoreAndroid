package com.example.livecricketscore.ui.playerprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livecricketscore.R;
import com.example.livecricketscore.adapter.PlayerprofileAdapter;
import com.example.livecricketscore.adapter.UpcomingmatchesAdapter;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.PlayerDetails;
import com.example.livecricketscore.model.UpcomingDetails;
import com.example.livecricketscore.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerprofileFragment extends Fragment {

    private RecyclerView recyclerView;


    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_playerprofile, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);
        Call<List<PlayerDetails>> detailsListCall = liveCricketScoreAPI.getPlayerDetailsList();

        detailsListCall.enqueue(new Callback<List<PlayerDetails>>() {
            @Override
            public void onResponse(Call<List<PlayerDetails>> call, Response<List<PlayerDetails>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "code :" + response.code(), Toast.LENGTH_SHORT).show();
                    return;

                }

                List<PlayerDetails> playerDetailsList = response.body();
                PlayerprofileAdapter playerprofileAdapter = new PlayerprofileAdapter(getContext(), playerDetailsList);
                recyclerView.setAdapter(playerprofileAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<PlayerDetails>> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }


}
package com.example.livecricketscore.ui.searchFragment;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livecricketscore.R;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.PlayerDetails;
import com.example.livecricketscore.url.URL;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private TextView txtName, txtCountry, txtOdirun, txtT20run, txtTestrun, txtWickets;
    private EditText etSearch;
    private Button btnSearch;
    private CardView cardViewSearch;

    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.search_fragment, container, false);

        etSearch = root.findViewById(R.id.etSearch);
        txtName = root.findViewById(R.id.txtName);
        txtCountry = root.findViewById(R.id.txtCountry);
        txtOdirun = root.findViewById(R.id.txtOdirun);
        txtT20run = root.findViewById(R.id.txtT20run);
        txtTestrun = root.findViewById(R.id.txtTestrun);
        txtWickets = root.findViewById(R.id.txtWickets);
        cardViewSearch = root.findViewById(R.id.cardViewSearch);
        btnSearch= root.findViewById(R.id.btnSearch);

        final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<PlayerDetails> detailsCall = liveCricketScoreAPI.searchPlayerprofile(etSearch.getText().toString());

                detailsCall.enqueue(new Callback<PlayerDetails>() {
                    @Override
                    public void onResponse(Call<PlayerDetails> call, Response<PlayerDetails> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        PlayerDetails playerDetails = response.body();

                        txtName.setText(playerDetails.getName());
                        txtName.setVisibility(View.VISIBLE);

                        txtCountry.setText(playerDetails.getCountry());
                        txtCountry.setVisibility(View.VISIBLE);

                        txtOdirun.setText(playerDetails.getOdirun());
                        txtOdirun.setVisibility(View.VISIBLE);

                        txtT20run.setText(playerDetails.getT20run());
                        txtT20run.setVisibility(View.VISIBLE);

                        txtTestrun.setText(playerDetails.getTestrun());
                        txtTestrun.setVisibility(View.VISIBLE);

                        txtWickets.setText(playerDetails.getWickets());
                        txtWickets.setVisibility(View.VISIBLE);


                        cardViewSearch.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onFailure(Call<PlayerDetails> call, Throwable t) {
                        Log.d("Error : ", t.getLocalizedMessage());

                    }
                });
            }
        });

        return root;
    }


}

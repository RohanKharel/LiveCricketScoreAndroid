package com.example.livecricketscore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livecricketscore.R;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.PlayerDetails;
import com.example.livecricketscore.model.UpcomingDetails;
import com.example.livecricketscore.url.URL;

import java.util.List;

public class PlayerprofileAdapter extends RecyclerView.Adapter<PlayerprofileAdapter.DetailsViewHolder> {
    public static String name;
    public static String country;
    public static String odirun;
    public static String t20run;
    public static String testrun;
    public static String Wickets;

    Context context;
    List<PlayerDetails> DetailsList;
    public FragmentManager fragmentManager;
    final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);

    public PlayerprofileAdapter(Context context, List<PlayerDetails> DetailsList) {
        this.context = context;
        this.DetailsList = DetailsList;
    }


    @NonNull
    @Override
    public PlayerprofileAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playerprofile_layout, parent, false);

        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerprofileAdapter.DetailsViewHolder holder, int position) {
        final PlayerDetails PlayerDetails = DetailsList.get(position);
        holder.txtName.setText(PlayerDetails.getName());
        holder.txtCountry.setText(PlayerDetails.getCountry());
        holder.txtOdirun.setText(PlayerDetails.getOdirun());
        holder.txtT20run.setText(PlayerDetails.getT20run());
        holder.txtTestrun.setText(PlayerDetails.getTestrun());
        holder.txtWickets.setText(PlayerDetails.getWickets());




    }

    @Override
    public int getItemCount() {
        return DetailsList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtCountry, txtOdirun, txtT20run, txtTestrun, txtWickets;


        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtCountry = itemView.findViewById(R.id.txtCountry);
            txtOdirun = itemView.findViewById(R.id.txtOdirun);
            txtT20run = itemView.findViewById(R.id.txtT20run);
            txtTestrun = itemView.findViewById(R.id.txtTestrun);
            txtWickets = itemView.findViewById(R.id.txtWickets);
        }
    }
}
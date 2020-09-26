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
import com.example.livecricketscore.model.Details;
import com.example.livecricketscore.model.UpcomingDetails;
import com.example.livecricketscore.url.URL;

import java.util.List;


public class UpcomingmatchesAdapter extends RecyclerView.Adapter<UpcomingmatchesAdapter.DetailsViewHolder> {
    public static String country11;
    public static String country22;
    public static String matchdetails1;
    public static String startdetail;

    Context context;
    List<UpcomingDetails> DetailsList;
    public FragmentManager fragmentManager;
    final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);

    public UpcomingmatchesAdapter(Context context,List<UpcomingDetails>DetailsList){
        this.context = context;
        this.DetailsList = DetailsList;
    }



    @NonNull
    @Override
    public UpcomingmatchesAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingmatches_layout, parent, false);

        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingmatchesAdapter.DetailsViewHolder holder, int position) {
        final UpcomingDetails UpcomingDetails = DetailsList.get(position);
        holder.txtCountry11.setText(UpcomingDetails.getCountry11());
        holder.txtCountry22.setText(UpcomingDetails.getCountry22());
        holder.txtMatchDetails1.setText(UpcomingDetails.getMatchdetails1());
        holder.txtStartdetail.setText(UpcomingDetails.getStartdetail());


    }

    @Override
    public int getItemCount() {
        return DetailsList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        TextView txtCountry11, txtCountry22, txtMatchDetails1, txtStartdetail;


        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCountry11 = itemView.findViewById(R.id.txtCountry11);
            txtCountry22 = itemView.findViewById(R.id.txtCountry22);
            txtMatchDetails1 = itemView.findViewById(R.id.txtMatchDetails1);
            txtStartdetail = itemView.findViewById(R.id.txtStartdetail);
        }
    }
}

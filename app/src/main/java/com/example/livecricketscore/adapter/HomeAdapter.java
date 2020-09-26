package com.example.livecricketscore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livecricketscore.R;
import com.example.livecricketscore.api.LiveCricketScoreAPI;
import com.example.livecricketscore.model.Details;
import com.example.livecricketscore.url.URL;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.DetailsViewHolder> {


    public static String country1;
    public static String country2;
    public static String run1;
    public static String run2;
    public static String over1;
    public static String over2;
    public static String target;
    public static String matchdetails;
    public static String result;

    Context context;
    List<Details> DetailsList;
    public FragmentManager fragmentManager;
    final LiveCricketScoreAPI liveCricketScoreAPI = URL.getInstance().create(LiveCricketScoreAPI.class);

    public HomeAdapter(Context context, List<Details> DetailsList) {
        this.context = context;

        this.DetailsList = DetailsList;

    }


    @NonNull
    @Override
    public HomeAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_layout, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.DetailsViewHolder holder, int position) {
        final Details Details = DetailsList.get(position);
        holder.txtCountry1.setText(Details.getCountry1());
        holder.txtCountry2.setText( Details.getCountry2());
        holder.txtRun1.setText( Details.getRun1());
        holder.txtRun2.setText( Details.getRun2());
        holder.txtOver1.setText(Details.getOver1());
        holder.txtOver2.setText( Details.getOver2());
        holder.txtTarget.setText(Details.getTarget());
        holder.txtMatchDetails.setText( Details.getMatchdetails());
        holder.txtResult.setText(Details.getResult());



//        Picasso.get().load(URL.image_url + Details)
//                .centerCrop()
//                .resize(500, 500)
//                .into(holder.imageView);


    }

    @Override
    public int getItemCount()  { return DetailsList.size();}
        public class DetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtCountry1, txtCountry2, txtRun1, txtRun2, txtOver1, txtOver2, txtTarget, txtMatchDetails, txtResult;

            public DetailsViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);

                txtCountry1 = itemView.findViewById(R.id.txtCountry1);
                txtCountry2 = itemView.findViewById(R.id.txtCountry2);
                txtRun1 = itemView.findViewById(R.id.txtRun1);
                txtRun2 = itemView.findViewById(R.id.txtRun2);
                txtOver1 = itemView.findViewById(R.id.txtOver1);
                txtOver2 = itemView.findViewById(R.id.txtOver2);
                txtTarget = itemView.findViewById(R.id.txtTarget);
                txtMatchDetails = itemView.findViewById(R.id.txtMatchDetails);
                txtResult = itemView.findViewById(R.id.txtStartdetail);

            }
        }
    }

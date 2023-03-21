package com.example.two.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.two.R;
import com.example.two.model.Movie;

import java.util.ArrayList;

public class RankAllAdapter extends RecyclerView.Adapter<RankAllAdapter.ViewHolder> {



    Context context;
    ArrayList<Movie> movieArrayList;


    public RankAllAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }


    @NonNull
    @Override
    public RankAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_row,parent,false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RankAllAdapter.ViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
//        Glide.with(context).load(holder.)

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView poster1;
        TextView titleSearchMovie1;
        TextView rateSearchMovie1;
        // 장르
        TextView textView7;
        // 출시년도
        TextView textView46;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster1 = itemView.findViewById(R.id.poster1);
            titleSearchMovie1 = itemView.findViewById(R.id.titleSearchMovie1);
            rateSearchMovie1 = itemView.findViewById(R.id.rateSearchMovie1);
            textView7 = itemView.findViewById(R.id.textView7);
            textView46 = itemView.findViewById(R.id.textView46);


        }
    }
}

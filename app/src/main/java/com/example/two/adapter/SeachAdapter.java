package com.example.two.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.two.R;
import com.example.two.model.Movie;
import com.example.two.model.Seach;

import java.util.ArrayList;

public class SeachAdapter extends RecyclerView.Adapter<SeachAdapter.ViewHolder> {
    Context context;

    ArrayList<Seach> seachArrayList;

    public SeachAdapter(Context context, ArrayList<Seach> seachArrayList) {
        this.context = context;
        this.seachArrayList = seachArrayList;
    }

    @NonNull
    @Override
    public SeachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchmovie_row,parent,false);

        return new SeachAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeachAdapter.ViewHolder holder, int position) {
        Seach seach = seachArrayList.get(position);
        Log.i("SIGN","OK");
        Glide.with(context)
                .load(seach.getImgUrl())
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(holder.searchMoviePoster);

        holder.titleSearchMovie1.setText(seach.getTitle());
        holder.rateSearchMovie1.setText(seach.getContentRating().toString());
        String[] splitText = seach.getGenre().replace("'","").split(",");
        holder.genreSearchMovie1.setText(splitText[0]);
        String[] splitText2 = seach.getCreatedYear().split("-");
        holder.dateSearchMovie1.setText(splitText2[0]);

    }

    @Override
    public int getItemCount() {
        return seachArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView searchMoviePoster ;
        TextView titleSearchMovie1;
        TextView rateSearchMovie1;
        TextView genreSearchMovie1;
        TextView dateSearchMovie1;

        LinearLayout layoutMovie1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchMoviePoster = itemView.findViewById(R.id.searchMoviePoster);
            titleSearchMovie1 = itemView.findViewById(R.id.titleSearchMovie1);
            rateSearchMovie1 = itemView.findViewById(R.id.rateSearchMovie1);
            genreSearchMovie1 = itemView.findViewById(R.id.genreSearchMovie1);
            dateSearchMovie1 = itemView.findViewById(R.id.dateSearchMovie1);
            layoutMovie1 = itemView.findViewById(R.id.layoutMovie1);

            layoutMovie1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}

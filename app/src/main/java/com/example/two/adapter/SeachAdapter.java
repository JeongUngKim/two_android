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
                .inflate(R.layout.serach_row,parent,false);

        return new SeachAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeachAdapter.ViewHolder holder, int position) {
        Seach seach = seachArrayList.get(position);
        holder.textView10.setText(seach.getTitle());
        holder.textView11.setText(seach.getVote_average());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w92"+seach.getPoster_path())
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(holder.imageView6);

    }

    @Override
    public int getItemCount() {
        return seachArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView6;
        TextView textView10;
        TextView textView11;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);
            imageView6 = itemView.findViewById(R.id.imageView6);
        }
    }
}

package com.example.two.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.two.R;
import com.example.two.fragment.HomeFragment;
import com.example.two.model.Movie;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    HomeFragment homeFragment;

    ArrayList<Movie> movieArrayList;

    public MainAdapter( HomeFragment homeFragment, ArrayList<Movie> movieArrayList) {
        this.homeFragment=homeFragment;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serach_row,parent,false);


        return new MainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.textView10.setText(movie.getTitle());
        holder.textView11.setText(movie.getVote_average());

        Glide.with(homeFragment)
                .load("https://image.tmdb.org/t/p/w342"+movie.getPoster_path())
                .override(300,300)
                .into(holder.imageView6);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView6;
        TextView textView10;
        TextView textView11;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);
            imageView6 = itemView.findViewById(R.id.searchPoster);

            imageView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Log.i("INDEX", String.valueOf(index));
                    homeFragment.isDetail(index);
                }
            });
        }
    }
}

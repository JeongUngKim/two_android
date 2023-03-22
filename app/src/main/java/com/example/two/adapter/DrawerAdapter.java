package com.example.two.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.two.R;
import com.example.two.model.User;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String,String>> set;

    public DrawerAdapter(Context context ,HashSet<HashMap<String,String>> hashdata){
        this.context = context;
        set = new ArrayList<>(hashdata);
        Log.i("setdata",set.get(0).get("nickname"));
    }
    public void updatedata(HashSet<HashMap<String,String>> hashdata){
        set = new ArrayList<>(hashdata);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.drawer_row,parent,false);
        return new DrawerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerAdapter.ViewHolder holder, int position) {
        HashMap<String,String> dataset = set.get(position);
        Log.i("data",dataset.get("nickname"));
        String profileUrl = dataset.get("profileUrl");
        String nickname = dataset.get("nickname");
        holder.textView22.setText(nickname);
        Glide.with(context).load(profileUrl).into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return set.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProfile;
        TextView textView22;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            textView22 = itemView.findViewById(R.id.textView22);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }
}

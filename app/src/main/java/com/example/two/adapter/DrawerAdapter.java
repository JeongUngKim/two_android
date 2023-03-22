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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String,String>> set=new ArrayList<>();
    HashMap<String, String> data = new HashMap<>();
    public DrawerAdapter(Context context ,HashSet<HashMap<String,String>> hashdata){
        this.context = context;
        data.put("nickname","바봉");
        data.put("profileUrl","https://ungjk-test.s3.ap-northeast-2.amazonaws.com/rrc0777@naver.com_profileImg.jpg");

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

        String profileUrl = data.get("profileUrl");
        String nickname = data.get("nickname");
        holder.textView22.setText(nickname);
        Glide.with(context).load(profileUrl).into(holder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return 0;
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

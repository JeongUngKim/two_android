package com.example.two.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.two.MainActivity;
import com.example.two.R;
import com.example.two.fragment.PartyFragment;
import com.example.two.model.Chat;
import com.example.two.model.Movie;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {

    Context context;

    ArrayList<Chat> chatArrayList;

    public ChatRoomAdapter(Context context, ArrayList<Chat> chatArrayList) {
        this.context = context;
        this.chatArrayList = chatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.party_row,parent,false);


        return new ChatRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Chat chat = chatArrayList.get(position);
            holder.partyName.setText(chat.getTitle());
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView partyName;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            partyName = itemView.findViewById(R.id.partyName);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }

}


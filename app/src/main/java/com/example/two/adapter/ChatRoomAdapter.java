package com.example.two.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.two.PartyChatActivity;
import com.example.two.R;
import com.example.two.config.Config;
import com.example.two.fragment.PartyFragment;
import com.example.two.model.Chat;
import com.example.two.model.Movie;
import com.example.two.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
            holder.tag.setText(chat.getService());
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView partyName;
        TextView headCount;
        TextView tag;

        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            partyName = itemView.findViewById(R.id.partyName);
            cardView = itemView.findViewById(R.id.cardView);
            headCount = itemView.findViewById(R.id.headCount);
            tag = itemView.findViewById(R.id.tag);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Chat chat = chatArrayList.get(index);

                    //파일 업로드
                    //1. Firebase Database에 nickName, profileUrl을 저장
                    //firebase DB관리자 객체 생성
//                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//                    //'profiles'라는 객체 생성
//                    DatabaseReference profileRef = firebaseDatabase.getReference("chatroom");
//
//                    //닉네임을 key 식별자로 하고 프로필 이미지의 주소를 값으로 저장
//                    profileRef.child(partyName).setValue(partyName);
//                    profileRef.child(partyName).setValue(imgUri);
//                    profileRef.child(partyName).setValue(nickname);

                    Intent intent = new Intent(context, PartyChatActivity.class);
                    intent.putExtra("partyBoardId",chat.getPartyBoardId());
                    intent.putExtra("index", index);
                    intent.putExtra("Id",chat.getUserId());
                    context.startActivity(intent);


                }

            });

        }
    }




}


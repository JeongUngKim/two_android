package com.example.two;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.two.adapter.ChatAdapter;
import com.example.two.model.MessageItem;
import com.example.two.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class PartyChatActivity extends AppCompatActivity {
    EditText editMsg;
    ListView listView;

    Button btn;
    ArrayList<MessageItem> messageItems = new ArrayList<>();
    ChatAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference chatRef;


    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_chat);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setTitle("넷플릭스 채팅방");
        }

        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("user");

        editMsg = findViewById(R.id.editMsg);
        listView = findViewById(R.id.listview);
        btn = findViewById(R.id.btn);
        adapter = new ChatAdapter(messageItems,getLayoutInflater(),user);
        listView.setAdapter(adapter);

        firebaseDatabase= firebaseDatabase.getInstance();
        chatRef = firebaseDatabase.getReference("대연님"+"/"+intent.getIntExtra("partyBoardId",0));
        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageItem messageItem= snapshot.getValue(MessageItem.class);
                messageItems.add(messageItem);
                adapter.notifyDataSetChanged();
                listView.setSelection(messageItems.size()-1); //리스트뷰의 마지막 위치로 스크롤 위치 이동


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName= user.getNickname();
                String message= editMsg.getText().toString();
                String pofileUrl= user.getImgUrl();

                Calendar calendar= Calendar.getInstance(); //현재 시간을 가지고 있는 객체
                String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE); //14:16

                MessageItem messageItem= new MessageItem(nickName,message,time,pofileUrl);
                //'char'노드에 MessageItem객체를 통해
                chatRef.push().setValue(messageItem );
                editMsg.setText("");

                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });

    }
}
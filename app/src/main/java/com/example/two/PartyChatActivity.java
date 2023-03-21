package com.example.two;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.two.Api.NetworkClient2;
import com.example.two.Api.UserApi;
import com.example.two.adapter.ChatAdapter;
import com.example.two.config.Config;
import com.example.two.fragment.PartyFragment;
import com.example.two.model.MessageItem;
import com.example.two.model.User;
import com.example.two.model.UserList;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PartyChatActivity extends AppCompatActivity {


    EditText editMsg;
    ListView listView;

    Button btn;
    ArrayList<MessageItem> messageItems = new ArrayList<>();
    ChatAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference chatRef;

    // 사이드바 관련 멤버 변수
    DrawerLayout drawerLayout;
    View drawerView;

    public static Context context;
    HashSet<HashMap<String,String>> set=new HashSet<>();;
    int index;
    User user;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_chat);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }
        Intent intent = getIntent();

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);

        context = this;

        user = new User();
        user.setProfileImgUrl(sp.getString("imgUrl", ""));
        user.setNickname(sp.getString("nickname", ""));


        editMsg = findViewById(R.id.editMsg);
        listView = findViewById(R.id.listview);

        // 사이드 바 연결
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);

        btn = findViewById(R.id.btn);
        adapter = new ChatAdapter(messageItems,getLayoutInflater(),user);
        listView.setAdapter(adapter);

        for( int i = 0 ; i < messageItems.size();i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("nickname",messageItems.get(i).getNickname());
            data.put("profileUrl",messageItems.get(i).getProfileUrl());
            set.add(data);
        }
        firebaseDatabase= firebaseDatabase.getInstance();
        chatRef = firebaseDatabase.getReference("chatroom"+"/"+intent.getIntExtra("partyBoardId",0));
        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageItem messageItem= snapshot.getValue(MessageItem.class);
                messageItems.add(messageItem);
                adapter.notifyDataSetChanged();
                listView.setSelection(messageItems.size()-1); //리스트뷰의 마지막 위치로 스크롤 위치 이동
                HashMap<String,String> data = new HashMap<>();
                data.put("nickname",messageItem.getNickname());
                data.put("profileUrl",messageItem.getProfileUrl());
                set.add(data);

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
                String pofileUrl= user.getProfileImgUrl();

                Calendar calendar= Calendar.getInstance(); //현재 시간을 가지고 있는 객체
                String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE); //14:16

                MessageItem messageItem= new MessageItem(nickName,message,time,pofileUrl);
                //'char'노드에 MessageItem객체를 통해
                chatRef.push().setValue(messageItem);
                editMsg.setText("");

                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnCheck){
            drawerLayout.openDrawer(drawerView);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO : 현재는 파티채팅방에서 백 버튼을 누르면 메인 액티비티로 가짐 파티 프래그먼트로 가지게 바꿔야함
        Intent intent = new Intent();
        setResult(100,intent);
        finish();
    }
}
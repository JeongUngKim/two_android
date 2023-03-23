package com.example.two;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toolbar;

import com.example.two.Api.ChatApi;
import com.example.two.Api.NetworkClient2;
import com.example.two.Api.UserApi;
import com.example.two.adapter.ChatAdapter;
import com.example.two.adapter.DrawerAdapter;
import com.example.two.config.Config;
import com.example.two.fragment.PartyFragment;
import com.example.two.model.MessageItem;
import com.example.two.model.PartyCheckRes;
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

    int partyBoardId;


    int index;
    User user;
    RecyclerView drawerRecyclerView;
    DrawerAdapter drawerAdapter;

    Button btnId;
    Button btnPay;

    PartyCheckRes partyCheckRes;
    HashSet<HashMap<String,String>> hash = new HashSet<>();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_chat);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);

        HashMap<String,String> data = new HashMap<>();
        data.put("nickname",user.getNickname());
        data.put("profileUrl",user.getProfileImgUrl());
        hash.add(data);

        editMsg = findViewById(R.id.editMsg);
        listView = findViewById(R.id.listview);
        partyBoardId = getIntent().getIntExtra("partyBoardId",0);


        // 사이드 바 연결
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);

        drawerRecyclerView = findViewById(R.id.drawerRecyclerView);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerAdapter = new DrawerAdapter(PartyChatActivity.this,hash,user);
        drawerRecyclerView.setAdapter(drawerAdapter);

        btnId = findViewById(R.id.btnId);
        btnPay = findViewById(R.id.btnPay);

        btn = findViewById(R.id.btn);
        adapter = new ChatAdapter(messageItems,getLayoutInflater(),user);
        listView.setAdapter(adapter);

        firebaseDatabase= firebaseDatabase.getInstance();
        chatRef = firebaseDatabase.getReference("chatroom"+"/"+partyBoardId);
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
                hash.add(data);
                drawerAdapter.updatedata(hash);
                partycheked();
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
        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] memberlist = partyCheckRes.getMemberEmail();
                Boolean paychecker;
                for(String member : memberlist){
                    if(user.getUserEmail().equals(member)){

                    }
                }
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        Intent intent = new Intent();
        setResult(100,intent);
        finish();
    }

    public void partycheked(){
        Retrofit retrofit = NetworkClient2.getRetrofitClient(getApplicationContext());

        ChatApi api = retrofit.create(ChatApi.class);

        Call<PartyCheckRes> call = api.getCheckParty(partyBoardId);
        call.enqueue(new Callback<PartyCheckRes>() {
            @Override
            public void onResponse(Call<PartyCheckRes> call, Response<PartyCheckRes> response) {
                if(response.code() == 200 ){
                    partyCheckRes = response.body();
                    drawerAdapter.setPartyCheckRes(partyCheckRes);
                }
            }

            @Override
            public void onFailure(Call<PartyCheckRes> call, Throwable t) {

            }
        });
    }
}

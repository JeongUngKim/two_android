package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.two.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PartyAddActivity extends AppCompatActivity {
    EditText txtPartyName;
    EditText txtOttName;
    EditText txtOttPassword;

    Button btnCreateParty;

    User user;


    boolean isFirst= true; // 첫 실행을 구분하기위한 멤버변수
    boolean isChanged= false; //프로필 변경 확인을 위한 멤버변수

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_add);

        txtPartyName=findViewById(R.id.txtPartyName);
        txtOttName=findViewById(R.id.txtOttName);
        txtOttPassword=findViewById(R.id.txtOttPassword);
        btnCreateParty=findViewById(R.id.btnCreateParty);



        user = (User) getIntent().getSerializableExtra("user");
        // 파이어베이스 연결항목.
        btnCreateParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();


            }
        });


    }


    void saveData(){
        //EditText의 닉네임 가져오기
        String partyName = txtPartyName.getText().toString().trim();
        String imgUri =user.getImgUrl();
        String nickname=user.getNickname();
        //이미지 미선택 예외처리
        if(imgUri==null) return;

        //Firebase storage에 이미지 저장하기 위해 날짜 형식으로 파일이름 바꾸기
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss"); //20191024111224
        String fileName= sdf.format(new Date())+".png";



        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



        //Firebase storage에 저장하기
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        final StorageReference imgRef= firebaseStorage.getReference("profileImages/"+fileName);

        //파일 업로드


        //1. Firebase Database에 nickName, profileUrl을 저장
        //firebase DB관리자 객체 생성
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        //'profiles'라는 객체 생성
        DatabaseReference profileRef= firebaseDatabase.getReference("chatroom");

        //닉네임을 key 식별자로 하고 프로필 이미지의 주소를 값으로 저장
        profileRef.child("1").setValue(partyName);
        profileRef.child("1").setValue(imgUri);
        profileRef.child("1").setValue(nickname);

                        //2. 내 phone에 nickName, profileUrl을 저장


                        //저장이 완료되었으니 ChatActivity로 전환





    }
}
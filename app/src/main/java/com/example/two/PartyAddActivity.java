package com.example.two;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.two.fragment.PartyFragment;
import com.example.two.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PartyAddActivity extends AppCompatActivity {
    EditText txtPartyName;
    EditText txtOttName;
    EditText txtOttPassword;
    TextView txtEndDate;

    Button btnCreateParty;
    ImageView imgBack;

    String date = "";

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
        txtEndDate = findViewById(R.id.txtEndDate);
        imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PartyAddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar current = Calendar.getInstance();

                new DatePickerDialog(
                        PartyAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                Log.i("MEMO_APP", "년도 : " + i + ", 월 :"+i1+", 일 :"+i2);
                                // i : 년도, i1 : 월(0~11) , i2 : 일

                                int month = i1 + 1;
                                String strMonth;
                                if(month < 10){
                                    strMonth = "0"+month;
                                }else{
                                    strMonth = ""+month;
                                }

                                String strDay;
                                if(i2 < 10){
                                    strDay = "0"+i2;
                                }else{
                                    strDay= ""+i2;
                                }

                                date = i + "-" + strMonth + "-" + strDay;
                                txtEndDate.setText(date);

                            }
                        },
                        current.get(Calendar.YEAR),
                        current.get(Calendar.MONTH),
                        current.get(Calendar.DAY_OF_MONTH)
                ).show();

            }
        });



        user = (User) getIntent().getSerializableExtra("user");
        // 파이어베이스 연결항목.
        btnCreateParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int partyBoardId = 1;
                Intent intent = new Intent(PartyAddActivity.this , PartyChatActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("partyBoardId",partyBoardId);

                startActivity(intent);
                finish();
                //saveData();


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
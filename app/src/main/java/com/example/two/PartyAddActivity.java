package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PartyAddActivity extends AppCompatActivity {
    EditText txtPartyName;
    EditText txtOttName;
    EditText txtOttPassword;

    Button btnCreateParty;

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


    }
}
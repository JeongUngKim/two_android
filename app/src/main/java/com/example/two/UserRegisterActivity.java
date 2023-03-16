package com.example.two;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.two.model.User;
import com.example.two.model.UserRes;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import com.example.two.Api.NetworkClient2;
import com.example.two.config.Config;
import com.example.two.config.RegisterApi;
import com.example.two.model.Res;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRegisterActivity extends AppCompatActivity {
    Button buttonRegister;


    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    EditText editTextTextNickName;

    EditText editName;

    EditText editAnswer;

    EditText editAge;
    Uri imgUri;

    int gender;

    private File photoFile;

    private File profileImg;


    CircleImageView circle_iv;

    String email;

    String password;
    String nickname;


//    String imgUrl;
//
//    User user = new User();

    Spinner genderSp;
    Spinner questionSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextNickName = findViewById(R.id.editTextTextNickName);
        editAge = findViewById(R.id.editAge);
        editName = findViewById(R.id.editName);
        editAnswer = findViewById(R.id.editAnswer);
        genderSp = findViewById(R.id.genderSp);
        questionSp = findViewById(R.id.questionSp);


        circle_iv = findViewById(R.id.circle_iv);

        buttonRegister = findViewById(R.id.buttonRegister);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();
//                addNetworkdata();
                Intent intent = new Intent(UserRegisterActivity.this, UserChoiceActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void clickImage(View view) {
        //프로필 이미지 선택하도록 Gallery 앱 실행
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    imgUri = data.getData();


                    Picasso.get().load(imgUri).into(circle_iv);
                    Log.i("IMGURL", String.valueOf(imgUri));
                }
                break;
        }
    }

    void saveData() {
        //EditText의 닉네임 가져오기

        String email = editTextTextEmailAddress.getText().toString().trim();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (pattern.matcher(email).matches() == false) {
            Toast.makeText(UserRegisterActivity.this, "이메일형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = editTextTextPassword.getText().toString().trim();
        if (password.length() < 4 || password.length() > 12) {
            Toast.makeText(UserRegisterActivity.this, "비밀번호형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        String nickname = editTextTextNickName.getText().toString().trim();
        if (nickname.isEmpty()) {
            Toast.makeText(UserRegisterActivity.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = editName.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String answer = editAnswer.getText().toString().trim();
        String genderSelect = genderSp.getSelectedItem().toString();


        if(genderSelect == "남자"){
            gender = 1;
        }else if(genderSelect == "여자"){
            gender = 0;
        }

        int questionNum = questionSp.getSelectedItemPosition();



        //이미지 미선택 예외처리
        if (imgUri == null) return;

        //Firebase storage에 이미지 저장하기 위해 날짜 형식으로 파일이름 바꾸기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss"); //20191024111224
        String fileName = sdf.format(new Date()) + ".png";

        fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        photoFile = getPhotoFile(fileName);


        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        Retrofit retrofit = NetworkClient2.getRetrofitClient(UserRegisterActivity.this);
        RegisterApi api = retrofit.create(RegisterApi.class);

        // 멀티파트로 파일을 보내는 경우, 파일 파라미터를 만든다.



        RequestBody nameBody = RequestBody.create(name, MediaType.parse("text/plain"));
        RequestBody emailBody = RequestBody.create(email, MediaType.parse("text/plain"));
        RequestBody passwordBody = RequestBody.create(password, MediaType.parse("text/plain"));
        RequestBody nicknameBody = RequestBody.create(nickname, MediaType.parse("text/plain"));
        RequestBody genderBody = RequestBody.create(String.valueOf(gender),MediaType.parse("text/plain"));
        RequestBody ageBody = RequestBody.create(age, MediaType.parse("text/plain"));
        RequestBody questionNumBody = RequestBody.create(String.valueOf(questionNum+1), MediaType.parse("text/plain"));
        RequestBody answerBody = RequestBody.create(answer, MediaType.parse("text/plain"));
//        User user = new User(name,nickname,email,password,gender,questionNum+1,answer);

//        RequestBody userBody = RequestBody.create(String.valueOf(user),MediaType.parse("text/plain"));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("nickname", nickname);
            jsonObject.put("gender", String.valueOf(gender));
            jsonObject.put("age", age );
            jsonObject.put("questionNum", String.valueOf(questionNum+1));
            jsonObject.put("answer", answer);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Log.i("HASH",String.valueOf(jsonObject));



//        RequestBody profileImgBody = RequestBody.create(profileImg, MediaType.parse("image/jpg"));

        RequestBody fileBody = RequestBody.create(photoFile, MediaType.parse("image/jpg"));
        MultipartBody.Part profileImg = MultipartBody.Part.createFormData("profileImg", photoFile.getName(), fileBody);


        // 헤더에 들어갈 억세스토큰 가져온다.
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String accessToken = "Bearer " + sp.getString(Config.ACCESS_TOKEN, "");

        Call<UserRes> call = api.resgister(jsonObject,profileImg);

        call.enqueue(new Callback<UserRes>() {
            @Override
            public void onResponse(Call<UserRes> call, Response<UserRes> response) {
                if (response.isSuccessful()) {

                    finish();

                } else {

                }

            }

            @Override
            public void onFailure(Call<UserRes> call, Throwable t) {


            }
        });

    }


//        Uri fileProvider = FileProvider.getUriForFile(UserRegisterActivity.this,
//                "com.blockent.postingapp.fileprovider", photoFile);
//        i.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);






    private File getPhotoFile(String fileName) {
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try{
            return File.createTempFile(fileName,"", storageDirectory);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}






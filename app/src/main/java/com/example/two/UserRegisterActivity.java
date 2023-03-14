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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    Uri imgUri;

    private File photoFile;

    CircleImageView circle_iv;

    String email;

    String password;
    String nickname;

    String imgUrl;

    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextNickName = findViewById(R.id.editTextTextNickName);

        circle_iv = findViewById(R.id.circle_iv);

        buttonRegister = findViewById(R.id.buttonRegister);



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = editTextTextEmailAddress.getText().toString().trim();
                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if(pattern.matcher(email).matches()==false){
                    Toast.makeText(UserRegisterActivity.this, "이메일형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = editTextTextPassword.getText().toString().trim();
                if(password.length()<4||password.length()>12){
                    Toast.makeText(UserRegisterActivity.this, "비밀번호형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nickname = editTextTextNickName.getText().toString().trim();
                if(nickname.isEmpty()){
                    Toast.makeText(UserRegisterActivity.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

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
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri= data.getData();


                    Picasso.get().load(imgUri).into(circle_iv);
                    Log.i("IMGURL",String.valueOf(imgUri));
                }
                break;
        }
    }

    void saveData(){
        //EditText의 닉네임 가져오기
        nickname = editTextTextNickName.getText().toString().trim();


        //이미지 미선택 예외처리
        if(imgUri==null) return;

        //Firebase storage에 이미지 저장하기 위해 날짜 형식으로 파일이름 바꾸기
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss"); //20191024111224
        String fileName= sdf.format(new Date())+".png";

        fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        photoFile = getPhotoFile(fileName);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri fileProvider = FileProvider.getUriForFile(UserRegisterActivity.this,
                "com.blockent.postingapp.fileprovider", photoFile);
        i.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        //Firebase storage에 저장하기
        FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
        final StorageReference imgRef= firebaseStorage.getReference("profileImages/"+fileName);

        //파일 업로드
        UploadTask uploadTask=imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //firebase storage의 이미지 파일 다운로드 URL을 얻어오기
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //파라미터로 firebase의 저장소에 저장되어 있는 이미지에 대한 다운로드 주소(URL)을 문자열로 얻어오기
                        imgUrl =imgUri.toString();
                        Toast.makeText(UserRegisterActivity.this, "프로필 저장 완료", Toast.LENGTH_SHORT).show();

                        //1. Firebase Database에 nickName, profileUrl을 저장
                        //firebase DB관리자 객체 생성
                        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                        //'profiles'라는 객체 생성
                        DatabaseReference profileRef= firebaseDatabase.getReference("profiles");

                        //닉네임을 key 식별자로 하고 프로필 이미지의 주소를 값으로 저장
                        profileRef.child(nickname).setValue(imgUrl);

                        //2. 내 phone에 nickName, profileUrl을 저장


                        //저장이 완료되었으니 ChatActivity로 전환


                    }
                });
            }
        });
    }

    public void addNetworkdata() {
        Retrofit retrofit = NetworkClient2.getRetrofitClient(UserRegisterActivity.this);

        //2-2.api 패키지에 있는 인터페이스 생성
        RegisterApi api = retrofit.create(RegisterApi.class);

        //2-3. 보낼 데이터 만들기
        User user = new User(email, password, nickname);

        RequestBody fileBody = RequestBody.create(photoFile, MediaType.parse("image/jpg"));
        MultipartBody.Part profileImg = MultipartBody.Part.createFormData("photo", photoFile.getName(), fileBody);
        //2-4. api 호출
        Call<UserRes> call = api.resgister(profileImg,user);

        //2-5. 서버로부터 받아온 응답을 처리하는 코드 작성
        call.enqueue(new Callback<UserRes>() {
            @Override
            public void onResponse(Call<UserRes> call, Response<UserRes> response) {
                // 프로그레스 다이얼로그가 있으면, 나타나지 않게 해준다.

                // 서버에서 보낸 응답이 200ok
                if (response.isSuccessful()) {
                    Log.i("APP", response.toString());

                    UserRes res = response.body();

                    SharedPreferences sp =
                            getApplication().getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Config.ACCESS_TOKEN, res.getAccess_token());
                    editor.apply();

                    Intent intent = new Intent(UserRegisterActivity.this, UserChoiceActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.i("APP", response.toString());
                }


//                        Log.i("APP",res.getResult());
//                        Log.i("APP",res.getAccess_token());

                // 억세스토큰은, api 할때마다 헤더에서 사용하므로
                // 회원가입이나 로그인이 끝나면, 파일로 꼭 저장해 놔야 한다.


                //3. 데이터를 이상없이 처리하면
                // 메인액티비티를 화면에 나오게 한다.

            }

            @Override
            public void onFailure(Call<UserRes> call, Throwable t) {
//                        dismissProgress();

            }
        });
    }

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






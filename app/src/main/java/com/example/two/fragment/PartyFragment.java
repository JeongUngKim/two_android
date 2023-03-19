package com.example.two.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.two.Api.NetworkClient1;
import com.example.two.Api.NetworkClient2;
import com.example.two.MainActivity;
import com.example.two.PartyAddActivity;
import com.example.two.R;
import com.example.two.UserRegisterActivity;
import com.example.two.adapter.ChatRoomAdapter;
import com.example.two.adapter.MainAdapter;
import com.example.two.Api.ChatApi;
import com.example.two.config.Config;
import com.example.two.Api.MovieApi;
import com.example.two.model.Chat;
import com.example.two.model.ChatRoomList;
import com.example.two.model.Movie;
import com.example.two.model.MovieList;
import com.example.two.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartyFragment extends Fragment {
    MainActivity activity;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ImageButton btnCommunity;
    ImageButton btnHome;
    ImageButton btnFilter;
    ImageButton btnParty;
    ImageButton btnMy;
    Button partyBtn;

    RecyclerView recyclerView;
    ChatRoomAdapter adapter;

    ArrayList<Chat> chatArrayList = new ArrayList<>();

    Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PartyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PartyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartyFragment newInstance(String param1, String param2) {
        PartyFragment fragment = new PartyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_party, container, false);

        btnCommunity = view.findViewById(R.id.btnCommunity);
        btnHome = view.findViewById(R.id.btnHome);
        btnParty = view.findViewById(R.id.btnParty);
        btnFilter = view.findViewById(R.id.btnFilter);
        btnMy = view.findViewById(R.id.btnMy);
        partyBtn = view.findViewById(R.id.partyBtn);


        recyclerView = view.findViewById(R.id.recyclerView);
        getChatNetworkData();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        context = getActivity().getApplicationContext();
        sp = context.getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        User user = new User();
        user.setProfileImgUrl(sp.getString("imgUrl",""));
        user.setNickname(sp.getString("nickname",""));

        partyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                Intent intent = new Intent(getActivity(), PartyAddActivity.class);
                intent.putExtra("user",user);

                startActivity(intent);

                getActivity().finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        // 커뮤니티 프래그먼트 넘어가기
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });


        // 검색 프래그먼트 넘어가기
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(2);
            }
        });

        // 내 정보 프래그먼트 넘어가기
        btnMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3);
            }
        });


        return view;



    }

    private void getChatNetworkData() {
        Retrofit retrofit = NetworkClient2.getRetrofitClient(getActivity());

        ChatApi api = retrofit.create(ChatApi.class);

        Log.i("AAA", api.toString());

        Call<ChatRoomList> call = api.getChatingList(0);

        call.enqueue(new Callback<ChatRoomList>() {
            @Override
            public void onResponse(Call<ChatRoomList> call, Response<ChatRoomList> response) {

                if (response.isSuccessful()) {
                    // getNetworkData는 항상처음에 데이터를 가져오는 동작 이므로
                    // 초기화 코드가 필요
                    chatArrayList.clear();

                    // 데이터를 받았으니 리사이클러 표시

                    chatArrayList.addAll(response.body().getPartyBoard());

                    // 오프셋 처리하는 코드


                    adapter = new ChatRoomAdapter(getActivity(),chatArrayList);
                    recyclerView.setAdapter(adapter);
                    Log.i("RECYCLE", adapter.toString());

                } else {
                    Toast.makeText(getActivity(), "문제가 있습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ChatRoomList> call, Throwable t) {


            }


        });
    }

}
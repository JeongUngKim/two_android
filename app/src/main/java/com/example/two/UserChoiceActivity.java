package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;


public class UserChoiceActivity extends AppCompatActivity {
    ImageView actionView;
    ImageView adventureView;
    ImageView comedyView;
    ImageView documentaryView;
    ImageView dramaView;
    ImageView fantasyView;
    ImageView historyView;
    ImageView horrorView;
    ImageView mysteryView;
    ImageView crimeView;
    ImageView romanceView;
    ImageView scienceView;
    ImageView thrillerView;
    ImageView warView;
    ImageView westernView;
    ImageView musicView;
    ImageView kidsView;
    ImageView familyView;
    ImageView animationView;
    ImageView realityView;

    Button btnGenreChoice;

    int count  ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        actionView=findViewById(R.id.actionView);
        adventureView=findViewById(R.id.adventureView);
        comedyView=findViewById(R.id.comedyView);
        documentaryView=findViewById(R.id.documentaryView);
        dramaView=findViewById(R.id.dramaView);
        fantasyView=findViewById(R.id.fantasyView);
        historyView=findViewById(R.id.historyView);
        horrorView=findViewById(R.id.horrorView);
        mysteryView=findViewById(R.id.mysteryView);
        crimeView=findViewById(R.id.crimeView);
        romanceView=findViewById(R.id.romanceView);
        scienceView=findViewById(R.id.scienceView);
        warView=findViewById(R.id.warView);
        westernView=findViewById(R.id.westernView);
        musicView=findViewById(R.id.musicView);
        kidsView=findViewById(R.id.kidsView);
        familyView=findViewById(R.id.familyView);
        animationView=findViewById(R.id.animationView);
        realityView=findViewById(R.id.realityView);
        thrillerView=findViewById(R.id.thrillerView);
        btnGenreChoice=findViewById(R.id.btnGenreChoice);


        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/3PCRWLeqp5y20k6XVzcamZR3BWF.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(actionView);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    actionView.setColorFilter(Color.parseColor("#B3000000"));
                    if(count == 0){

                        count = 1;
                        Log.i("count", String.valueOf(count));
                    }
                    else{
                        count = count+1;
                        Log.i("count", String.valueOf(count));
                    }

                }
                else {
                    actionView.setColorFilter(Color.parseColor("#00000000"));
                    count = count - 1;
                    Log.i("count", String.valueOf(count));
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/rKgvctIuPXyuqOzCQ16VGdnHxKx.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .override(300,300)
                .into(adventureView);

        adventureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    adventureView.setColorFilter(Color.parseColor("#B3000000"));
                    if(count == 0){

                        count = 1;
                        Log.i("count", String.valueOf(count));
                    }
                    else{
                        count = count+1;
                        Log.i("count", String.valueOf(count));
                    }
                }
                else {
                    adventureView.setColorFilter(Color.parseColor("#00000000"));
                    count = count - 1;
                    Log.i("count", String.valueOf(count));
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/1XSYOP0JjjyMz1irihvWywro82r.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .override(300,300)
                .into(comedyView);

        comedyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    comedyView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    comedyView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/seeEc3xNttsx308AB0iFtqKVyjG.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(documentaryView);

        documentaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    documentaryView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    documentaryView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/q0xV5Lnq30JiegbVGBOvVwrgUDT.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(dramaView);

        dramaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    dramaView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    dramaView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/67QFqWFl8AJCeKBs1nadkU9145y.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(fantasyView);

        fantasyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    fantasyView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    fantasyView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/lwybGlEEJtXZM3ynY19PNwNlPn9.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(historyView);

        historyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    historyView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    historyView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/mCDSOfcVJfMkGUNrNpXWFO7oNBY.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(horrorView);

        horrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    horrorView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    horrorView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/kVwudGk77ilXN4TwJn0B3pZtS6N.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(mysteryView);

        mysteryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    mysteryView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    mysteryView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/cmWTZj9zzT9KFt3XyL0gssL7Ig8.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(crimeView);

        crimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    crimeView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    crimeView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/aqqXcUvcfJmGXJKBmEjUXThXAZv.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(romanceView);

        romanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    romanceView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    romanceView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/z56bVX93oRG6uDeMACR7cXCnAbh.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(scienceView);

        scienceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    scienceView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    scienceView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/66mrr3AK6grmkfGJ1wlCP8dkzjM.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(warView);

        warView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    warView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    warView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/qX7hudNY428lYyj8zz3w5Qgfsrd.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(westernView);

        westernView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    westernView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    westernView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/pQu93NuwR90AaCULzglVD5Ge4Ml.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(musicView);

        musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    musicView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    musicView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/juAmsE4GC6gql0L1HsDmABkLWuX.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(kidsView);

        kidsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    kidsView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    kidsView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/sLTAEFtjentQ5satiGdmv7o2f1C.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(familyView);

        familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    familyView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    familyView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/2SsEKPFypasKs4fPV8Nqrl9LD55.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(animationView);

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    animationView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    animationView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/ArntYxWZXArwegRh9IaOq2KD1JR.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(realityView);

        realityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    realityView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    realityView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/8XDLgSlM6hb01iEWNRbqxKdFnGD.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(thrillerView);

        thrillerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if(view.isSelected()){
                    thrillerView.setColorFilter(Color.parseColor("#B3000000"));
                    int count = 1;
                    Log.i("count", String.valueOf(count));
                }
                else {
                    thrillerView.setColorFilter(Color.parseColor("#00000000"));
                    int count = 0;
                }

            }
        });

        btnGenreChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserChoiceActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });











    }
}
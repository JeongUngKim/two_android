package com.example.two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

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

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/3PCRWLeqp5y20k6XVzcamZR3BWF.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)

                .into(actionView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/rKgvctIuPXyuqOzCQ16VGdnHxKx.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .override(300,300)
                .into(adventureView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/1XSYOP0JjjyMz1irihvWywro82r.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .override(300,300)
                .into(comedyView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/seeEc3xNttsx308AB0iFtqKVyjG.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(documentaryView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/q0xV5Lnq30JiegbVGBOvVwrgUDT.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(dramaView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/67QFqWFl8AJCeKBs1nadkU9145y.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(fantasyView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/lwybGlEEJtXZM3ynY19PNwNlPn9.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(historyView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/mCDSOfcVJfMkGUNrNpXWFO7oNBY.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(horrorView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/kVwudGk77ilXN4TwJn0B3pZtS6N.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(mysteryView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/cmWTZj9zzT9KFt3XyL0gssL7Ig8.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(crimeView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/aqqXcUvcfJmGXJKBmEjUXThXAZv.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(romanceView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/z56bVX93oRG6uDeMACR7cXCnAbh.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(scienceView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/66mrr3AK6grmkfGJ1wlCP8dkzjM.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(warView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/qX7hudNY428lYyj8zz3w5Qgfsrd.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(westernView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/pQu93NuwR90AaCULzglVD5Ge4Ml.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(musicView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/juAmsE4GC6gql0L1HsDmABkLWuX.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(kidsView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/sLTAEFtjentQ5satiGdmv7o2f1C.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(familyView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/2SsEKPFypasKs4fPV8Nqrl9LD55.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(animationView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/ArntYxWZXArwegRh9IaOq2KD1JR.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(realityView);

        Glide.with(UserChoiceActivity.this)
                .load("https://image.tmdb.org/t/p/w500/8XDLgSlM6hb01iEWNRbqxKdFnGD.jpg")
                .placeholder(R.drawable.baseline_person_outline_24)
                .into(thrillerView);










    }
}
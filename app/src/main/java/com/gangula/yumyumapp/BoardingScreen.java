package com.gangula.yumyumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BoardingScreen extends AppCompatActivity {
    TabLayout tabindicator;
    private ViewPager screenViewPager;
    IntoViewAdapter introViewPagerAdapter;
    int position = 0;
    Button buttonNext, getStartedButton;
    Animation buttonAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activate the full screen mode

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // hide the action bar
//        getSupportActionBar().hide();



        // check whether activity opened before or not

        if(restorePrefsData ()){
            Intent logScreen = new Intent(getApplicationContext(), LoginScreen.class);
            startActivity(logScreen);
            finish();
        }

        setContentView(R.layout.activity_boarding_screen);


        // init tab indicator

        tabindicator = findViewById(R.id.tab_indicator);

        buttonNext = findViewById(R.id.button_next);

        getStartedButton = findViewById(R.id.getStartedBTN);

        buttonAnime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);


        //fil the list from the picture
        List<ScreenItem> mlist = new ArrayList<>();

        mlist.add(new ScreenItem("Food Community", "simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries", R.drawable.img1));
        mlist.add(new ScreenItem("Safe and Secure", "but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop", R.drawable.img2));
        mlist.add(new ScreenItem("Recipes ", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here,", R.drawable.img3));

        screenViewPager = findViewById(R.id.screen_viewPager);
        introViewPagerAdapter = new IntoViewAdapter(this, mlist);
        screenViewPager.setAdapter(introViewPagerAdapter);


        // setup with view pager

        tabindicator.setupWithViewPager(screenViewPager);
        // next button click listener

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = screenViewPager.getCurrentItem();
                if (position < mlist.size()){
                    position++;
                    screenViewPager.setCurrentItem(position);
                }

                if (position == mlist.size() -1){
                    //TODO show the get started button and hide the indicator and the next button
                    loadLastScreen();
                }

            }
        });


        // tab layout add changes to the listener

        tabindicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() ==mlist.size() -1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // get started button click listener

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open login screen
                Intent loginScreen = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(loginScreen);
                // store a boolean value for check whether the user has been already checked the walk-through
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefsData() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroOpenedBefore = prefs.getBoolean("isIntroOpened", false);
        return isIntroOpenedBefore;
    }

    private void savePrefsData() {

        SharedPreferences  pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    //show the get started button and hide the indicator and the next button
    private void loadLastScreen() {
        buttonNext.setVisibility(View.INVISIBLE);
        getStartedButton.setVisibility(View.VISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);

        // animated get started button

        getStartedButton.setAnimation(buttonAnime);

    }
}
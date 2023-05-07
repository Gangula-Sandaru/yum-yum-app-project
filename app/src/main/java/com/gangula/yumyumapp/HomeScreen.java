package com.gangula.yumyumapp;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable; // todo comment not null
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gangula.yumyumapp.databinding.ActivityHomeScreenBinding;
import com.gangula.yumyumapp.fragment.AccountFragment;
import com.gangula.yumyumapp.fragment.AddFragment;
import com.gangula.yumyumapp.fragment.HomeFragment;
import com.gangula.yumyumapp.fragment.ListFragment;
import com.gangula.yumyumapp.fragment.MapsFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements ShakeDetector.OnShakeListener{
    ActivityHomeScreenBinding binding;


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get the SensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get the accelerometer sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Create a new ShakeDetector instance
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(this);

        ReplaceFragment(new HomeFragment());













        binding.navBottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) { //todo change remove null

                // select different screen
                switch (item.getItemId()){
                    case R.id.location:
                        ReplaceFragment(new MapsFragment());
                        break;

                    case R.id.add:
                        ReplaceFragment(new AddFragment());
                        break;

                    case R.id.home:
                        ReplaceFragment(new HomeFragment());
                        break;
                    case R.id.list:
                        ReplaceFragment(new ListFragment());
                        break;
                    case R.id.account:
                        ReplaceFragment(new AccountFragment());
                        break;
                }

                return true;
            }
        });

        //***********************************************************

    }

    private void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the ShakeDetector
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        // Unregister the ShakeDetector
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void onShake(int count) {
        // Do whatever you want when the phone is shaken
        // For example, close the activity
        finish();
    }

    // go to the account settings page
    public void goToActivity(View view) {
            Intent intent = new Intent(HomeScreen.this, AccountSettings.class);
            startActivity(intent);
    }
}
package com.gangula.yumyumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class LoginScreen extends AppCompatActivity implements ShakeDetector.OnShakeListener {
    private boolean passwordShow = false;

    DatabaseHelper MyDataDB;
    Boolean checkuserpass;


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Get the SensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // get data
        MyDataDB = new DatabaseHelper(this);

        // fire base initialize
        // firebase
//        DatabaseReference myFireDB = FirebaseDatabase.getInstance().getReference();

        // Get the accelerometer sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Create a new ShakeDetector instance
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(this);

        // store data in the cache
        // call share preference
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText textField = (EditText) findViewById(R.id.signUserFiled);
        final EditText passwdField = (EditText) findViewById(R.id.signPasswdFiled);

        final ImageView passwdShowIcon = (ImageView) findViewById(R.id.passwdShowIcon);
        final TextView singUpBtn = (TextView) findViewById(R.id.signupBtn);
        final Button singmBTN = (Button) findViewById(R.id.singMBtn);


        // create an on click lister for the sing in button

        singmBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = textField.getText().toString().trim().toLowerCase();
                String pass = passwdField.getText().toString().trim();


                Boolean result = MyDataDB.checkEmailAllReadyReg(user);

                if (user.equals("") || pass.equals("")){
//                    Toast.makeText(LoginScreen.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    textField.setError("Fill this Field");
                    passwdField.setError("Fill this Field");

                }
                else{
                    checkuserpass = MyDataDB.checkusernamepassword(user, pass);
                    if(!result){
//                    Toast.makeText(LoginScreen.this, "Wrong email address", Toast.LENGTH_SHORT).show();
                        textField.setError("Wrong Email Address");
                    }
                    else if (checkuserpass){
                        Toast.makeText(LoginScreen.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                        // get the user id
                        final int userID = MyDataDB.getUserIdByEmail(user);

//                        Log.i("DATA", String.valueOf(userID));

                        // the actual user id store in cache
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("LogUserId", String.valueOf(userID));
                        editor.apply();


                        Intent intent= new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginScreen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        passwdShowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checking if the password is showing or not
                if(passwordShow){
                    passwordShow = false;
                    passwdField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwdShowIcon.setImageResource(R.drawable.passwdshow);
                } else{
                    passwordShow = true;
                    passwdField.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwdShowIcon.setImageResource(R.drawable.passwdhide);
                }
                // move the cursor last of the text
                passwdField.setSelection(passwdField.length());
            }
        });

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
            }
        });




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

}

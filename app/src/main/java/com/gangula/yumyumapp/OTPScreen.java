package com.gangula.yumyumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OTPScreen extends AppCompatActivity {
    EditText OTPcode1, OTPcode2, OTPcode3, OTPcode4;
    TextView resentBTN;


    // create database object for the data retrieves
    DatabaseHelper get_data;

    // true after every second
    private boolean resendEnable = false;

    // resent time in second
    private int resentTime = 60;

    private int selectedEtPostion  = 0;
    private  Boolean register_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpscreen);
        // reg users

        // database creation
        get_data = new DatabaseHelper(this);

        // 4 boxes of the otp

        OTPcode1 = (EditText) findViewById(R.id.OPTB1);
        OTPcode2 = (EditText) findViewById(R.id.OPTB2);
        OTPcode3 = (EditText) findViewById(R.id.OPTB3);
        OTPcode4 = (EditText) findViewById(R.id.OPTB4);

        // resent button
        resentBTN = (TextView) findViewById(R.id.resentBtn);
        // verified button
        final Button verifyBTN = findViewById(R.id.verifyBTN);

        final TextView otpEmail = (TextView) findViewById(R.id.otpEmail);
        final TextView otpMobile = (TextView) findViewById(R.id.otpMobile);

        // getting email and phone number
        final String getFname = getIntent().getStringExtra("fname");
        final String getLname = getIntent().getStringExtra("lname");
        final String getEmail = getIntent().getStringExtra("email");
        final String getPhone = getIntent().getStringExtra("mobile");
        final String getPasswd = getIntent().getStringExtra("passwd");
        final String getConPasswd = getIntent().getStringExtra("conpasswd");

        // setting email and mobile to textView

        otpEmail.setText(getEmail);
        otpMobile.setText(getPhone);

        OTPcode1.addTextChangedListener(textWatcher);
        OTPcode2.addTextChangedListener(textWatcher);
        OTPcode3.addTextChangedListener(textWatcher);
        OTPcode4.addTextChangedListener(textWatcher);

        // default open keyboard at OPTcode

        showKeyboard(OTPcode1);


        // start resent time count downer

        startCountDownTimer();
        resentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resendEnable){
                    // handle your resend code here

                    // start new resent count down timer
                    startCountDownTimer();
                }
            }
        });

        verifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String generateOTP = OTPcode1.getText().toString() + OTPcode2.getText().toString() + OTPcode3.getText().toString() + OTPcode4.getText().toString();

                if (generateOTP.length() == 4){
                    // handle your verification here

                    // update the database with user values
                    register_user = get_data.insertData(getFname, getLname,getEmail,getPhone, getPasswd);
//                    register_user = get_data.insertData("gangula", "sandaru","gangula@gmail.com","1234", "343s4");


//                     display a success mgs
                    if(register_user) {
                        Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OTPScreen.this, LoginScreen.class));
                        finish();
                        Toast.makeText(getApplicationContext(), "Login to new account", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(), "Register Unsuccessful", Toast.LENGTH_SHORT).show();
                    }

                }

            }


        });

    }

    private void startCountDownTimer(){
        resendEnable = false;
        resentBTN.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resentTime * 1000 ,1000){

            @Override
            public void onTick(long millisUntilFinished){
                resentBTN.setText("Resend Code (" + (millisUntilFinished / 1000) + ")");
            }
            @Override
            public void onFinish(){
                resendEnable = true;
                resentBTN.setText("Resend Code");
                resentBTN.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }


    private void showKeyboard(EditText OTPCODE){
        OTPCODE.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(OTPCODE, InputMethodManager.SHOW_IMPLICIT);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length() > 0 ){
                if(selectedEtPostion == 0){
                    selectedEtPostion  = 1;
                    showKeyboard(OTPcode2);

                }else if(selectedEtPostion == 1){

                    selectedEtPostion  = 2;
                    showKeyboard(OTPcode3);

                }else if(selectedEtPostion == 2){
                    selectedEtPostion  = 3;
                    showKeyboard(OTPcode4);
                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedEtPostion == 3){
                selectedEtPostion = 2;
                showKeyboard(OTPcode3);
            }else if(selectedEtPostion == 2){

                selectedEtPostion = 1;
                showKeyboard(OTPcode2);
            }else if(selectedEtPostion  == 1){
                selectedEtPostion = 0;
                showKeyboard(OTPcode1);
            }


            return true;

        }else{
            return super.onKeyUp(keyCode, event);
        }

    }
}
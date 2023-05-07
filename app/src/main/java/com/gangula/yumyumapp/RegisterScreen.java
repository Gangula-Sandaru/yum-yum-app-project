package com.gangula.yumyumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.common.annotation.NonNullApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class RegisterScreen extends AppCompatActivity {


    private boolean passwordShow = false;
    private boolean conPasswordShow = false;
//    private FirebaseAuth firebaseAuth;
//    private DatabaseReference myFireDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // getting the data from the layout, like btn and links.

        // fire base initialize
        // firebase
//        myFireDB = FirebaseDatabase.getInstance().getReference();

        // fire base initialize authentication
//        firebaseAuth = FirebaseAuth.getInstance();

        //**********************************************************

        final ImageView passwordIcon = (ImageView) findViewById(R.id.passwdShowIcon);
        final ImageView conPasswordIcon = (ImageView) findViewById(R.id.passwdShowCIcon);

        final AppCompatButton singUpButton = findViewById(R.id.singUpMBtn);
        final TextView singInBtn = findViewById(R.id.signInBtn);

        // getting the data from input field

        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);

        final EditText emailField = (EditText) findViewById(R.id.emailAddress);
        final EditText mobile = (EditText) findViewById(R.id.mobileNo);

        final EditText passwordField = (EditText) findViewById(R.id.signPasswdFiled);
        final EditText conPasswordField = (EditText) findViewById(R.id.signPasswdConfirmFiled);


        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking if the password is showing or not
                if(passwordShow){
                    passwordShow = false;
                    passwordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.passwdshow);
                } else{
                    passwordShow = true;
                    passwordField.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.passwdhide);
                }
                // move the cursor last of the text
                passwordField.setSelection(passwordField.length());

            }
        });

        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // checking if the password is showing or not
                if(conPasswordShow){
                    conPasswordShow = false;
                    conPasswordField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.passwdshow);
                } else{
                    conPasswordShow = true;
                    conPasswordField.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.passwdhide);
                }
                // move the cursor last of the text
                conPasswordField.setSelection(conPasswordField.length());

            }
        });




        singUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send to the otp screen for the showing to verified.
                final String getMobileText = mobile.getText().toString();
                final String getEmailText = emailField.getText().toString().trim().toLowerCase();

                // keep data tmp until the verification has been completed
                final String getFirstName = firstName.getText().toString().trim();
                final String getLastName = lastName.getText().toString().trim();
                final String getPasswd = passwordField.getText().toString().trim();
                final String getC_Passwd = conPasswordField.getText().toString().trim();


//
//                if(TextUtils.isEmpty(getFirstName) || TextUtils.isEmpty(getLastName) || TextUtils.isEmpty(getMobileText) || TextUtils.isEmpty(getEmailText) || TextUtils.isEmpty(getPasswd)) {
//                    Toast.makeText(RegisterScreen.this, "Empty Credintials", Toast.LENGTH_SHORT).show();
//                }else{
//                    if(!getPasswd.equals(getC_Passwd)){
//                        Toast.makeText(RegisterScreen.this, "Password is not match", Toast.LENGTH_SHORT).show();
//                    }else if(getPasswd.length() < 8){
//                        Toast.makeText(RegisterScreen.this, "Password is Short", Toast.LENGTH_SHORT).show();
//                    }else{
//                        registerUser(getFirstName, getLastName, getMobileText,getEmailText , getPasswd);
//                    }
//
//                }

                if(TextUtils.isEmpty(getFirstName) || TextUtils.isEmpty(getLastName) || TextUtils.isEmpty(getMobileText) || TextUtils.isEmpty(getEmailText) || TextUtils.isEmpty(getPasswd)) {
                    firstName.setError("Fill this field");
                    lastName.setError("Fill this field");
                    emailField.setError("Fill this field");
                    mobile.setError("Fill this field");
                    passwordField.setError("Fill this field");
                    conPasswordField.setError("Fill this field");
                }else if(!getPasswd.equals(getC_Passwd)){
                    passwordField.setError("Password is didn't match");
                    conPasswordField.setError("Password is didn't match");
                } else if (getPasswd.length() <= 7){
                    passwordField.setError("Password is short ");
                } else{

                // opening OTP verification |email|phone
                Intent intent = new Intent(RegisterScreen.this, OTPScreen.class);
                intent.putExtra("fname", getFirstName);
                intent.putExtra("lname", getLastName);
                intent.putExtra("mobile", getMobileText);
                intent.putExtra("email", getEmailText);
                intent.putExtra("passwd", getPasswd);
                intent.putExtra("conpasswd", getC_Passwd);
                startActivity(intent);

                }

            }
        });





        singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

//    // register the user
//    private void registerUser(String FirstName, String LastName, String MobileText, String EmailText, String Passwd) {
//
//        firebaseAuth.createUserWithEmailAndPassword(EmailText, Passwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("f_name", FirstName);
//                map.put("l_name", LastName);
//                map.put("mobile", MobileText);
//                map.put("email", EmailText);
//                map.put("password", Passwd);
//                map.put("id", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
//
//                myFireDB.child("Users").child(firebaseAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
////                            pd.dismiss();
//                            Toast.makeText(RegisterScreen.this, "Update the profile " +
//                                    "for better experience", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(RegisterScreen.this , MainActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                });
//            }
//
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(Exception e) {
//                Toast.makeText(RegisterScreen.this, e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
}
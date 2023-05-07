package com.gangula.yumyumapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gangula.yumyumapp.AccountSettings;
import com.gangula.yumyumapp.R;

public class AccountFragment extends Fragment {

    Button account_settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);


    }
    public void goToActivity(View view) {

    }

}
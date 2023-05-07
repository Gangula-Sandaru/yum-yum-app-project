package com.gangula.yumyumapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.gangula.yumyumapp.MyPostAdapter;
import com.gangula.yumyumapp.Post_items;
import com.gangula.yumyumapp.R;

import java.util.ArrayList;
import java.util.List;


public class AddFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView postPic ;
    LinearLayout camBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        // Find the TextView with ID text_view and assign it to a variable

        postPic = view.findViewById(R.id.postPic);

        camBtn = view.findViewById(R.id.camLayOut);
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 100);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap pic = (Bitmap) data.getExtras().get("data");
        postPic.setImageBitmap(pic);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            imageView.setImageURI(uri);
//        }
//    }
}
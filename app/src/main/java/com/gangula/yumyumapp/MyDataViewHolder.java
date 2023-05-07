package com.gangula.yumyumapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyDataViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView username, PPname;
    public MyDataViewHolder(@NonNull View itemView) {
        super(itemView);

        PPname = itemView.findViewById(R.id.userId);
        username = itemView.findViewById(R.id.username);
        image = itemView.findViewById(R.id.postPic);

    }
}

package com.gangula.yumyumapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView description, title;
    public MyListHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.listTile);
        description = itemView.findViewById(R.id.description_list);
        image = itemView.findViewById(R.id.list_pic);
    }
}

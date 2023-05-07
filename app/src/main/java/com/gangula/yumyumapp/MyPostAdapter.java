package com.gangula.yumyumapp;


import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.List.*;
public class MyPostAdapter extends RecyclerView.Adapter<MyDataViewHolder> {



    Context context;
    List<Post_items> items;

    public MyPostAdapter(Context context, List<Post_items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyDataViewHolder(LayoutInflater.from(context).inflate(R.layout.post_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataViewHolder holder, int position) {
        holder.PPname.setText(items.get(position).getProfileName());
        holder.username.setText(items.get(position).getUsername());
        holder.image.setImageResource(items.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

package com.gangula.yumyumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<MyListHolder> {

    Context context;
    List<list_items>  list_items;

    public ListViewAdapter(Context context, List<list_items> list_items) {
        this.context = context;
        this.list_items = list_items;
    }


    @NonNull
    @Override
    public MyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyListHolder(LayoutInflater.from(context).inflate(R.layout.recepit_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyListHolder holder, int position) {
        holder.title.setText(list_items.get(position).getName());
        holder.description.setText(list_items.get(position).getDescription());
        holder.image.setImageResource(list_items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }
}

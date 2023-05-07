package com.gangula.yumyumapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gangula.yumyumapp.HomeScreen;
import com.gangula.yumyumapp.MyPostAdapter;
import com.gangula.yumyumapp.Post_items;
import com.gangula.yumyumapp.R;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    // Find the TextView with ID text_view and assign it to a variable



    RecyclerView recyclerView = view.findViewById(R.id.recycleView1);
    List<Post_items> post_items = new ArrayList<Post_items>();



    post_items.add(new Post_items("Gangula sandaru", "gangula-sandaru", R.drawable.food1));
    post_items.add(new Post_items("Gangula sandaru", "gangula-sandaru", R.drawable.food2));

    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    recyclerView.setAdapter(new MyPostAdapter(getContext(),post_items));
    return view;
}

}
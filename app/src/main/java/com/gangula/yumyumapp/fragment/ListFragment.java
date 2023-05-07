package com.gangula.yumyumapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gangula.yumyumapp.DatabaseHelper;
import com.gangula.yumyumapp.ListViewAdapter;
import com.gangula.yumyumapp.R;
import com.gangula.yumyumapp.list_items;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ListFragment extends Fragment {

    DatabaseHelper MyDB;
    String userId2;
    String recipeTitle;
    String recipeDescription;
    private RecyclerView recyclerView;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        // Find the TextView with ID text_view and assign it to a variable

        Date currentDate = new Date();

        MyDB = new DatabaseHelper(this.getContext());


        // add items to the database
        EditText Title = view.findViewById(R.id.EditTextTitle);
        EditText description = view.findViewById(R.id.EditTextDescription);

        ImageButton add = view.findViewById(R.id.addBTN);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        // get current log id from cache
        String userId = preferences.getString("LogUserId", null);

        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                final String getTitle = Title.getText().toString();
                final String getDescriptipn = description.getText().toString();

                if (getTitle.isEmpty() || getDescriptipn.isEmpty()){
                    Title.setError("Fill this field");
                    description.setError("Fill this field");
                }

                else if (!userId.isEmpty()) {
                    LocalDateTime currentDateTime = LocalDateTime.now();

                    MyDB.recipe_list(Integer.valueOf(userId), getTitle, getDescriptipn,currentDateTime.toString());

                    Toast.makeText(getContext(), "Added to the database", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Can't add", Toast.LENGTH_SHORT).show();

                }
//                Log.i("DATA", getTitle + getDescriptipn);
//                Toast.makeText(getContext(), "You add a one item", Toast.LENGTH_SHORT).show();


            }
        });


        RecyclerView recyclerView = view.findViewById(R.id.recycleView2);
        List<list_items> list_items = new ArrayList<list_items>();

        Cursor recipe_result = MyDB.getAllRecipe(userId);
        while (recipe_result.moveToNext()) {
            String userId2 = recipe_result.getString(recipe_result.getColumnIndex("User_id"));
            String recipeTitle = recipe_result.getString(recipe_result.getColumnIndex("Recipet_Title"));
            String recipeDescription = recipe_result.getString(recipe_result.getColumnIndex("Recipet_Description"));

            // Do something with the retrieved data for this row
            Log.i("DATA",  userId2 + " " +recipeTitle + " " + recipeDescription+ " " );
                    list_items.add(new list_items(recipeTitle, recipeDescription , R.drawable.otp));
        }

        recipe_result.close();
        MyDB.close();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ListViewAdapter(getContext(),list_items));

        return view;
    }


}
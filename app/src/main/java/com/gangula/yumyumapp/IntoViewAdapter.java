package com.gangula.yumyumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntoViewAdapter extends PagerAdapter {
    Context  mContext;
    List<ScreenItem> mListScreen;

    public IntoViewAdapter(Context mcontext, List<ScreenItem> mlistscreen){
        this.mContext = mcontext;
        this.mListScreen = mlistscreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen = inflater.inflate(R.layout.boarding_screen_layout, null);

        ImageView imgslide = layoutscreen.findViewById(R.id.intro_image);
        TextView title = layoutscreen.findViewById(R.id.intro_titile);
        TextView description = layoutscreen.findViewById(R.id.intro_description);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescrip());
        imgslide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutscreen);
        return layoutscreen;

    }


    @Override
    public int getCount(){
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object obj){
        container.removeView((View) obj);
    }

}

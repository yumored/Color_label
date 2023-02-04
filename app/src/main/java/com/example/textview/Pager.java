package com.example.textview;

import static android.view.Gravity.CENTER;
import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Pager extends PagerAdapter {

    private List<View> mViewList = new ArrayList<>();;
    private Boolean viewa=false;

    @SuppressLint("ResourceAsColor")
    public void AddPager(List list,Context context){
        for (int i=0;i<list.size();i++){
            LinearLayout linearLayout=new LinearLayout(context);
            linearLayout.setGravity(CENTER);
            TextView textView=new TextView(context);
            textView.setText((CharSequence) list.get(i));
            linearLayout.addView(textView);
            mViewList.add(linearLayout);
        }
    }
    // 声明一个图像视图列表
    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }
}

package com.example.textview;

import static android.view.Gravity.CENTER;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int Position;
    private String[] itme={"主页","功能","个人"};
    private List<TextView> itme_data=new ArrayList<TextView>();
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager=findViewById(R.id.viewpager);
        Pager pager =new Pager();
        add(pager);
        viewPager.setAdapter(pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private TextView Not_textView;
            private TextView textView;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float size=positionOffset*100;
                float Not_size=100-(positionOffset*100);
                textView=itme_data.get(MainActivity.this.Position);//本界面
                Not_textView=itme_data.get(position);//前界面
                if (positionOffset>0){
                    if (MainActivity.this.Position-position<=1){
                        if (MainActivity.this.Position==0){
                            textView=itme_data.get(1);
                            textView.SetType(TextView._Type.left_To_right);
                            Not_textView.SetType(TextView._Type.right_To_left);
                        }else {
                            textView=itme_data.get(position+1);
                            textView.SetType(TextView._Type.left_To_right);
                            Not_textView.SetType(TextView._Type.right_To_left);
                        }
                    } else{
                        textView.SetType(TextView._Type.right_To_left);
                        Not_textView.SetType(TextView._Type.left_To_right);
                    }
                        textView.SetEfficiency(size);
                        Not_textView.SetEfficiency(Not_size);
                }else {
                    textView.SetEfficiency(100);
                }
           }

            @Override
            public void onPageSelected(int position) {
                MainActivity.this.Position=position;
                Not_textView.SetEfficiency(0);
                textView.SetEfficiency(0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    public void add(Pager pager){
        LinearLayout linearLayout=findViewById(R.id.lint);
        for (String arr:itme){
            //实例化一个LinearLayout
            LinearLayout linearLayout1 = new LinearLayout(this);
            //设置LinearLayout属性(宽和高)
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            linearLayout1.setLayoutParams(layoutParams);
            linearLayout1.setGravity(CENTER);
            TextView textView=new TextView(this);
            textView.setText(arr);
            linearLayout1.addView(textView);
            linearLayout.addView(linearLayout1);
            itme_data.add(textView);
        }
        pager.AddPager(Arrays.asList(itme),this);
    }
}
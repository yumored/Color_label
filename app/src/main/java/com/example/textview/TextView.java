package com.example.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *Email 2789599771@qq.com
 *Created by 雨莫 on 2023/1/.
 *Version 1.0
 *Description:颜色跟踪的Textview
 */
public class TextView extends androidx.appcompat.widget.AppCompatTextView {
    private int Elow_Color= Color.BLACK;
    private int Eextra_Color=Color.GREEN;
    private Paint low_Color,extra_Color;
    private float Efficiency=0;

    private _Type mType=_Type.right_To_left;
    enum _Type{
        left_To_right,right_To_left
    }

    public TextView(@NonNull Context context) {
        this(context,null);
    }

    public TextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array= context.obtainStyledAttributes(attrs,R.styleable.TextView);
        Elow_Color=array.getColor(R.styleable.TextView_low_Color,Elow_Color);
        Eextra_Color=array.getColor(R.styleable.TextView_extra_Color,Eextra_Color);
        array.recycle();
        //基础颜色
        low_Color=new Paint();
        low_Color.setAntiAlias(true);
        low_Color.setColor(Elow_Color);
        low_Color.setTextSize(getTextSize());
        //高亮颜色
        extra_Color=new Paint();
        extra_Color.setAntiAlias(true);
        extra_Color.setColor(Eextra_Color);
        extra_Color.setTextSize(getTextSize());

    }
    @Override
    protected void onDraw(Canvas canvas) {
        Set_To(canvas,mType);
    }
    public void Set_To(Canvas canvas,_Type direction) {
        //计算坐标
        Rect rect = new Rect();
        low_Color.getTextBounds((String) getText(), 0, getText().length(), rect);
        int Y = rect.height() + getPaddingTop() + getPaddingBottom();
        int X = getPaddingLeft() + getPaddingRight();
        float E_fficiency = (Efficiency / 100) * rect.width();
        if (direction==_Type.right_To_left){
            //绘制底色
            canvas.save();
            canvas.clipRect(0, 0, rect.width()-E_fficiency, getHeight());
            canvas.drawText((String) getText(), X, Y, low_Color);
            canvas.restore();
            //绘制高光
            canvas.save();
            canvas.clipRect(rect.width()-E_fficiency, 0, rect.width() + 1, getHeight());
            canvas.drawText((String) getText(), X, Y, extra_Color);
            canvas.restore();
        }else {
            //绘制底色
            canvas.save();
            canvas.clipRect(0, 0, E_fficiency+1, getHeight());
            canvas.drawText((String) getText(), X, Y, extra_Color);
            canvas.restore();
            //绘制高光
            canvas.save();
            canvas.clipRect(E_fficiency+1, 0, rect.width()+1, getHeight());
            canvas.drawText((String) getText(), X, Y, low_Color);
            canvas.restore();
        }
    }
    public void SetType(_Type mType){
        this.mType=mType;
    }
    public void SetEfficiency(float num){
        this.Efficiency=num;
        invalidate();
    }
}

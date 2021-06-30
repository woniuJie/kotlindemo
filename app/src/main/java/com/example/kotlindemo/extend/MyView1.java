package com.example.kotlindemo.extend;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author:zhangshijie
 * @Date:2021/3/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class MyView1 extends FrameLayout {
    public MyView1(@NonNull Context context) {
        super(context);
    }

    public MyView1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void play(){
        if(aListener!=null){
            aListener.onPlay();
        }
    }


    interface AListener{
        void onPlay();
    }

    AListener aListener;

    public void setaListener(AListener aListener) {
        this.aListener = aListener;
    }

}

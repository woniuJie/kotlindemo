package com.example.kotlindemo.extend;

import android.content.Context;

/**
 * @author:zhangshijie
 * @Date:2021/2/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class A {

    void a(Context context){
        MyView1 myView = new MyView1(context);
        myView.setaListener(new MyView1.AListener() {
            @Override
            public void onPlay() {
                //xxx
            }
        });
    }
}

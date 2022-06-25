package com.example.kotlindemo.maoboli.blur;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * @author:zhangshijie
 * @Date:2021/11/12
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public interface BlurImpl {

    boolean prepare(Context context, Bitmap buffer, float radius);

    void release();

    void blur(Bitmap input, Bitmap output);
}

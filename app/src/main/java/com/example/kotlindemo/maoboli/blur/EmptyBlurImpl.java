package com.example.kotlindemo.maoboli.blur;

import android.content.Context;
import android.graphics.Bitmap;


/**
 * @author:zhangshijie
 * @Date:2021/11/12
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class EmptyBlurImpl implements BlurImpl {
    @Override
    public boolean prepare(Context context, Bitmap buffer, float radius) {
        return false;
    }

    @Override
    public void release() {

    }

    @Override
    public void blur(Bitmap input, Bitmap output) {

    }
}

package com.example.kotlindemo.spannable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author:zhangshijie
 * @Date:2022/6/8
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class MyImageSpan extends ImageSpan {
    public MyImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        Drawable drawable = getDrawable();
        Log.e("zsj", "draw:  y"+y+"--fontMetrics.descent:"+fontMetrics.descent +"---fontMetrics.ascent:"+fontMetrics.ascent+"--drawable.getBounds().bottom:"+drawable.getBounds().bottom+"--drawable.getBounds().top:"+drawable.getBounds().top+"---fontMetrics.bottom:"+fontMetrics.bottom+"--fontMetrics.leading:"+fontMetrics.leading);
        int MyY = (int) ((y + fontMetrics.ascent +y +fontMetrics.descent)/2 - (drawable.getBounds().bottom+drawable.getBounds().top)/2);
        int yyy = (int)fontMetrics.bottom;
        Log.e("zsj", "draw: MyY"+MyY+"---yyy:"+yyy );

        canvas.save();
        canvas.translate(x,MyY+yyy);
        drawable.draw(canvas);
        canvas.restore();


    }
}

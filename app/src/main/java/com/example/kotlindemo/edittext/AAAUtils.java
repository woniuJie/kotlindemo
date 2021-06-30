package com.example.kotlindemo.edittext;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

/**
 * @author:zhangshijie
 * @Date:2021/4/12
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class AAAUtils {

    public static Drawable getDiagnoseTagBg(String bgColor, Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dp2px(context, 14));
        drawable.setColor(Color.parseColor(bgColor));
//        drawable.setStroke(dp2px(context,0.5f), Color.parseColor(bgColor));
        return drawable;
    }

    public static Drawable getGradientFillDrawable(Context context,String bgColor,int radiu) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dp2px(context,radiu));
        drawable.setColor(Color.parseColor(getColorAlpha05(bgColor)));
        return drawable;
    }

    public static String getColorAlpha05(String color) {
        if (color.length() == 9) {
            return "#0D" + color.substring(3);
        } else {
            return "#0D" + color.substring(1);
        }
    }

    /**
     * dp 转 px
     *
     * @param dpValue dp 值
     * @return px 值
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取drawable下面图片，一般imageview会使用
     *
     * @param resId
     * @return
     */
    public static Drawable getDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }
}

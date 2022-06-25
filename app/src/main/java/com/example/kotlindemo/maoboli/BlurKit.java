package com.example.kotlindemo.maoboli;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;

/**
 * @author:zhangshijie
 * @Date:2021/11/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class BlurKit {
    private static final float FULL_SCALE = 1.0F;
    private static BlurKit instance;
    private static RenderScript rs;

    public BlurKit() {
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new BlurKit();
            rs = RenderScript.create(context.getApplicationContext());
        }
    }

    public Bitmap blur(Bitmap src, int radius) {
        Allocation input = Allocation.createFromBitmap(rs, src);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius((float)radius);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(src);
        return src;
    }

    public Bitmap blur(View src, int radius) {
        Bitmap bitmap = this.getBitmapForView(src);
        return this.blur(bitmap, radius);
    }

    public Bitmap fastBlur(View src, int radius, float downscaleFactor) {
        Bitmap bitmap = this.getBitmapForView(src, downscaleFactor);
        return this.blur(bitmap, radius);
    }

    private Bitmap getBitmapForView(View src, float downscaleFactor) {
        Bitmap bitmap = Bitmap.createBitmap((int)((float)src.getWidth() * downscaleFactor), (int)((float)src.getHeight() * downscaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Matrix matrix = new Matrix();
        matrix.preScale(downscaleFactor, downscaleFactor);
        canvas.setMatrix(matrix);
        src.draw(canvas);
        return bitmap;
    }

    private Bitmap getBitmapForView(View src) {
        Bitmap bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        src.draw(canvas);
        return bitmap;
    }

    public static BlurKit getInstance() {
        if (instance == null) {
            throw new RuntimeException("BlurKit not initialized!");
        } else {
            return instance;
        }
    }
}

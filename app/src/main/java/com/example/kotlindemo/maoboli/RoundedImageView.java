package com.example.kotlindemo.maoboli;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author:zhangshijie
 * @Date:2021/11/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class RoundedImageView extends AppCompatImageView {
    private float mCornerRadius = 0.0F;
    public static final int DEFAULT_COLOR = -16777216;
    public static final int DEFAULT_RGB = 0;
    private RectF rectF = new RectF();
    private PorterDuffXfermode porterDuffXfermode;

    public RoundedImageView(Context context) {
        super(context, (AttributeSet)null);
        this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    public RoundedImageView(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    protected void onDraw(Canvas canvas) {
        Drawable myDrawable = this.getDrawable();
        if (myDrawable != null && myDrawable instanceof BitmapDrawable && this.mCornerRadius > 0.0F) {
            this.rectF.set(myDrawable.getBounds());
            int prevCount = canvas.saveLayer(this.rectF, (Paint)null, 31);
            this.getImageMatrix().mapRect(this.rectF);
            Paint paint = ((BitmapDrawable)myDrawable).getPaint();
            paint.setAntiAlias(true);
            paint.setColor(-16777216);
            Xfermode prevMode = paint.getXfermode();
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawRoundRect(this.rectF, this.mCornerRadius, this.mCornerRadius, paint);
            paint.setXfermode(this.porterDuffXfermode);
            super.onDraw(canvas);
            paint.setXfermode(prevMode);
            canvas.restoreToCount(prevCount);
        } else {
            super.onDraw(canvas);
        }

    }

    public void setCornerRadius(float cornerRadius) {
        this.mCornerRadius = cornerRadius;
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }
}

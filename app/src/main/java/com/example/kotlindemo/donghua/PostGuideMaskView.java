package com.example.kotlindemo.donghua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

import com.example.kotlindemo.click.ScreenUtilsKt;

import java.util.ArrayList;

public class PostGuideMaskView extends View {
    private int width;
    private int height;

    private Paint paintCircle;
    private Paint paintRect;

    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    private int radius;

    //多个的情况
    private ArrayList<RectF> mRectFs;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    public void setRectF(RectF rectF) {
        this.mRectFs.clear();
        this.mRectFs.add(rectF);
        invalidate();
    }

    public void setRectFs(ArrayList<RectF> mRectFs) {
        this.mRectFs = mRectFs;
    }

    public PostGuideMaskView(Context context) {
        super(context);
        init();
    }
    public PostGuideMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //背景的矩阵
    RectF rectF = new RectF();
    private void init() {
        //使用Xfermode时，关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paintCircle = new Paint();
        paintRect = new Paint();
        paintCircle.setColor(0x66000000);
        paintRect.setColor(0xFFFFFF);
//        //设置画笔图层在上
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        paintRect.setXfermode(mode);


        mRectFs = new ArrayList<>();

        rectF.right = ScreenUtilsKt.getScreenWidth(getContext());
        rectF.bottom = ScreenUtilsKt.getScreenHeight(getContext());
    }

    public void setBackgroundColor(@ColorInt int color){
        if (paintCircle == null){
            return;
        }
        paintCircle.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先绘制的DST图片
        canvas.drawRect(rectF,paintCircle);
        //后画SRC的图片
        if(mRectFs!=null && !mRectFs.isEmpty()){
            for (int i = 0; i < mRectFs.size(); i++) {
                canvas.drawRoundRect(mRectFs.get(i),radius, radius, paintRect);
            }
        }
    }
}


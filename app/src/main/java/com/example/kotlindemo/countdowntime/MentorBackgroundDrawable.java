package com.example.kotlindemo.countdowntime;

import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

/**
 * @author:zhangshijie
 * @Date:2020/10/9
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class MentorBackgroundDrawable extends ShapeDrawable {
    private int topColor;
    private int bottomColor;
    private int leftPercent;
    private int rightPercent;
    private Paint topPaint;
    private Paint bottomPaint;
    float[] rids = {25.0f, 25.0f, 25.0f, 25.0f, 25.0f, 25.0f, 25.0f, 25.0f};//四个角都圆角

    private MentorBackgroundDrawable(Builder builder) {
        super(builder.shape);

        topColor = builder.topColor;
        bottomColor = builder.bottomColor;
        leftPercent = builder.leftPercent;
        rightPercent = builder.rightPercent;

        topPaint = new Paint();
        topPaint.setStyle(Paint.Style.FILL);
        topPaint.setAntiAlias(true);
        topPaint.setColor(topColor);

        bottomPaint = new Paint();
        bottomPaint.setStyle(Paint.Style.FILL);
        bottomPaint.setAntiAlias(true);
        bottomPaint.setColor(bottomColor);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        Rect r = getBounds();

        Paint paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        canvas.drawRect(r,paint1);

        float radius = r.bottom/2;

        //背景左侧的top值
        int lTop;
        //背景右侧的top值
        int rTop;
        if (leftPercent > 0 && rightPercent > 0 && leftPercent < 100 && rightPercent < 100) {

            lTop = (int) (r.right * leftPercent * 0.01 );
            rTop = (int) (r.right * rightPercent * 0.01);
            //使用path类来绘制不规则图形，形成斜切的效果
            Path path = new Path();
            path.lineTo(r.left+r.bottom/2, r.top);
            path.lineTo(lTop, r.top);
            path.lineTo(rTop, r.bottom);
            path.lineTo(r.left+r.bottom/2, r.bottom);
            path.lineTo(r.left+r.bottom/2, r.top);


            //背景下半部分的rect
            Rect bRect = new Rect(r.left+r.bottom/2, r.top, r.right-r.bottom/2, r.bottom);
            //开始绘制

//            canvas.save();

            Paint paint = new Paint();
            paint.setColor(topColor);
            paint.setAntiAlias(true);
            canvas.drawCircle(r.left+radius,r.bottom/2,radius,paint);

            paint.setColor(bottomColor);
            canvas.drawCircle(r.right-r.bottom/2,r.bottom/2,radius,paint);

            canvas.drawRect(bRect, bottomPaint);
            canvas.drawPath(path, topPaint);


//            canvas.restore();

        }


    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements IShapeDrawableBuilder {

        private RectShape shape;
        private int topColor;
        private int bottomColor;
        private int leftPercent;
        private int rightPercent;

        private Builder() {
            shape = new RectShape();
            topColor = Color.WHITE;
            bottomColor = Color.WHITE;
            leftPercent = 0;
            rightPercent = 0;
        }

        @Override
        public IShapeDrawableBuilder left(int percent) {
            leftPercent = percent;
            return this;
        }

        @Override
        public IShapeDrawableBuilder right(int percent) {
            rightPercent = percent;
            return this;
        }
        @Override
        public IShapeDrawableBuilder leftColor(int topColor) {
            this.topColor = topColor;
            return this;
        }

        @Override
        public IShapeDrawableBuilder rightColor(int bottomColor) {
            this.bottomColor = bottomColor;
            return this;
        }

        @Override
        public MentorBackgroundDrawable build() {
            return new MentorBackgroundDrawable(this);
        }
    }

    public interface IShapeDrawableBuilder {
        public IShapeDrawableBuilder left(int percent);

        public IShapeDrawableBuilder right(int percent);

        public IShapeDrawableBuilder leftColor(int topColor);

        public IShapeDrawableBuilder rightColor(int bottomColor);

        public MentorBackgroundDrawable build();
    }

}

package com.example.kotlindemo.maoboli;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.kotlindemo.R;

import java.lang.ref.WeakReference;

/**
 * @author:zhangshijie
 * @Date:2021/11/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class BlurLayout extends FrameLayout {
    private float mDownscaleFactor;
    private int mBlurRadius;
    private int mFPS;
    private float mCornerRadius;
    private float mAlpha;
    private boolean mRunning;
    private boolean mAttachedToWindow;
    private boolean mPositionLocked;
    private boolean mViewLocked;
    private RoundedImageView mImageView;
    private WeakReference<View> mActivityView;
    private Point mLockedPoint;
    private Bitmap mLockedBitmap;
    private Choreographer.FrameCallback invalidationLoop;

    class NamelessClass_1 implements Choreographer.FrameCallback {
        NamelessClass_1() {
        }

        public void doFrame(long frameTimeNanos) {
            invalidate();
            Choreographer.getInstance().postFrameCallbackDelayed(this, (long)(1000 / mFPS));
        }
    }

    public BlurLayout(Context context) {
        super(context, (AttributeSet)null);
        this.invalidationLoop = new NamelessClass_1();
    }

    public BlurLayout(Context context, AttributeSet attrs) {
        super(context, attrs);



        this.invalidationLoop = new NamelessClass_1();
        if (!this.isInEditMode()) {
            BlurKit.init(context);
        }

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BlurLayout, 0, 0);

        try {
            this.mDownscaleFactor = a.getFloat(R.styleable.BlurLayout_blk_downscaleFactor, 0.12F);
            this.mBlurRadius = a.getInteger(R.styleable.BlurLayout_blk_blurRadius, 12);
            this.mFPS = a.getInteger(R.styleable.BlurLayout_blk_fps, 60);
            this.mCornerRadius = a.getDimension(R.styleable.BlurLayout_blk_cornerRadius, 0.0F);
            this.mAlpha = a.getDimension(R.styleable.BlurLayout_blk_alpha, 0.0f);
        } finally {
            a.recycle();
        }

        this.mImageView = new RoundedImageView(this.getContext());
        this.mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.addView(this.mImageView);
        this.setCornerRadius(this.mCornerRadius);
    }

    public void startBlur() {
        if (!this.mRunning) {
            if (this.mFPS > 0) {
                this.mRunning = true;
                Choreographer.getInstance().postFrameCallback(this.invalidationLoop);
            }

        }
    }

    public void pauseBlur() {
        if (this.mRunning) {
            this.mRunning = false;
            Choreographer.getInstance().removeFrameCallback(this.invalidationLoop);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.startBlur();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.pauseBlur();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.invalidate();
    }

    public void invalidate() {
        super.invalidate();
        Bitmap bitmap = this.blur();
        if (bitmap != null) {
            this.mImageView.setImageBitmap(bitmap);
        }

    }

    private Bitmap blur() {
        if (this.getContext() != null && !this.isInEditMode()) {
            if (this.mActivityView == null || this.mActivityView.get() == null) {
                this.mActivityView = new WeakReference(this.getActivityView());
                if (this.mActivityView.get() == null) {
                    return null;
                }
            }

            Point pointRelativeToActivityView;
            if (this.mPositionLocked) {
                if (this.mLockedPoint == null) {
                    this.mLockedPoint = this.getPositionInScreen();
                }

                pointRelativeToActivityView = this.mLockedPoint;
            } else {
                pointRelativeToActivityView = this.getPositionInScreen();
            }

            super.setAlpha(0.0F);
            int screenWidth = ((View)this.mActivityView.get()).getWidth();
            int screenHeight = ((View)this.mActivityView.get()).getHeight();
            int width = (int)((float)this.getWidth() * this.mDownscaleFactor);
            int height = (int)((float)this.getHeight() * this.mDownscaleFactor);
            int x = (int)((float)pointRelativeToActivityView.x * this.mDownscaleFactor);
            int y = (int)((float)pointRelativeToActivityView.y * this.mDownscaleFactor);
            int xPadding = this.getWidth() / 8;
            int yPadding = this.getHeight() / 8;
            int leftOffset = -xPadding;
            leftOffset = x + leftOffset >= 0 ? leftOffset : 0;
            int rightOffset = x + screenWidth - xPadding <= screenWidth ? xPadding : screenWidth + screenWidth - x;
            int topOffset = -yPadding;
            topOffset = y + topOffset >= 0 ? topOffset : 0;
            int bottomOffset = y + this.getHeight() + yPadding <= screenHeight ? yPadding : 0;
            Bitmap bitmap;
            if (this.mViewLocked) {
                if (this.mLockedBitmap == null) {
                    this.lockView();
                }

                if (width == 0 || height == 0) {
                    return null;
                }

                bitmap = Bitmap.createBitmap(this.mLockedBitmap, x, y, width, height);
            } else {
                try {
                    bitmap = this.getDownscaledBitmapForView((View)this.mActivityView.get(), new Rect(pointRelativeToActivityView.x + leftOffset, pointRelativeToActivityView.y + topOffset, pointRelativeToActivityView.x + this.getWidth() + Math.abs(leftOffset) + rightOffset, pointRelativeToActivityView.y + this.getHeight() + Math.abs(topOffset) + bottomOffset), this.mDownscaleFactor);
                } catch (Exception var16) {
                    return null;
                }
            }

            if (!this.mViewLocked) {
                bitmap = BlurKit.getInstance().blur(bitmap, this.mBlurRadius);
                bitmap = Bitmap.createBitmap(bitmap, (int)((float)Math.abs(leftOffset) * this.mDownscaleFactor), (int)((float)Math.abs(topOffset) * this.mDownscaleFactor), width, height);
            }

            if (Float.isNaN(this.mAlpha)) {
                super.setAlpha(1.0F);
            } else {
                super.setAlpha(this.mAlpha);
            }

            return bitmap;
        } else {
            return null;
        }
    }

    private View getActivityView() {
        Activity activity;
        try {
            activity = (Activity)this.getContext();
        } catch (ClassCastException var3) {
            return null;
        }

        return activity.getWindow().getDecorView();
    }

    private Point getPositionInScreen() {
        PointF pointF = this.getPositionInScreen(this);
        return new Point((int)pointF.x, (int)pointF.y);
    }

    private PointF getPositionInScreen(View view) {
        if (this.getParent() == null) {
            return new PointF();
        } else {
            ViewGroup parent;
            try {
                parent = (ViewGroup)view.getParent();
            } catch (Exception var4) {
                return new PointF();
            }

            if (parent == null) {
                return new PointF();
            } else {
                PointF point = this.getPositionInScreen(parent);
                point.offset(view.getX(), view.getY());
                return point;
            }
        }
    }

    private Bitmap getDownscaledBitmapForView(View view, Rect crop, float downscaleFactor) throws Exception {
        View screenView = view.getRootView();
        int width = (int)((float)crop.width() * downscaleFactor);
        int height = (int)((float)crop.height() * downscaleFactor);
        if (screenView.getWidth() > 0 && screenView.getHeight() > 0 && width > 0 && height > 0) {
            float dx = (float)(-crop.left) * downscaleFactor;
            float dy = (float)(-crop.top) * downscaleFactor;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Matrix matrix = new Matrix();
            matrix.preScale(downscaleFactor, downscaleFactor);
            matrix.postTranslate(dx, dy);
            canvas.setMatrix(matrix);
            screenView.draw(canvas);
            return bitmap;
        } else {
            throw new Exception("No screen available (width or height = 0)");
        }
    }

    public void setDownscaleFactor(float downscaleFactor) {
        this.mDownscaleFactor = downscaleFactor;
        this.mLockedBitmap = null;
        this.invalidate();
    }

    public float getDownscaleFactor() {
        return this.mDownscaleFactor;
    }

    public void setBlurRadius(int blurRadius) {
        this.mBlurRadius = blurRadius;
        this.mLockedBitmap = null;
        this.invalidate();
    }

    public int getBlurRadius() {
        return this.mBlurRadius;
    }

    public void setFPS(int fps) {
        if (this.mRunning) {
            this.pauseBlur();
        }

        this.mFPS = fps;
        if (this.mAttachedToWindow) {
            this.startBlur();
        }

    }

    public int getFPS() {
        return this.mFPS;
    }

    public void setCornerRadius(float cornerRadius) {
        this.mCornerRadius = cornerRadius;
        if (this.mImageView != null) {
            this.mImageView.setCornerRadius(cornerRadius);
        }

        this.invalidate();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public void setAlpha(float alpha) {
        this.mAlpha = alpha;
        if (!this.mViewLocked) {
            super.setAlpha(this.mAlpha);
        }

    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void lockView() {
        this.mViewLocked = true;
        if (this.mActivityView != null && this.mActivityView.get() != null) {
            View view = ((View)this.mActivityView.get()).getRootView();

            try {
                super.setAlpha(0.0F);
                this.mLockedBitmap = this.getDownscaledBitmapForView(view, new Rect(0, 0, view.getWidth(), view.getHeight()), this.mDownscaleFactor);
                if (Float.isNaN(this.mAlpha)) {
                    super.setAlpha(1.0F);
                } else {
                    super.setAlpha(this.mAlpha);
                }

                this.mLockedBitmap = BlurKit.getInstance().blur(this.mLockedBitmap, this.mBlurRadius);
            } catch (Exception var3) {
            }
        }

    }

    public void unlockView() {
        this.mViewLocked = false;
        this.mLockedBitmap = null;
    }

    public boolean getViewLocked() {
        return this.mViewLocked;
    }

    public void lockPosition() {
        this.mPositionLocked = true;
        this.mLockedPoint = this.getPositionInScreen();
    }

    public void unlockPosition() {
        this.mPositionLocked = false;
        this.mLockedPoint = null;
    }

    public boolean getPositionLocked() {
        return this.mPositionLocked;
    }
}

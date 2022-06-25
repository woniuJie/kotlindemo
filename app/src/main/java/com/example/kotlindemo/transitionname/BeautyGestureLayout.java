package com.example.kotlindemo.transitionname;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.kotlindemo.UtilsKt;

/**
 * @author:zhangshijie
 * @Date:2022/2/15
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class BeautyGestureLayout extends ConstraintLayout {
    private int lastX;
    private int lastY;

    private float range = 0.3f;
    private int totalMoveY = 0;

    private LinearLayout linearLayout;


    public BeautyGestureLayout(Context context) {
        super(context);
    }

    public BeautyGestureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeautyGestureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                totalMoveY = totalMoveY + dy;
                if(totalMoveY<0){
                    return false;
                }
                Log.e("zsj", "onTouchEvent: " + dy + "---" + totalMoveY);

                int absDx = Math.abs(dx);
                int absDy = Math.abs(dy);

                if (absDy > absDx * 2) {
                    int moveY = (int) (dy * range);
                    int l = this.getLeft();
                    int r = this.getRight();
                    int t = this.getTop() + moveY;
                    int b = this.getBottom() + moveY;
                    this.layout(l, t, r, b);


                    int ll = linearLayout.getLeft();
                    int lr = linearLayout.getRight();
                    int lt = linearLayout.getTop()+ moveY;
                    int lb = linearLayout.getBottom()+ moveY;

//                    MarginLayoutParams lp = (MarginLayoutParams) linearLayout.getLayoutParams();
//                    lp.topMargin = moveY;
//                    linearLayout.setLayoutParams(lp);

                }
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.e("zsj", "onTouchEvent: action " + action);
                if(totalMoveY>1000){
                    mListener.gestureFinish();
                }else{
                    this.layout(getLeft(), 0, getRight(), getBottom());
                    totalMoveY = 0;
                }
                break;
        }
        return true;
    }

    private OnGestureFinishListener mListener;

    public void addGestureFinishListener(OnGestureFinishListener listener) {
        this.mListener = listener;
    }

    interface OnGestureFinishListener {
        void gestureFinish();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        linearLayout = (LinearLayout) getChildAt(0);
    }
}

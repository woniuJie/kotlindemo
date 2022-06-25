package com.example.kotlindemo.touchevent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class TouchEventChildView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        loge("点击事件 子view dispatchTouchEvent ${event?.action}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        loge("点击事件 子view onTouchEvent ${event?.action}")
        return super.onTouchEvent(event)
    }
}
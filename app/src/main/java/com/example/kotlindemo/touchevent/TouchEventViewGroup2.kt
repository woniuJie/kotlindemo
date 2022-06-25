package com.example.kotlindemo.touchevent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class TouchEventViewGroup2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        loge("点击事件 viewgroup dispatchTouchEvent 第二个 ${ev?.action}")
//        if(ev?.action == MotionEvent.ACTION_MOVE){
//            return true
//        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        loge("点击事件 viewgroup onInterceptTouchEvent 第二个  ${ev?.action}")
        if(ev?.action == MotionEvent.ACTION_MOVE){
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        loge("点击事件 viewgroup onTouchEvent 第二个  ${event?.action}")
        return super.onTouchEvent(event)
    }

}
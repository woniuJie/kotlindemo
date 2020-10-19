package com.example.kotlindemo.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/7/18
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return false
    }
}
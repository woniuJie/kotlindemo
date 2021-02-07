package com.example.kotlindemo.click

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/11/4
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyClickButton(context: Context) : View(context) {

    private var state: Int = 0
    private val STATE_IDLE = 0x001//空闲状态
    private val STATE_PRESS = 0x002//按下状态
    private val STATE_LONG_PRESS = 0x003//进入长按状态
    private val STATE_RECORDERING = 0x004//录制状态

    private val mPaint: Paint
    private var mScreenWidth: Int = 0


    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = Color.GRAY
        mScreenWidth = getScreenWidth(context)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(100f, 100f, 400f, 200f, mPaint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                state = STATE_PRESS
                postDelayed(longPressRunnable, 500)
            }
            MotionEvent.ACTION_UP -> {
                handlerUnpressByState()
            }
            MotionEvent.ACTION_MOVE -> {

            }
        }
        return true
    }

    //长按线程
    private val longPressRunnable = Runnable {
        state = STATE_LONG_PRESS //状态为长按状态

        loge("长按")

    }

    /**
     * 松开手指处理的逻辑
     */
    private fun handlerUnpressByState() {
        removeCallbacks(longPressRunnable)//移除长按逻辑的Runnable
        loge("抬起来")
        when (state) {
            STATE_PRESS -> {
            }
            STATE_RECORDERING -> {
            }
            STATE_LONG_PRESS -> {
            }
        }
        this.state = STATE_IDLE //制空当前状态
    }

}
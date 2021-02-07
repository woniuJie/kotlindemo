package com.example.kotlindemo.click

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/11/4
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class ClickView : View {

    private var progress: Int = 0

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        initView()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView()
    }

    fun initView() {

    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        super.setOnLongClickListener(l)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {


        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}
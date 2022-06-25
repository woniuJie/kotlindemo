package com.example.kotlindemo.bringtofront

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/5/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MeasureTestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }
}
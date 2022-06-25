package com.example.kotlindemo.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/5/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyTextViewDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        loge("MyTextViewDemoView onMeasure")
    }


}
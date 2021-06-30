package com.example.kotlindemo.extend

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/3/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyView : FrameLayout{
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }


    interface AAListener{
        fun play()
    }

    var aaListerner : AAListener ?=null

    fun setAALisetner(onCLick:(View)->Unit){
//        this.aaListerner = ar
    }

}
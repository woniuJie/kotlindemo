package com.example.kotlindemo.click

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/11/4
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
fun getScreenWidth(context: Context): Int {
    val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    manager.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels
}

fun getScreenHeight(context: Context): Int {
    val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    manager.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}
fun dp2px(context: Context, dpValue: Float): Int {
    var context = context
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
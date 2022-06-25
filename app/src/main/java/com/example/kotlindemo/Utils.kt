package com.example.kotlindemo

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.NullPointerException

/**
 *  @author zhangshijie on 2020/7/1
 *  description:
 */

fun toast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

val coroutineScopeMain = CoroutineScope(Dispatchers.Main)
const val TAG = "zsj"

inline fun <T> T.whenNull(l: (() -> Unit)): T {
    if (this == null) {
        l.invoke()
    }
    return this
}

inline fun <T> T.whenNotNull(l: (() -> Unit)): T {
    if (this == null) {
        l.invoke()
    }
    return this
}

inline fun View.onClick(crossinline block: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        override fun onClick(p0: View?) {
            block()
        }
    })
}

inline fun isEmpty(str: String): Boolean {
    if (str == null || str.isEmpty()) {
        return true
    }
    return false
}

inline fun loge(str: String) {
    Log.e("zsj", "loge: $str")
}

/**
 * dp 转 px
 *
 * @param dpValue dp 值
 * @return px 值
 */
fun dp2px(context: Context?, dpValue: Float): Int {
    context ?: return 0
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun getDisplayHeight(): Int {
    val metrics = DisplayMetrics()
    val wm = getApp()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    wm.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

private var sApplication: Application? = null


fun getApp(): Application? {
    if (sApplication != null) {
        return sApplication
    } else {
        try {
            sApplication =
                Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication", *arrayOfNulls(0))
                    .invoke(null as Any?, null as Array<Any?>?) as Application
            if (sApplication != null) {
                return sApplication
            }
        } catch (`var`: Exception) {
            `var`.printStackTrace()
        }
    }
    throw NullPointerException("u should init first")
}

package com.example.kotlindemo

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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
    this.setOnClickListener(object : View.OnClickListener{
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

inline fun loge(str:String){
    Log.e("zsj", "loge: $str" )
}
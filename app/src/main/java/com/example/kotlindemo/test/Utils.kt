package com.example.kotlindemo.test

import android.content.Context
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

package com.example.kotlindemo

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.content.ContextCompat


/**
 * @version
 * @author:zhangshijie
 * @Date:2020/8/7
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyApplication : Application(){
    lateinit var mContext: Context
    override fun onCreate() {
        super.onCreate()
        mContext = this

    }
}
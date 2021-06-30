package com.example.kotlindemo.extend

import android.view.View

/**
 * @author:zhangshijie
 * @Date:2021/2/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class HHA {

    var listener:ClickListener?=null
    fun HHHH(mListener:ClickListener){
        this.listener = mListener
    }
    interface ClickListener{
        fun method()
    }
}
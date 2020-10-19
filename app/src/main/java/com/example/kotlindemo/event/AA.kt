package com.example.kotlindemo.event

/**
 * @author:zhangshijie
 * @Date:2020/7/26
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class AA {
    private val aListener: AListener? = null
    fun aaa() {
        aListener?.a()
    }

    internal interface AListener {
        fun a()
    }
}
package com.example.kotlindemo.extend

import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/2/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
fun funa(params:()->String) :String{
    return params()
}

fun funb():String{
    return "params.toString()"
}


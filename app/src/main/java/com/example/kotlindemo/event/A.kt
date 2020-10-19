package com.example.kotlindemo.event

/**
 * @author:zhangshijie
 * @Date:2020/7/25
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class A {
    fun a() {
        val childPosition = 0
        val itemCount=1
        if (childPosition == 0) {
            // 第一个要设置PaddingLeft
        } else if (childPosition == itemCount - 1) {
            // 最后一个设置PaddingRight
        } else {
        }
    }
}
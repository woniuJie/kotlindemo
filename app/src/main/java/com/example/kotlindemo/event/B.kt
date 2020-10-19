package com.example.kotlindemo.event

/**
 * @author:zhangshijie
 * @Date:2020/7/26
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class B {
    private var bInterface: BInterface? = null
    fun aaa() {
        if (bInterface != null) {
            bInterface!!.b()
        }
    }

    fun setbInterface(bInterface: BInterface?) {
        this.bInterface = bInterface
    }

    interface BInterface {
        fun b()
    }
}
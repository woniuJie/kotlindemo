package com.example.kotlindemo.glide

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/9/9
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class BB(val ss:String) {

    companion object {
        fun newInstance(par:String): BB {
            return BB(par)
        }
    }


}
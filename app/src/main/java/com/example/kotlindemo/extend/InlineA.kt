package com.example.kotlindemo.extend

import com.example.kotlindemo.loge
import kotlin.math.log

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/3/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class InlineA {

    inline fun map(data: ArrayList<Int>, fn: (Int) -> Int): ArrayList<Int> {
        var result = ArrayList<Int>(data.size)
        for (i in data.indices){
            result.add(fn(data[i]))
        }
        return result
    }

    fun main(){
        var xxx = mutableListOf<Int>(1,2,3)
        var list = listOf<Int>(1,2,3,4)
        var xx = map(list as ArrayList<Int>,{it+1})
        loge("xxx${xx}")
    }

}
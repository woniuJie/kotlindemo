package com.example.kotlindemo.click

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/11/4
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
interface CaptureListener {
    fun caputre()
    fun recorderShort()
    fun recorderStart()
    fun recorderEnd(time: Long)
    fun recorderZoom()
    fun error(error: String)
}
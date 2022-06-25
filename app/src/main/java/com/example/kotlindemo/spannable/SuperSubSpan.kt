package com.example.kotlindemo.spannable

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/8
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class SuperSubSpan :ReplacementSpan(){
    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        val subText = text.subSequence(start,end)?:""
        return paint.measureText(subText.toString()).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val subText = text.subSequence(start,end)
        val fm = paint.fontMetricsInt
//        val subY = (fm.descent - fm.ascent)+(y - (fm.descent - fm.ascent))/2
        val subY = y
        canvas.drawText(subText.toString(),x,subY.toFloat(),paint)

    }
}
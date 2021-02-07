package com.example.kotlindemo.click

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/11/4
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class QuitButton(context: Context, size: Int) : View(context) {

    private val size: Int
    private var center_X: Int
    private var center_Y: Int
    private var strokeWidth: Float
    private var paint: Paint
    internal var path: Path

    init {
        this.size = size
        center_X = size / 2
        center_Y = size / 2

        strokeWidth = size / 15f

        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        path = Path()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(size, size / 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.moveTo(strokeWidth, strokeWidth / 2)
        path.lineTo(center_X.toFloat(), center_Y - strokeWidth / 2)
        path.lineTo(size - strokeWidth, strokeWidth / 2)
        canvas.drawPath(path, paint)
    }
}

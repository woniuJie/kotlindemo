package com.example.kotlindemo.textview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp2px

class MyXformodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val dp5 = dp2px(context,10f)

    val mPaint = Paint()
    private val mode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val layerId = canvas?.saveLayer(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            null,
            Canvas.ALL_SAVE_FLAG
        )
        mPaint.color = Color.parseColor("#ffffff")
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 12f
        mPaint.textSize = 100f
        canvas?.drawText("我是张士杰",0f,100f,mPaint)

        mPaint.setXfermode(mode)

//        val rect = Rect()
//        rect.top = 0
//        rect.left = 0
//        rect.bottom = bottom
//        rect.right = 50
//        mPaint.color = Color.parseColor("#1Affffff")
//        canvas?.drawRect(rect,mPaint)

        for (i in 0..5){
            val rect = RectF()
            rect.top = 0f
            rect.left = (dp5*i).toFloat()
            rect.bottom = bottom.toFloat()
            rect.right = (dp5*(i+1)).toFloat()
            val path = Path()
            path.addRect(rect,Path.Direction.CCW)

            mPaint.color = Color.parseColor("#ffffff")

            val alpha = (255 * i/10.0f*2.0f).toInt()
            mPaint.alpha = alpha

            canvas?.drawPath(path,mPaint)
        }

//        val rect = RectF()
//        rect.top = 0f
//        rect.left = 0f
//        rect.bottom = bottom.toFloat()
//        rect.right = dp5.toFloat()
//        val path = Path()
//        path.addRect(rect,Path.Direction.CCW)
//
//        mPaint.color = Color.parseColor("#1Affffff")
//
//        canvas?.drawPath(path,mPaint)
//
//        val rect1 = RectF()
//        rect1.top = 0f
//        rect1.left = dp5.toFloat()
//        rect1.bottom = bottom.toFloat()
//        rect1.right = (dp5+dp5).toFloat()
//        val path1 = Path()
//        path1.addRect(rect1,Path.Direction.CCW)
//
//        mPaint.color = Color.parseColor("#ffffff")
//        mPaint.alpha = (0.6*255).toInt()
//        canvas?.drawPath(path1,mPaint)

        mPaint.xfermode = null
        canvas?.restoreToCount(layerId!!)


    }
}
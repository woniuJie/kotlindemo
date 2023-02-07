package com.example.kotlindemo.textview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge

/**
 *  动态处理tab的模糊处理
 */
class HomeMulitplyBlurTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    val dp5 = dp2px(context,5f)

    val mPaint = Paint()
    val mPath = Path()

    private val mode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)

    /**
     *  是否需要进行模糊处理
     */
    private var isNeedBlur = false

    /**
     *  模糊处理最开始的left坐标
     */
    private var startBlurLocation :Float = 0f

    /**
     *  动态设置开始时的坐标
     */
    fun setStartBlurLocation(left:Float){
        isNeedBlur = true
        startBlurLocation = left
        postInvalidate()
    }

    /**
     *  还原所有的模糊处理
     */
    fun resetAllBlur(){
        isNeedBlur = false
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        val layerId = canvas?.saveLayer(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            null,
            Canvas.ALL_SAVE_FLAG
        )

        super.onDraw(canvas)

        if(!isNeedBlur){
            return
        }

        loge("onDraw startBlurLocation$startBlurLocation")

        mPaint.setXfermode(mode)

        for (i in 0..10){
            val mRectF = RectF()
            mPath.reset()
            mRectF.top = 0f
            mRectF.left = startBlurLocation+(dp5*i).toFloat()
            mRectF.bottom = bottom.toFloat()
            mRectF.right = startBlurLocation+(dp5*(i+1)).toFloat()
            mPath.addRect(mRectF,Path.Direction.CCW)

            mPaint.color = Color.parseColor("#ffffff")

            val alpha = (255 * i/10.0f*0.4f).toInt()
            mPaint.alpha = alpha

            canvas?.drawPath(mPath,mPaint)
        }

        mPaint.xfermode = null
        canvas?.restoreToCount(layerId!!)

    }
}
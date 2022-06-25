package com.example.kotlindemo.zhengze

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.kotlindemo.click.dp2px

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/9/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class CommunityProgressBgView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    /**
     * 要变色的宽度
     * */
    private var consumeWidth: Float = 0f

    private var rectF = RectF()

    /**
     * 负责变色动画类
     * */
    private val animator by lazy {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            consumeWidth = it.animatedValue as Float
            invalidate()
        }
        animator
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val mPaint = Paint()
        mPaint.color = Color.parseColor("#ff0000")

        rectF.left = left.toFloat()
        rectF.top = top.toFloat()
        rectF.right = consumeWidth
        rectF.bottom = bottom.toFloat()

        canvas.drawRoundRect(rectF, 8f, 8f, mPaint)
    }

    /**
     * 开始播放
     * */
    fun startPlay(width: Float) {

        animator.setFloatValues(0f, width)
        animator.duration = 1000
        animator.start()
    }


}
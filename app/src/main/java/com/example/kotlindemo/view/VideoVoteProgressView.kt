package com.example.kotlindemo.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import kotlin.math.acos

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/5/28
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class VideoVoteProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * 要变色的宽度
     * */
    private var consumeWidth: Float = 0f

    private val ddi = FloatArray(8)
    private val mPaint = Paint()
    private var mRadius = dp2px(context, 22f)
    private var rectF = RectF()
    private val mPath = Path()

    private var playColor = Color.parseColor("#00AB84")

    private var dp18 = dp2px(context, 18f)
    private var dp195 = dp2px(context, 195f)
    private var dp177 = dp2px(context, 177f)
    private var dp190 = dp2px(context, 190f)

    init {
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = playColor

        rectF.left = left.toFloat()
        rectF.top = top.toFloat()
        rectF.right = dp195.toFloat()
        rectF.bottom = bottom.toFloat()

        consumeWidth = dp190.toFloat()

//        canvas?.drawRoundRect(rectF,mRadius.toFloat(),mRadius.toFloat(),mPaint)
//
//        mPaint.color = Color.parseColor("#f0f0f0")
//            val cosDegree = acos(((consumeWidth-dp177.toFloat()) / mRadius.toDouble()))
//            val ang = Math.toDegrees(cosDegree)
//        loge("--"+ang)
//            mPath.addArc((dp195 - dp18*2).toFloat(), 0f, dp195.toFloat(), bottom.toFloat(), (0-ang).toFloat(), (ang * 2).toFloat())
//            mPath.close()
//            canvas?.drawPath(mPath, mPaint)


        val path1 = Path()
        val path2 = Path()
        path1.addRoundRect(rectF,mRadius.toFloat(),mRadius.toFloat(),Path.Direction.CW)

        val cosDegree = acos(((consumeWidth-dp177.toFloat()) / mRadius.toDouble()))
        val ang = Math.toDegrees(cosDegree)
        loge("--"+ang)
        path2.addArc((dp195 - dp18*2).toFloat(), 0f, dp195.toFloat(), bottom.toFloat(), (0-ang).toFloat(), (ang * 2).toFloat())
        path2.close()

        path1.op(path2,Path.Op.DIFFERENCE)
        canvas?.drawPath(path1, mPaint)

//        if (consumeWidth < mRadius) {
//            val cosDegree = acos(((mRadius - consumeWidth) / mRadius.toDouble()))
//            val ang = Math.toDegrees(cosDegree)
//            mPath.addArc(0f, 0f, bottom.toFloat(), bottom.toFloat(), (180 - ang).toFloat(), (ang * 2).toFloat())
//            mPath.close()
//            canvas?.drawPath(mPath, mPaint)
//        } else {
//            ddi[0] = mRadius.toFloat()
//            ddi[1] = mRadius.toFloat()
//            ddi[6] = mRadius.toFloat()
//            ddi[7] = mRadius.toFloat()
//            ddi[2] = 0f
//            ddi[3] = 0f
//            ddi[4] = 0f
//            ddi[5] = 0f
//            mPath.reset()
//            mPath.addRoundRect(rectF, ddi, Path.Direction.CW)
//            canvas?.drawPath(mPath, mPaint)
//        }
    }

    fun startPlay(width: Float, isVoteFinish: Boolean, playColor: Int) {
//        this.playColor = playColor
//        animator.setFloatValues(0f, width)
//        animator.duration = if (isVoteFinish) 0 else 1000
//        animator.start()
    }
}
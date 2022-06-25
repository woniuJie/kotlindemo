package com.example.kotlindemo.indicator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class PostVideoHotItemView : View {

    /**
     * 要变化的高度
     * */
    private var consumeHeight: Float = 0f
    private var mWidth : Float=0f

    private val ddi = FloatArray(8)
    private val mPaint = Paint()
    private var rectF = RectF()
    private val mPath = Path()
    private val mOpPath = Path()
    private val mRadius = dp2px(context,6f)

    private var playColor = Color.parseColor("#00AB84")

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }

    private fun initView(){
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
            consumeHeight = it.animatedValue as Float
            loge("子view onDrew测试 animator动画开始postInvalidate view$this")
            postInvalidate()
        }
        animator
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.color = playColor

        loge("子view onDrew测试 执行---mWidth$mWidth --consumeHeight$consumeHeight")

        rectF.left = 0f
        rectF.top = 0f
        rectF.right = mWidth
        rectF.bottom = consumeHeight

        ddi[0] = mRadius.toFloat()
        ddi[1] = mRadius.toFloat()
        ddi[6] = mRadius.toFloat()
        ddi[7] = mRadius.toFloat()
        ddi[2] = mRadius.toFloat()
        ddi[3] = mRadius.toFloat()
        ddi[4] = mRadius.toFloat()
        ddi[5] = mRadius.toFloat()
        mPath.reset()
        mPath.addRoundRect(rectF, ddi, Path.Direction.CW)
        canvas?.drawPath(mPath, mPaint)
    }

    fun startPlay(width : Float,startHeight:Float,endHeight: Float, isShowAnim: Boolean, playColor: Int) {
        this.playColor = playColor
        this.mWidth = width
        if(isShowAnim){
            animator.setFloatValues(startHeight, endHeight)
            animator.duration = 250
            animator.start()
        }else{
            consumeHeight = startHeight
            loge("子view onDrew测试 开始执行动画 当前view $this ")
            postInvalidate()
        }

    }

}
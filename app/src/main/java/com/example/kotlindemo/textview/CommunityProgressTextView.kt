package com.example.kotlindemo.textview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import com.example.kotlindemo.dp2px

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/9/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class CommunityProgressTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {


    /**
     * 文字为播放的颜色
     * */
    private val normalColor = Color.parseColor("#ffffff")

    /**
     * 文字播放的颜色
     * */
    private var playColor = Color.parseColor("#ff00ff")

    /**
     * 要变色的宽度
     * */
    private var consumeWidth: Float = 0f

    /**
     * 是否正在播放中
     * */
    private var isPlaying = false

    private val mPaint = paint

    /**
     * 要变色的区域
     * */
    private val path = Path()

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
        mPaint.color = normalColor
        super.onDraw(canvas)

        if (layout == null) {
            invalidate()
            return
        }


        val content = text.toString()
        val lineWidth = mPaint.measureText(
            content.substring(layout.getLineStart(0), layout.getLineEnd(0))
        )

        if (lineWidth <= consumeWidth) {
            // 如果是之前已经变色区域，直接添加到path中
            consumeWidth -= lineWidth
            path.addRect(
                layout.getLineLeft(0),
                layout.getLineTop(0).toFloat(),
                layout.getLineRight(0),
                layout.getLineBottom(0).toFloat(),
                Path.Direction.CCW
            )
        } else {
            path.addRect(
                layout.getLineLeft(0),
                layout.getLineTop(0).toFloat(),
                layout.getLineLeft(0) + consumeWidth - dp2px(context,5f),
                (layout.getLineBottom(0)).toFloat(),
                Path.Direction.CCW
            )
        }

        canvas.clipPath(path)



//        mPaint.color = playColor
        mPaint.alpha = (0.3*255).toInt()


        layout.draw(canvas)
    }

    /**
     * 开始播放
     * */
    fun startPlay(width: Float, isVoteFinish: Boolean, playColor: Int) {
        isPlaying = true
        this.playColor = playColor
        animator.setFloatValues(0f, width)
        animator.duration = if (isVoteFinish) 0 else 1000
        animator.start()
    }


}
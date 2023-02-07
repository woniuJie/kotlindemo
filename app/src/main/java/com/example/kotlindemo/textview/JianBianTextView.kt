package com.example.kotlindemo.textview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
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
class JianBianTextView @JvmOverloads constructor(
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

    private val mode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val c = canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), mPaint)

        mPaint.setXfermode(mode)
        path.addRect(
            0f,
            0f,
            20f,
            bottom.toFloat(),
            Path.Direction.CCW
        )

        mPaint.color = Color.parseColor("#ff00ff")
        canvas.drawPath(path, mPaint)

        mPaint.xfermode = null
        canvas?.restoreToCount(c!!)
    }
}
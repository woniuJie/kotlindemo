package com.example.kotlindemo.donghua

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

class WelfareForNewUserRectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextRectF = RectF()

    init {
        mPaint.setAntiAlias(true)
        mPaint.setStyle(Paint.Style.FILL)
        mPaint.setColor(Color.parseColor("#FFCAAA"))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mTextRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        canvas!!.drawRoundRect(mTextRectF, 42f, 42f, mPaint)
    }
}
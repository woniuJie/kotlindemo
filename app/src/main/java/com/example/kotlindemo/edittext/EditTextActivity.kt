package com.example.kotlindemo.edittext

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.TextPaint
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_edit_text.*


class EditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        val paint : TextPaint = tv_zhengchang_fake.paint
        paint.isFakeBoldText = true

        val paint1 : TextPaint = tv_stroke_fake.paint
        paint1.style = Paint.Style.FILL_AND_STROKE
        paint1.strokeWidth = 0.8f

        loge("zsj tv_stroke_fake ${tv_stroke_fake.paint.strokeWidth}")

        val paint2 : TextPaint = tv_bold.paint
        paint2.style = Paint.Style.FILL_AND_STROKE
        loge("zsj tvbold ${tv_bold.paint.strokeWidth}")



    }

    fun dp2px( dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
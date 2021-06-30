package com.example.kotlindemo.edittext

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_edit_text.*


class EditTextActivity : AppCompatActivity() {

    private var moduleTextBg: Drawable ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        moduleTextBg = resources.getDrawable(R.drawable.shape_experience_num_bg)

        val paint: TextPaint = tv_zhengchang_fake.paint
        paint.isFakeBoldText = true

        val paint1: TextPaint = tv_stroke_fake.paint
        paint1.style = Paint.Style.FILL_AND_STROKE
        paint1.strokeWidth = 0.8f

        val paint2: TextPaint = tv_stroke_fake_nomal.paint
        paint2.style = Paint.Style.FILL
        paint2.strokeWidth = 0f

        val paint3: TextPaint = tv_stroke_fake_nomal2.paint
        paint3.style = Paint.Style.FILL_AND_STROKE
        paint3.strokeWidth = 0f


        var num = 3
        tv_zsj_pbiaoqian?.text = Html.fromHtml("正在报名，还剩<font color= '#FF4040'>$num</font>个免费体验名额")

        var xx =
            "<p>正在报名，还剩<font color= '#FF4040'>3</font>个免费<font color= '#FF4040'>体验</font>名额</p>"
        tv_zsj_pbiaoqian1?.text = Html.fromHtml(xx)

        moduleTextBg = AAAUtils.getGradientFillDrawable(this,"#ff4455", 8)
        tv_drawable.text = StringBuilder("555").append("个名额")
        tv_drawable.setTextColor(Color.parseColor("#7744dd"))
        tv_drawable.background = moduleTextBg


        tv_drawable1.text = StringBuilder("555").append("个名额")
        tv_drawable1.setTextColor(Color.parseColor("#7744dd"))
        tv_drawable1.background = moduleTextBg

        tv_fuwenbenhuanhang.text = "1.阿卡的书法考级收到话费卡\n\n2.阿力度少杰发快乐少杰东方科技哈阿卡的好书法考级收到话费卡结合上阿卡焦迪舒服哈开机收到回复\n\n阿卡的少杰话费卡就是东方卡焦迪好书法考级看见啊都是废话卡焦迪好舒服卡结合的身份"
        tv_fuwenbenhuanhang1.text = "1.阿卡的书法考级收到话费卡\n\n2.阿力度少杰发快乐少杰东方科技哈阿卡的好书法考级收到话费卡结合上阿卡焦迪舒服哈开机收到回复\n\n阿卡的少杰话费卡就是东方卡焦迪好书法考级看见啊都是废话卡焦迪好舒服卡结合的身份"
        beautifulParagraph(tv_fuwenbenhuanhang)



        val province_name = ""
        val cityName = "changping"
        val addressSb = java.lang.StringBuilder()

        province_name?.let {
            if(it.isNotEmpty()){
                addressSb.append(it)
            }
        }
        cityName?.let {
            if(it.isNotEmpty()){
                if(addressSb.isNotEmpty()){
                    addressSb.append(".")
                }
                addressSb.append(it)
            }
        }

        loge("addressSb.toString()---${addressSb.toString()}")

    }

    private fun beautifulParagraph(textView: TextView) {
        val text = textView.text
        var startIndex = 0
        val sb = SpannableString(text)
        var index: Int
        while (text.indexOf("\n\n", startIndex).also { index = it } != -1) {
            sb.setSpan(RelativeSizeSpan(0.5f), index + 1, index + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            startIndex = index + 2
        }
        textView.text = sb
    }

    fun dp2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
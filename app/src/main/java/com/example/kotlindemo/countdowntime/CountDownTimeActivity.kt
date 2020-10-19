package com.example.kotlindemo.countdowntime

import android.graphics.Color
import android.graphics.Outline
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_count_down_time.*

class CountDownTimeActivity : AppCompatActivity() {

    private var timer : CountDownTimer ?=null
    private val startTime : Long = 400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down_time)


        timer = object : CountDownTimer(startTime, 1000) {
            override fun onTick(l: Long) {
                val day = l / (1000 * 24 * 60 * 60) //单位天
                val hour = (l - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60) //单位时
                val minute =
                    (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60) //单位分
                val second =
                    (l - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000 //单位秒
                tv_timer.setText(hour.toString() + "小时" + minute + "分钟" + second + "秒")
            }

            override fun onFinish() {

                //倒计时为0时执行此方法
            }
        }

        timer?.start()


        val drawable : MentorBackgroundDrawable = MentorBackgroundDrawable.builder()
            .left(30)
            .right(33)
            .leftColor(Color.parseColor("#FF7A7A"))
            .rightColor(Color.parseColor("#FFCACA"))
            .build()



        ll_draw.background = drawable
//
//        ll_draw.outlineProvider = object : ViewOutlineProvider(){
//            override fun getOutline(view: View, outline: Outline) {
//                outline.setRoundRect(0, 0, view.width, view.height, dp2px(20f).toFloat())
//            }
//        }
//        ll_draw.clipToOutline = true


    }

    fun dp2px(dpValue: Float): Int {
        val scale: Float = applicationContext.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }
}
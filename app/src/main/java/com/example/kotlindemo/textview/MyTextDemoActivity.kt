package com.example.kotlindemo.textview

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_my_text_demo_layout.*

class MyTextDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_text_demo_layout)
        val dp50 = dp2px(this,50f)
        val dp10 = dp2px(this,10f)

        var x = 0

        tv_mu_text?.startPlay(dp50.toFloat(),false, Color.parseColor("#ff00ff"))

        button_xxxx?.setOnClickListener {

            x += 15
            tv_blur?.setStartBlurLocation((x).toFloat())
        }

        button_xxxx_reset?.setOnClickListener {
            tv_blur?.resetAllBlur()
        }


//        val sb = StringBuilder()
//        for (i in 0..349){
//            val x1 = LocationBean("116.506467","39.874143","100", "北京市朝阳区王四营乡化工路辅路$i","0")
//            val locaitonStr = Gson().toJson(x1)
//            sb.append(locaitonStr)
//            sb.append(",")
//        }
//
//
//        LogUtils.e("locaitonStr",sb.toString())
    }


}
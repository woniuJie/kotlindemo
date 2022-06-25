package com.example.kotlindemo.indicator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_indicator_demo_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class IndicatorDemoActivity : AppCompatActivity() {

    private var mCurrentCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator_demo_layout)

         val dp6 = dp2px(this,6f)
         val dp12 = dp2px(this,12f)

        indicator_video_view.initTotalCount(10,1)

        btn_move_up.setOnClickListener {
            mCurrentCount+=1
            loge("mCurrentCount $mCurrentCount")
            indicator_video_view.setScrollCount(mCurrentCount)
        }

        btn_move_down.setOnClickListener {
            mCurrentCount-=1
            indicator_video_view.setScrollCount(mCurrentCount)
        }

        btn_move_next?.setOnClickListener {
            mCurrentCount = 1
            indicator_video_view.initTotalCount(4,1)
        }

        btn_move_shangyige?.setOnClickListener {
            mCurrentCount = 4
            indicator_video_view.initTotalCount(4,4)
        }


//        item_view_hot.startPlay(dp6.toFloat(),dp12.toFloat(),dp12.toFloat(),true,Color.parseColor("#00ff00"))
    }
}
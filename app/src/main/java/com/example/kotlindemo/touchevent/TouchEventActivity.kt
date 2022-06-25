package com.example.kotlindemo.touchevent

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_touch_event_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class TouchEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event_layout)

        viewgroup_touchevent?.setOnClickListener {
            loge("点击---父view点击事件")
        }
//        viewgroup_touevent2?.setOnClickListener {
//            loge("点击---父view点击事件 第二个")
//        }

        childview_touchevent?.setOnClickListener {
            loge("点击---子view点击事件")
        }


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        loge("activity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        loge("activity onTouchEvent")
        return super.onTouchEvent(event)
    }
}
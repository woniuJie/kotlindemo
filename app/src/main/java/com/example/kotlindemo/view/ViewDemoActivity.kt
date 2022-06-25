package com.example.kotlindemo.view

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_view_demo_layout.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/5/5
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class ViewDemoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_demo_layout)

        val dp100 = dp2px(this,100f)

        val animator = ObjectAnimator.ofFloat(view_zsjjj,"translationY",dp100.toFloat(),0f)
        animator.start()

        val dp50 = dp2px(this,150f)

        view_zsjjj.setOnClickListener {
            video_vote_progress_view.startPlay(dp50.toFloat(),false, Color.parseColor("#f0f0f0"))
        }


    }
}
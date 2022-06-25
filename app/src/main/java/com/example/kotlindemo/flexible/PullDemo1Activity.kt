package com.example.kotlindemo.flexible

import android.graphics.Outline
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.example.kotlindemo.R
import com.example.kotlindemo.click.dp2px
import com.example.kotlindemo.flexible.flexible.FlexibleLayout
import kotlinx.android.synthetic.main.activity_pull_demo1.*
import kotlinx.android.synthetic.main.medical_activity_main.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/9/7
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class PullDemo1Activity : AppCompatActivity() {
    var scrollView:NestedScrollView ?=null
    var imagev:ImageView ?=null
    var flx: FlexibleLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medical_activity_main)
        initView()
    }

    private fun initView() {
        scrollView = findViewById(R.id.nestedScrollView)
        imagev = findViewById(R.id.header)
        flx = findViewById(R.id.flexible_layout)
        flx?.setHeader(header)
            ?.setReadyListener {
                Log.e("zsj", "initView: crollView?.scaleY  ${scrollView?.scaleY }" )
                scrollView?.scaleY == 0f
            }
    }
}
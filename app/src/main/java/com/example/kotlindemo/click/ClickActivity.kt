package com.example.kotlindemo.click

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_click.*

class ClickActivity : AppCompatActivity() {

    var captureLayout: CaptureLayout ?=null
    var button : MyClickButton ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        captureLayout = CaptureLayout(this)
        val captureLayout_param = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        captureLayout_param.gravity = Gravity.BOTTOM
        captureLayout?.layoutParams = captureLayout_param

        button = MyClickButton(this)
        val button_param = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        button_param.gravity = Gravity.BOTTOM
        button?.layoutParams = captureLayout_param

        constraint_layout.addView(button)

        initListener()
    }

    fun initListener() {
        captureLayout?.mQuitListener = object : QuitListener {
            override fun quit() {
            }
        }
        captureLayout?.mTypeListener = object : TypeListener {
            override fun confirm() {
            }

            override fun cancle() {
            }
        }
        captureLayout?.mCaptureListener = object : CaptureListener {
            override fun error(error: String) {
            }

            override fun recorderZoom() {

            }

            override fun caputre() {
            }

            override fun recorderEnd(time: Long) {
            }

            override fun recorderShort() {
            }

            override fun recorderStart() {
            }

        }
    }

}
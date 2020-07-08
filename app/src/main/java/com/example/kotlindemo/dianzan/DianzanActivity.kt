package com.example.kotlindemo.dianzan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_dianzan.*
import toast

class DianzanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dianzan)

        loveView?.setDoubleClickListener(object : OnDoubleClickListener {
            override fun onDoubleClick() {
                toast("点击了")
            }
        })
    }
}
package com.example.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlindemo.network.DemoActivity
import com.example.kotlindemo.dianzan.DianzanActivity
import com.example.kotlindemo.xiecheng.XieChengActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onCorClick(view: View) {
        Intent(this, DemoActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onDianZanClick(view: View) {
        Intent(this, DianzanActivity::class.java).run {
            startActivity(this)
        }
    }
    fun onXieCheng(view: View) {
        Intent(this, XieChengActivity::class.java).run {
            startActivity(this)
        }
    }
}
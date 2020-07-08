package com.example.kotlindemo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.kotlindemo.demo.DemoActivity
import com.example.kotlindemo.dianzan.DianzanActivity
import kotlin.math.log

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
}
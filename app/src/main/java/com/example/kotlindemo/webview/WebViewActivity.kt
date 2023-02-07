package com.example.kotlindemo.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_webview_layout.*

class WebViewActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_layout)

        webview_zsj.loadUrl("www.baidu.com")


    }
}
package com.example.kotlindemo.glide

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : AppCompatActivity() {

    private val mLevelDetailLists by lazy { ArrayList<String>(3) }


    private val xx = ArrayList<String>(3)

    private val xxx = mutableListOf<AAA<*>>()
    private val yyy by lazy { ArrayList<AAA<*>>() }

    private val zzz = arrayListOf<String>()

    private val xxxxxx : String ?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        xxxxxx?.let {
            //不为空
            it.toString()
        }?:let {
            //为空
        }


        loge("xxx${mLevelDetailLists.size}")
        loge("xxx${xx.size}")




    }
}
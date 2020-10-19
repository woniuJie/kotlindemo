package com.example.kotlindemo.async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_async.*

class AsyncActivity : AppCompatActivity() {


    val xx : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        AsyncLayoutInflater(this).inflate(
//            R.layout.activity_async,
//            null,
//            object : AsyncLayoutInflater.OnInflateFinishedListener {
//                override fun onInflateFinished(view: View, resid: Int, parent: ViewGroup?) {
//                    setContentView(view)
//                    tv_async.text = "zsj"
//                }
//            })

        val async = AsyncLayoutInflatePlus(this)
        async.inflate(R.layout.activity_async,null,object:AsyncLayoutInflatePlus.OnInflateFinishedListener{
            override fun onInflateFinished(view: View, resid: Int, parent: ViewGroup?) {
                setContentView(view)
                tv_async.text = "zsj"
            }
        })

        val xx : String ?= null
        xx?.let {
            loge("xx不为空的时候")
        }?:let {
            loge("xx为空的时候")
        }

    }
}
package com.example.kotlindemo.clipchlidren

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.activity_clip_children_layout.*

class ClipChilderenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip_children_layout)

        sy_bottom_bar?.setData()

        Class.forName("")



        val jsonStr = "{\"a\":\"xxx\",\"b\":{\"zz\":\"1\",\"vv\":{\"aa\":\"aaa\"}}}"


        val xStr = "{\"xx\":\"zhang\",\"xxxx\":\"shijie\"}"
//        val a = Gson().fromJson(jsonStr,DateAA::class.java)
        val a = Gson().fromJson(xStr,MyB::class.java)

//        loge("a.b is LinkedTreeMap<*,*> ${a.b is LinkedTreeMap<*,*>}")
        loge("a.b is LinkedTreeMap<*,*> ${a.xx}  ${a.xxxx}")


    }

}
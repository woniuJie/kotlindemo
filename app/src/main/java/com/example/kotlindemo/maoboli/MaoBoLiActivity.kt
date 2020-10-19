package com.example.kotlindemo.maoboli

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_mao_bo_li.*


class MaoBoLiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mao_bo_li)

//        button_mao.onClick {
//            Blurry.with(this)
//                .radius(25)
//                .sampling(1)
//                .async()
//                .capture(findViewById(R.id.iv_left))
//                .into(findViewById(R.id.iv_left))
//
//
//            Blurry.with(this)
//                .radius(25)
//                .sampling(1)
//                .color(Color.argb(66, 255, 255, 0))
//                .async()
//                .onto(findViewById<View>(R.id.ll_mao) as ViewGroup)

    }

    override fun onStart() {
        super.onStart()
        blurLayout.startBlur()
    }

    override fun onStop() {
        blurLayout.pauseBlur()

        super.onStop()
    }
}
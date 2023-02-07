package com.example.kotlindemo.maoboli

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_mao_bo_li.*
import java.lang.StringBuilder


class MaoBoLiActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mao_bo_li)

//        Glide.with(this)
//            .asBitmap()
//            .load("https://img2.soyoung.com/user/20220118/4/29155181295c6f5ee2b51c5ed6c1ffea_100_100.jpg")
//            .into(object : SimpleTarget<Bitmap?>() {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
//                    Blurry.with(this@MaoBoLiActivity)
//                        .sampling(2)
//                        .radius(4)
//                        .async()
//                        .color(Color.parseColor("#99000000"))
//                        .from(resource)
//                        .into(iv_img)
//                }
//            })

//        Glide.with(this)
//            .load("https://img2.soyoung.com/origin/20220511/7/b59b85f3c7a966d1a17dc36d22700722.png")
//            .into(iv_image2)


    }


}
package com.example.kotlindemo.transitionname

import android.app.ActivityOptions
import android.app.SharedElementCallback
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_transition_aactivity.*

class TransitionAActivity : AppCompatActivity() {

    var mWidth = 0
    var mHeight = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.allowEnterTransitionOverlap = true
        window.allowReturnTransitionOverlap = true

        setContentView(R.layout.activity_transition_aactivity)

        setEnterSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                super.onMapSharedElements(names, sharedElements)
                sharedElements?.put("view_middle",view_middle)
            }
        })
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        mWidth = dm.widthPixels
        mHeight = dm.heightPixels
        val url = "https://img2.soyoung.com/tieba/ios/post/20220104/7/ba323c1d59d0e91e66c6a7064ef81283_570.jpg"
        Glide.with(this).load(url).into(iv_transition_a)
        setImageLayoutParams(iv_transition_a,570,1014)

        iv_transition_a?.setOnClickListener {
            val bundle =
                ActivityOptions.makeSceneTransitionAnimation(this, iv_transition_a, "activitytransition")
                    .toBundle()
            startActivity(Intent(this, TransitionBActivity::class.java), bundle)

//            startActivity(Intent(this, TransitionBActivity::class.java))
        }

    }

    protected fun setImageLayoutParams(view: View?, width: Int, height: Int) {
        if (view == null) {
            return
        }
        val layoutParams = view.layoutParams
        if (width > 0 && height > 0) {
            layoutParams.width = mWidth
            var ratio = width * 1f / height

            layoutParams.height = ((mWidth / ratio).toInt()) //最大高宽比是4:3
        } else { //如果出现width or height=0的情况，则将图片设置成方形，宽高为mWidth
            layoutParams.width = mWidth
            layoutParams.height = mWidth
        }
        view.layoutParams = layoutParams
    }
}
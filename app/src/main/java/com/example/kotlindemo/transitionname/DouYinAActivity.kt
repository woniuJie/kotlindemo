package com.example.kotlindemo.transitionname

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_douyin_aactivity.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/2/14
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class DouYinAActivity : AppCompatActivity(){
    var mWidth = 0
    var mHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_aactivity)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        mWidth = dm.widthPixels
        mHeight = dm.heightPixels
        val url = "https://img2.soyoung.com/tieba/ios/post/20220104/7/ba323c1d59d0e91e66c6a7064ef81283_570.jpg"
        Glide.with(this).load(url).into(iv_douyin_a)
        setImageLayoutParams(iv_douyin_a,570,1014)

        iv_douyin_a?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                iv_douyin_a?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                val mTop = iv_douyin_a.top
                loge("mTop $mTop")
            }

        })

        tv_music_name.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv_music_name.setSingleLine(true);
        tv_music_name.setSelected(true);
        tv_music_name.setFocusable(true);
        tv_music_name.setFocusableInTouchMode(true);

        iv_douyin_a?.setOnClickListener {

//            val bundle =
//                ActivityOptions.makeSceneTransitionAnimation(this, iv_transition_a, "activitytransition")
//                    .toBundle()
//            startActivity(Intent(this, TransitionBActivity::class.java), bundle)

            startActivity(Intent(this, DouYinBActivity::class.java))
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
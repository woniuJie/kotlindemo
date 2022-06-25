package com.example.kotlindemo.transitionname

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_douyin_bactivity.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/2/14
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class DouYinBActivity : AppCompatActivity(){

    var mWidth = 0
    var mHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_bactivity)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        mWidth = dm.widthPixels
        mHeight = dm.heightPixels
        val url_top = "https://img2.soyoung.com/tieba/ios/post/20220104/7/ba323c1d59d0e91e66c6a7064ef81283_570.jpg"
        Glide.with(this).load(url_top).into(iv_douyin_b_top)
        Glide.with(this).load(url_top).into(iv_douyin_b_top_1)
        setImageLayoutParams(iv_douyin_b_top,570,1014,false)
        setImageLayoutParams(iv_douyin_b_top_1,570,1014,true)

        iv_douyin_b_top?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                iv_douyin_b_top?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                val mTop = iv_douyin_b_top.top
                loge("---mTop $mTop---height${iv_douyin_b_top.height} bottom${iv_douyin_b_top.bottom} srceenHeight$mHeight")
                val clTop = mHeight - iv_douyin_b_top.height
                cl_container.translationY = (mTop).toFloat()

                val topTranslateAnimator = ObjectAnimator.ofFloat(iv_douyin_b_top,"translationY",0f,-mTop.toFloat()).setDuration(400)
                val topScaleAnimator = ObjectAnimator.ofFloat(iv_douyin_b_top,"scaleY",1.0f,0.75f).setDuration(400)
                val bottomTranslateAnimator = ObjectAnimator.ofFloat(cl_container,"translationY",mTop.toFloat(),0f).setDuration(400)
                val animatorSet = AnimatorSet()
                animatorSet.playTogether(topTranslateAnimator,bottomTranslateAnimator)
                animatorSet.start()

//                val mHeight = iv_douyin_b_top.height
//                val realHeight = (mHeight*0.75).toInt()
//                loge("mHeight-$mHeight realHeight$realHeight")
//
//                val heightAnimator = ValueAnimator.ofInt(mHeight,realHeight)
//                heightAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener{
//                    override fun onAnimationUpdate(animation: ValueAnimator?) {
//                        animation?.let {
//                            val h = animation.getAnimatedValue() as Int
//                            loge("h-- $h")
//                            iv_douyin_b_top.layoutParams.height = h
//                            iv_douyin_b_top.layoutParams.width = mWidth
//                            iv_douyin_b_top.requestLayout()
//                        }
//                    }
//                })
//                heightAnimator.setDuration(400)
//                heightAnimator.start()

            }
        })

//        setBottomSheetBehavior()

    }


    protected fun setImageLayoutParams(view: View?, width: Int, height: Int,needRatio:Boolean) {
        if (view == null) {
            return
        }
        val layoutParams = view.layoutParams
        if (width > 0 && height > 0) {
            layoutParams.width = mWidth
            var ratio = width * 1f / height
            if(needRatio){
                ratio = if (3 * 1f / 4 - ratio > 0) 3 * 1f / 4 else ratio
            }
            layoutParams.height = ((mWidth / ratio).toInt()) //最大高宽比是4:3
        } else { //如果出现width or height=0的情况，则将图片设置成方形，宽高为mWidth
            layoutParams.width = mWidth
            layoutParams.height = mWidth
        }
        view.layoutParams = layoutParams
    }

    private fun setBottomSheetBehavior() {
        val bottomSheetBehavior = BottomSheetBehavior.from(cl_container)
        bottomSheetBehavior.setPeekHeight(mHeight)
        bottomSheetBehavior.setHideable(false)
        bottomSheetBehavior.setSkipCollapsed(true)
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
            }

            override fun onSlide(p0: View, p1: Float) {
            }

        })
    }

    override fun finish() {

        iv_douyin_b_top_1?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                iv_douyin_b_top_1?.viewTreeObserver?.removeOnGlobalLayoutListener(this)


                val mBottom = iv_douyin_b_top_1.bottom
                val tranY = mHeight - mBottom

                val bottomTranslateAnimator = ObjectAnimator.ofFloat(cl_container,"translationY",mBottom.toFloat(),tranY.toFloat()).setDuration(400)
                val animatorSet = AnimatorSet()
                animatorSet.playTogether(bottomTranslateAnimator)
                animatorSet.start()
            }
        })

        super.finish()
//        overridePendingTransition(0, R.anim.dialog_dismiss)
    }
}
package com.example.kotlindemo.transitionname

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_transition_bactivity.*
import com.bumptech.glide.Priority
import com.bumptech.glide.request.target.DrawableImageViewTarget


class TransitionBActivity : AppCompatActivity() {
    var mWidth = 0
    var mHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_bactivity)
        postponeEnterTransition()
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        mWidth = dm.widthPixels
        mHeight = dm.heightPixels
        val url = "https://img2.soyoung.com/tieba/ios/post/20220104/7/ba323c1d59d0e91e66c6a7064ef81283_570.jpg"
        val url1 = "https://img2.soyoung.com/tieba/android/post/20211231/9/fbcf7f5c32de80ac31d3def0df1860ab_640_853.jpg"
        Glide.with(this)
            .load(url)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : DrawableImageViewTarget(iv_transition_b) {
                override fun setResource(resource: Drawable?) {
                    super.setResource(resource)
                    //图片加载完成的回调中，启动过渡动画
                    supportStartPostponedEnterTransition()
                }
            })
        setImageLayoutParams(iv_transition_b,570,1014,true)
//        val bottomTranslateAnimator = ObjectAnimator.ofFloat(tv_bottom_container,"translationY",0f,-1000f).setDuration(400)
//        val animatorSet = AnimatorSet()
//        animatorSet.playTogether(bottomTranslateAnimator)
//        animatorSet.start()

//        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into()

        gesturelayout_rootview?.addGestureFinishListener {

            val bottomTranslateAnimator = ObjectAnimator.ofFloat(tv_bottom_container,"translationY",0f,500f).setDuration(300)

            bottomTranslateAnimator.addListener(object :  Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    finish()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }

            })

            bottomTranslateAnimator.start()

//            val animatorSet = AnimatorSet()
//            animatorSet.playTogether(bottomTranslateAnimator)
//            animatorSet.start()


        }
    }



    protected fun setImageLayoutParams(view: View?, width: Int, height: Int, needRatio:Boolean) {
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

    override fun finish() {
        super.finish()

    }
}
package com.example.kotlindemo.transitionname

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_my_test_b_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/2/18
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyTestBActivity : AppCompatActivity(){
    var mH = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_test_b_layout)

        ll_bbzsj?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                ll_bbzsj?.viewTreeObserver?.removeOnGlobalLayoutListener(this)

                mH = ll_bbzsj.height
                val valueAnimator = ValueAnimator()
                valueAnimator.apply {
                    setIntValues(mH, mH/2)
                    interpolator = AccelerateInterpolator()
                    addUpdateListener {
                        ll_bbzsj?.layoutParams?.height = it.animatedValue as Int?
                        ll_bbzsj?.requestLayout()
                    }
                    doOnEnd {

                    }
                }
                valueAnimator.start()
            }
        })

        cl_router?.addGestureFinishListener {



            val valueAnimator = ValueAnimator()
            valueAnimator.apply {
                setIntValues(mH/2, mH)
                interpolator = AccelerateInterpolator()
                addUpdateListener {
                    ll_bbzsj?.layoutParams?.height = it.animatedValue as Int?

                    ll_bbzsj?.requestLayout()
                }
                doOnEnd {
                    finish()
                }
            }
            valueAnimator.start()
        }

        ll_bbzsj?.setOnClickListener {
            val valueAnimator = ValueAnimator()
            valueAnimator.apply {
                setIntValues(mH/2, mH)
                interpolator = AccelerateInterpolator()
                addUpdateListener {
                    ll_bbzsj?.layoutParams?.height = it.animatedValue as Int?
                    ll_bbzsj?.requestLayout()
                }
                doOnEnd {
                    finish()
                }
            }
            valueAnimator.start()
        }


    }

}
package com.example.kotlindemo.dianzan

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlindemo.R
import kotlin.random.Random

/**
 * @author : zhangshijie
 * e-mail : zhangshijie@soyoung.com
 * date   : 2020/7/7
 * description   :
 */
class LikeGuideView(context: Context) : ConstraintLayout(context) {
    var mContext: Context? = null

    //随机爱心的旋转角度
    var num = floatArrayOf(-15f, -10f, 0f, 10f, 15f)

    //判断是否是连续的点击事件
    private val mHits = LongArray(2)

    var aa = "";

    private var clickListener: OnDoubleClickListener? = null

    constructor(context: Context, attrs: AttributeSet) : this(context) {
        mContext = context
    }

    //用这个来判断是否是双击事件，判断数组中pos=1的点击事件的时间与数组中pos=0的点击事件的时间差值是否小于500，若是小于500认为是双击事件，这时需要绘制爱心图片
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
        mHits[mHits.size - 1] = SystemClock.uptimeMillis()

        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {

            clickListener?.onDoubleClick()

            aa as Int

            var iv = ImageView(context)

            //设置展示图片的大小
            var lp = LayoutParams(300, 300)

            //设置图片的相对坐标是父布局的左上角开始的
            lp.leftToLeft = 0
            lp.topToTop = 0

            //设置图片相对于点击位置的坐标
            lp.leftMargin = (event?.x!! - 150F).toInt()
            lp.topMargin = (event.y!! - 230F).toInt()

            //设置图片资源
            iv.setImageDrawable(resources.getDrawable(R.drawable.ic_heart_red))
            iv.layoutParams = lp

            //把IV添加到父布局中
            addView(iv)

            var animatorSet = AnimatorSet()
            animatorSet.play(
                scaleAni(iv, "scaleX", 2f, 0.9f, 100, 0)
            )
                .with(scaleAni(iv, "scaleY", 2f, 0.9f, 100, 0))
                .with(rotation(iv, 0, 0, num[Random.nextInt(4)]))
                .with(alphaAni(iv, 0F, 1F, 100, 0))
                .with(scaleAni(iv, "scaleX", 0.9f, 1F, 50, 150))
                .with(scaleAni(iv, "scaleY", 0.9f, 1F, 50, 150))
                .with(translationY(iv, 0f, -600F, 800, 400))
                .with(alphaAni(iv, 1F, 0F, 300, 400))
                .with(scaleAni(iv, "scaleX", 1F, 3f, 700, 400))
                .with(scaleAni(iv, "scaleY", 1F, 3f, 700, 400))


            animatorSet.start()

            animatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    //当动画结束，把控件从父布局移除
                    removeViewInLayout(iv)
                }
            })

        }

        return super.onTouchEvent(event)
    }

    //vararg可变参数修饰符，此处可以传入多个Float类型值
    fun rotation(view: View, time: Long, delayTime: Long, vararg values: Float): ObjectAnimator {
        val ani = ObjectAnimator.ofFloat(view, "rotation", *values)
        ani.duration = time
        ani.startDelay = delayTime
        ani.interpolator = TimeInterpolator { input -> input }
        return ani
    }

    fun alphaAni(view: View, from: Float, to: Float, time: Long, delayTime: Long): ObjectAnimator {
        val ani = ObjectAnimator.ofFloat(view, "alpha", from, to)
        ani.interpolator = LinearInterpolator()
        ani.duration = time
        ani.startDelay = delayTime
        return ani
    }

    fun translationY(
        view: View,
        from: Float,
        to: Float,
        time: Long,
        delayTime: Long
    ): ObjectAnimator {
        val ani = ObjectAnimator.ofFloat(view, "translationY", from, to)
        ani.interpolator = LinearInterpolator()
        ani.startDelay = delayTime
        ani.duration = time
        return ani
    }

    fun translationX(
        view: View,
        from: Float,
        time: Long,
        to: Float,
        delayTime: Long
    ): ObjectAnimator {
        val ani = ObjectAnimator.ofFloat(view, "translationX", from, to)
        ani.startDelay = delayTime
        ani.duration = time
        ani.interpolator = LinearInterpolator()
        return ani
    }

    fun scaleAni(
        view: View,
        propertyName: String,
        from: Float,
        to: Float,
        time: Long,
        delayTime: Long
    ): ObjectAnimator {
        val ani = ObjectAnimator.ofFloat(view, propertyName, from, to)
        ani.interpolator = LinearInterpolator()
        ani.startDelay = delayTime
        ani.duration = time
        return ani
    }

    fun setDoubleClickListener(listener: OnDoubleClickListener) {
        clickListener = listener
    }
}
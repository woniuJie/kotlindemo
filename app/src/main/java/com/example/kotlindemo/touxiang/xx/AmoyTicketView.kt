package com.example.kotlindemo.touxiang.xx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.touxiang.UserAvatarEntity
import com.makeramen.roundedimageview.RoundedImageView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

/**
 * 头像轮播动画 设置2个child 动态add进去4个childview
 * 0 1 2 3
 * 【共计4个view】通过initFirstView()初始化第一个，通过for循环动态add进去3个（2、1、0）
 * onAnimationEnd 每执行一次结束时 removeall   childNum=2，add 1个view
 * by zhangyan
 */
class AmoyTicketView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private var eachWidth = 0
    private var distance = 0
    private var browseEntities: List<UserAvatarEntity>? = null
    private var position = 0
    private var mContext: WeakReference<Context?>? = null
    private var stopAnimator = false
    private var subscribe: Disposable? = null
    private val childNum: Int
    private fun initView(context: Context) {
        mContext = WeakReference(context)
        eachWidth = dp2px(context, 30f)
        val eachMargin = dp2px(context, 5f)
        distance = eachWidth - eachMargin
        initFirstView()
        for (i in childNum downTo 0) {
            val layoutParams = getLayoutParams(i)
            val roundedImageView: ImageView = imageView
            addView(roundedImageView, layoutParams)
        }
    }

    private fun initFirstView() {
        val imageView = imageView
        imageView.scaleX = 0f
        imageView.scaleY = 0f
        addView(imageView, getLayoutParams(childNum))
    }

    private val imageView: RoundedImageView
        private get() {
            val roundedImageView =
                RoundedImageView(context)
            roundedImageView.cornerRadius = eachWidth / 2.0f
            roundedImageView.setBorderWidth(R.dimen.d_1)
            roundedImageView.borderColor = Color.WHITE
            roundedImageView.scaleType = ImageView.ScaleType.FIT_XY
            return roundedImageView
        }

    private fun getLayoutParams(i: Int): LayoutParams {
        val layoutParams = LayoutParams(eachWidth, eachWidth)
        layoutParams.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
        val marginRight = distance * i
        layoutParams.setMargins(0, 0, marginRight, 0)
        return layoutParams
    }

    fun setBrowseEntities(browseEntities: List<UserAvatarEntity>?) {
        if (null == browseEntities || browseEntities.isEmpty()) {
            visibility = View.GONE
            return
        }
        val childCount = childCount
        if (childCount == 0) return
        if (browseEntities.size < childCount) return
        this.browseEntities = browseEntities
        position = childCount - 1
        for (i in 0 until childCount) {
            val (url) = browseEntities[i]
            val imageView =
                getChildAt(childCount - (i + 1)) as RoundedImageView
            Glide.with(context).load(url).into(imageView)
        }
        startAnimator()
    }

    fun startAnimator() {
        if (!stopAnimator) {
            stopAnimator = true
            startAnimation()
        }
    }

    fun stopAnimator() {
        stopAnimator = false
        if (null != subscribe) {
            subscribe!!.dispose()
            subscribe = null
        }
    }

    private fun startAnimation() {
        if (null != subscribe) {
            subscribe!!.dispose()
            subscribe = null
        }
        subscribe = Observable.timer(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { startAnimations() }
    }

    private fun startAnimations() {
        if (!stopAnimator) return
        if (null == browseEntities || browseEntities!!.isEmpty()) {
            stopAnimator = false
            return
        }
        val valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
        valueAnimator.duration = 600
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            val childCount = childCount - 1
            val v = 1.0f - animatedValue
            val translationX = distance * v
            for (i in 0 until getChildCount()) {
                val childView =
                    getChildAt(i) as ImageView
                if (i == childCount) {
                    childView.scaleX = animatedValue
                    childView.scaleY = animatedValue
                } else if (i == 0) {
                    childView.scaleX = v
                    childView.scaleY = v
                } else {
                    val layoutParams =
                        childView.layoutParams as LayoutParams
                    val marginRight = distance * (childCount - i)
                    layoutParams.setMargins(0, 0, (marginRight - translationX).toInt(), 0)
                    childView.layoutParams = layoutParams
                }
            }
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                val childCount = childCount - 1
                val imageView =
                    getChildAt(childCount) as ImageView
                imageView.pivotX = eachWidth.toFloat()
                imageView.pivotY = eachWidth / 2.0f
            }

            override fun onAnimationEnd(animation: Animator) {
                val childCount = childCount - 1
                removeViewAt(childCount)
                position++
                if (position >= browseEntities!!.size) {
                    position = 0
                }
                val imageView: ImageView = imageView
                val browseEntity = browseEntities!![position]
                if (null != browseEntity) {
                    val img = browseEntity.url
                    if (!TextUtils.isEmpty(img) && mContext != null && mContext!!.get() != null) {
                        Glide.with(context).load(img!!.trim { it <= ' ' }).into(imageView)
                    }
                }
                imageView.scaleX = 0f
                imageView.scaleY = 0f
                addView(imageView, 0, getLayoutParams(childNum))
                startAnimation()
            }
        })
        valueAnimator.start()
    }

    fun onDestroy() {
        stopAnimator()
        mContext!!.clear()
        mContext = null
    }

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AmoyTicketView)
        childNum = a.getInt(R.styleable.AmoyTicketView_childNum, 5)
        a.recycle()
        initView(context)
        isFocusable = false
    }
}
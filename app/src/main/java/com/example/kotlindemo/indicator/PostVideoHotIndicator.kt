package com.example.kotlindemo.indicator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class PostVideoHotIndicator : FrameLayout {

    private var mIndicatorSelectColor: Int = 0
    private var mIndicatorUnSelectColor: Int = 0
    private var mIndicatorSelectHeight: Int = 0
    private var mIndicatorUnSelectHeight: Int = 0
    private var mIndicatorItemMargin: Int = 0
    private var mIndicatorItemWidth: Int = 0

    private var mCurrentCount: Int = 0
    private var mTotalCount: Int = 0

    private var mNormalScrollHeight: Int = 0
    private var mSelectScrollHeight: Int = 0
    private var mHeight: Int = 0

    private var dp11 = dp2px(context,11f)

    /**
     *  是否正在进行动画
     */
    private var isAnimatorIng = false

    var onCurrentCountListener: ((pos: Int) -> Unit)? = null


    companion object {
        const val SCROLL_DIRECTION_UP = 1
        const val SCROLL_DIRECTION_DOWN = 2
        const val SCROLL_DIRECTION_IDEL = 3

    }

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {


        mHeight = dp2px(context, 80f)

        val a = context.obtainStyledAttributes(
            attrs, R.styleable.PostVideoHotIndicator,
            defStyleAttr, 0
        )

        mIndicatorSelectColor = a.getColor(
            R.styleable.PostVideoHotIndicator_indicatorSelectColor,
            mIndicatorSelectColor
        )
        mIndicatorUnSelectColor = a.getColor(
            R.styleable.PostVideoHotIndicator_indicatorUnSelectColor,
            mIndicatorUnSelectColor
        )
        mIndicatorSelectHeight = a.getDimensionPixelSize(
            R.styleable.PostVideoHotIndicator_indicatorSelectHeight,
            mIndicatorSelectHeight
        )
        mIndicatorUnSelectHeight = a.getDimensionPixelSize(
            R.styleable.PostVideoHotIndicator_indicatorUnSelectHeight,
            mIndicatorUnSelectHeight
        )
        mIndicatorItemWidth = a.getDimensionPixelSize(
            R.styleable.PostVideoHotIndicator_indicatorItemWidth,
            mIndicatorItemWidth
        )
        mIndicatorItemMargin = a.getDimensionPixelSize(
            R.styleable.PostVideoHotIndicator_indicatorItemMargin,
            mIndicatorItemMargin
        )

        mNormalScrollHeight = mIndicatorUnSelectHeight + mIndicatorItemMargin
        mSelectScrollHeight = mIndicatorSelectHeight + mIndicatorItemMargin
        a.recycle()
    }

    fun setTotalCount(totalCount: Int): PostVideoHotIndicator {
        this.mTotalCount = totalCount
        return this
    }

    fun setCurrentCount(currentCount: Int) {
        if (!isAnimatorIng) {
            if (mTotalCount > 0 && currentCount > -1 && currentCount < mTotalCount) {
                var scrollDirection = SCROLL_DIRECTION_IDEL
                var scrollItemCount = 1
                if (currentCount < mCurrentCount) {
                    scrollDirection = SCROLL_DIRECTION_DOWN
                    scrollItemCount = mCurrentCount - currentCount
                } else if (currentCount > mCurrentCount) {
                    scrollDirection = SCROLL_DIRECTION_UP
                    scrollItemCount = currentCount - mCurrentCount
                }

                this.mCurrentCount = currentCount

                onCurrentCountListener?.invoke(currentCount)

                isAnimatorIng = true
                startAnimation(scrollDirection,scrollItemCount)

            }
        }
    }


    private fun startAnimation(direction: Int,scrollItemCount:Int) {
        if (direction == SCROLL_DIRECTION_IDEL) {
            return
        }
        val valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
        valueAnimator.duration = 250
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            val v = 1.0f - animatedValue
            val translatY = (mNormalScrollHeight).toFloat() * v
            val selectTranslatY = (mSelectScrollHeight).toFloat() * v
            loge("ValueAnimator 动画 v $v mCount $mCurrentCount")

            for (i in 0 until getChildCount()) {
                val childView = getChildAt(i) as View
                if (direction == SCROLL_DIRECTION_UP) {
                    if (mCurrentCount == i) {

                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight = mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight)*v).toInt()
                        val childTopMargin = childView.tag as Int
                        loge("mMHeight 由小变大 mMHeight$mMHeight childView${childView.tag}")
                        lp.height = mMHeight

                        val newTop = (childTopMargin-selectTranslatY - translatY*(scrollItemCount-1)).toInt()
                        lp.setMargins(0,newTop,0,0)

                        childView.layoutParams = lp

                        childView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
                    } else if(mCurrentCount == i + 1){

                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight = mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight)*animatedValue).toInt()
                        loge("mMHeight 由大变小 mMHeight$mMHeight mIndicatorUnSelectHeight$mIndicatorUnSelectHeight mIndicatorSelectHeight$mIndicatorSelectHeight")
                        lp.height = mMHeight
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin-translatY- selectTranslatY*(scrollItemCount-1)).toInt()
                        lp.setMargins(0,newTop,0,0)

                        childView.layoutParams = lp
                        childView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
                    }else {
                        val lp = childView.layoutParams as LayoutParams
                        val childTopMargin = childView.tag as Int
                        lp.height = mIndicatorUnSelectHeight
                        val newTop = (childTopMargin-translatY - translatY*(scrollItemCount-1)).toInt()
                        lp.setMargins(0,newTop,0,0)
                        childView.layoutParams = lp

                        childView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot)

                    }

//                    if(mCurrentCount == i-2){
//                        childView.scaleX = v
//                        childView.scaleY = v
//                    }else if(mCurrentCount == i+3){
//                        childView.scaleX = animatedValue
//                        childView.scaleY = animatedValue
//                    }

                } else if (direction == SCROLL_DIRECTION_DOWN) {
                    if (mCurrentCount == i - 1) {
                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight = mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight)*animatedValue).toInt()
                        loge("mMHeight 由大变小 mMHeight$mMHeight mIndicatorUnSelectHeight$mIndicatorUnSelectHeight mIndicatorSelectHeight$mIndicatorSelectHeight")
                        lp.height = mMHeight
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin+selectTranslatY).toInt()
                        lp.setMargins(0,newTop,0,0)

                        childView.layoutParams = lp
                        childView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot)

                    } else if(mCurrentCount == i){
                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight = mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight)*v).toInt()
                        val childTopMargin = childView.tag as Int
                        loge("mMHeight 由小变大 mMHeight$mMHeight childView${childView.tag}")
                        lp.height = mMHeight
                        val newTop = (childTopMargin+translatY).toInt()

                        lp.setMargins(0,newTop,0,0)
                        childView.layoutParams = lp

                        childView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)

                    }else {
                        val lp = childView.layoutParams as LayoutParams
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin+translatY).toInt()
                        lp.setMargins(0,newTop,0,0)
                        childView.layoutParams = lp
                    }

//                    if(mCurrentCount == i-3){
//                        childView.scaleX = v
//                        childView.scaleY = v
//                    }else if(mCurrentCount == i+2){
//                        childView.scaleX = animatedValue
//                        childView.scaleY = animatedValue
//                    }
                }

            }
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                loge("动画进度 onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator) {
                loge("动画进度 onAnimationEnd")
                isAnimatorIng = false
                for (i in 0 until getChildCount()) {
                    val childView = getChildAt(i) as View
                    val childTopMargin = childView.tag as Int
                    var mNewTop = 0

                    if (direction == SCROLL_DIRECTION_UP) {
                        if (mCurrentCount == i) {
                            mNewTop = childTopMargin - mSelectScrollHeight
                        } else {
                            mNewTop = childTopMargin - mNormalScrollHeight
                        }
                        childView.tag = mNewTop
                    } else if (direction == SCROLL_DIRECTION_DOWN) {
                        if (mCurrentCount == i - 1) {
                            mNewTop = childTopMargin + mSelectScrollHeight
                        } else {
                            mNewTop = childTopMargin + mNormalScrollHeight
                        }
                        childView.tag = mNewTop
                    }
                }
            }
        })
        valueAnimator.start()
    }

    fun show() {
        if (mTotalCount < 2) {
            visibility = View.GONE
            return
        }
        removeAllViews()

        for (i in 0 until mTotalCount) {
            val layoutParams = getLayoutParams(i)
            val itemView = View(context)

            loge("childView 一开始时的高度 第 $i 个 layoutParams.topMargin ${layoutParams.topMargin}")
            itemView.tag = layoutParams.topMargin

            if (mCurrentCount == i) {
                itemView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
            } else {
                itemView.background = context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
            }
            this.addView(itemView, layoutParams)
        }
    }


    private fun getLayoutParams(i: Int): LayoutParams {
        if (mCurrentCount == i) {
            //选中的哪一个item
            val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorSelectHeight)
            val marginTop: Int = mHeight / 2 - mIndicatorSelectHeight / 2

            layoutParams.setMargins(0, marginTop, 0, 0)
            return layoutParams
        } else {
            //未选中
            val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorUnSelectHeight)
            val marginTop: Int =
                mHeight / 2 + mIndicatorSelectHeight / 2 + mIndicatorItemMargin * i + mIndicatorUnSelectHeight * (i - 1)
            layoutParams.setMargins(0, marginTop, 0, 0)
            return layoutParams
        }
    }

}
package com.example.kotlindemo.indicator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/22
 * @email:zhangshijie@soyoung.com
 * @Description: 提供给视频榜单页的 指示器
 */
class PostVideoHotIndicator4 : FrameLayout {

    private var mIndicatorSelectColor: Int = 0
    private var mIndicatorUnSelectColor: Int = 0
    private var mIndicatorSelectHeight: Int = 0
    private var mIndicatorUnSelectHeight: Int = 0

    /**
     * 指示器每一个小球之间的间距
     */
    private var mIndicatorItemMargin: Int = 0

    /**
     * 指示器的小球的宽度
     */
    private var mIndicatorItemWidth: Int = 0

    /**
     * 当前选中的位置 从0开始
     */
    private var mCurrentCount: Int = 0

    /**
     * 指示器的总数
     */
    private var mTotalCount: Int = 0

    /**
     *  正常小球需要滚动的高度
     */
    private var mNormalScrollHeight: Int = 0

    /**
     * 从选中到未选中 需要滚动的高度
     */
    private var mSelectScrollHeight: Int = 0

    /**
     * 指示器的总高度
     */
    private var mHeight: Int = 0

    /**
     *  是否正在进行动画
     */
    private var isAnimatorIng = false

    private var valueAnimator: ValueAnimator? = null

    /**
     *  滚动的方向
     */
    private var scrollDirection = SCROLL_DIRECTION_INIT

    /**
     *  选中的定位位置
     */
    private var startStatus = START_STATUS_TOP

    companion object {
        /**
         *  向上滚动
         */
        const val SCROLL_DIRECTION_UP = 1

        /**
         * 向下滚动
         */
        const val SCROLL_DIRECTION_DOWN = 2

        /**
         * 初始状态
         */
        const val SCROLL_DIRECTION_INIT = -1

        /**
         *  选中的定位位置 为顶部
         */
        const val START_STATUS_TOP = 1

        /**
         *  选中的定位位置 为底部
         */
        const val START_STATUS_BOTTOM = 2

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


        mHeight = dp2px(context, 96f)

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

    /**
     *  初始化指示器
     *  totalCount 进度总数
     *  currentCount 当前的位置 从0开始
     *
     */
    fun initTotalCount(totalCount: Int,currentCount: Int): PostVideoHotIndicator4 {
        this.mCurrentCount = currentCount
        this.mTotalCount = totalCount

        if(mCurrentCount+1 == mTotalCount){
            startStatus = START_STATUS_BOTTOM
        }else{
            startStatus = START_STATUS_TOP
        }
        return this
    }

    /**
     * 展示指示器
     */
    fun showIndicator() {
        removeAllViews()
        for (i in 0 until mTotalCount) {
            val layoutParams = getLayoutParams(i,mCurrentCount)
            val itemView = View(context)
            //此处用于记录高度top
            itemView.tag = layoutParams.topMargin
            if (mCurrentCount == i) {
                itemView.background =
                    context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
            } else {
                itemView.background =
                    context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
            }
            this.addView(itemView, layoutParams)
        }
    }

    /**
     *  开始滚动指示器
     *  targetCount 目标要滚动的位置
     */
    fun setScrollCount(targetCount: Int) {
        if (mTotalCount > 0 && targetCount > -1 && targetCount < mTotalCount) {

            if(targetCount == mCurrentCount){
                return
            }
            loge("动画执行相关 currentCount$targetCount isAnimatorIng$isAnimatorIng mCurrentCount$mCurrentCount")

            //如果动画还没有结束 就滚动下一个，则移除所有动画，直接将位置给定位到动画应该执行完时的那个位置，也就是上一个位置
            if (isAnimatorIng) {
                isAnimatorIng = false

                //清除所有动画
                valueAnimator?.removeAllUpdateListeners()
                valueAnimator?.removeAllListeners()
                valueAnimator?.cancel()
                valueAnimator = null

                resetIndicator(mCurrentCount)
                loge("动画执行相关 触发清除动画完成")
            }

            if (targetCount < mCurrentCount) {
                scrollDirection = SCROLL_DIRECTION_DOWN
            } else if (targetCount > mCurrentCount) {
                scrollDirection = SCROLL_DIRECTION_UP
            }

            //如果滚动的位置 超过了1个，按照先初始化到上一个地方，然后再做动画
            if(Math.abs(targetCount-mCurrentCount) >1){
                handleLocationPreDirection(targetCount)
            }

            this.mCurrentCount = targetCount

            valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
            isAnimatorIng = true
            loge("动画执行相关 开始执行动画")
            startAnimation()
        }
    }

    /**
     *  如果存在当滚动位置超过1个的时候，先定位到要滚动的上一个位置 如当前位置是1  要滚动的位置是4  那先定位到3，然后再动画滚动到4
     */
    private fun handleLocationPreDirection(tragetCount:Int){

        var nextCount = 0
        if(scrollDirection == SCROLL_DIRECTION_UP){
            nextCount = tragetCount - 1
        }else if(scrollDirection == SCROLL_DIRECTION_DOWN){
            nextCount = tragetCount + 1
        }
        loge("handleLocationPreDirection tragetCount $tragetCount nextCount$nextCount")

        for (i in 0 until getChildCount()) {
            val childView = getChildAt(i) as View

            val lp = getHandlePerLayoutParams(i,nextCount)

            childView.layoutParams = lp

            loge("childView 一开始时的高度 第 $i 个 layoutParams.topMargin ${lp.topMargin}")
            childView.tag = lp.topMargin

            childView.background =
                    context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
        }

    }

    /**
     * 如果动画还没有结束 就滚动下一个，则移除所有动画，直接将位置给定位到动画应该执行完时的那个位置，也就是上一个位置
     */
    private fun resetIndicator(preCountCount: Int) {
        loge("动画执行相关 触发清除动画 时的count preCountCount$preCountCount")
        for (i in 0 until getChildCount()) {
            val childView = getChildAt(i) as View
            childView.scaleX = 1.0f
            childView.scaleY = 1.0f
            if (scrollDirection == SCROLL_DIRECTION_UP) {

                if(preCountCount == i){
                    val lp = childView.layoutParams as LayoutParams
                    val mMHeight = mIndicatorSelectHeight
                    lp.height = mMHeight
                    val childTopMargin = childView.tag as Int
                    val newTop = (childTopMargin - mSelectScrollHeight)

                    lp.setMargins(0, newTop, 0, 0)

                    childView.layoutParams = lp
                    childView.tag = newTop

                    childView.background =
                        context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
                }else{
                    val lp = childView.layoutParams as LayoutParams
                    val mMHeight = mIndicatorUnSelectHeight
                    lp.height = mMHeight
                    val childTopMargin = childView.tag as Int
                    val newTop = (childTopMargin - mNormalScrollHeight)

                    lp.setMargins(0, newTop, 0, 0)

                    childView.layoutParams = lp
                    childView.tag = newTop

                    childView.background =
                        context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
                }

            } else if (scrollDirection == SCROLL_DIRECTION_DOWN) {

                if(preCountCount == i){
                    val lp = childView.layoutParams as LayoutParams
                    val mMHeight = mIndicatorSelectHeight
                    lp.height = mMHeight
                    val childTopMargin = childView.tag as Int
                    val newTop = (childTopMargin + mNormalScrollHeight)

                    lp.setMargins(0, newTop, 0, 0)

                    childView.layoutParams = lp
                    childView.tag = newTop

                    childView.background =
                        context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
                }else if(preCountCount == i-1){
                    val lp = childView.layoutParams as LayoutParams
                    val mMHeight = mIndicatorUnSelectHeight
                    lp.height = mMHeight
                    val childTopMargin = childView.tag as Int
                    val newTop = (childTopMargin + mSelectScrollHeight)

                    lp.setMargins(0, newTop, 0, 0)

                    childView.layoutParams = lp
                    childView.tag = newTop

                    childView.background =
                        context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
                }else{
                    val lp = childView.layoutParams as LayoutParams
                    val mMHeight = mIndicatorUnSelectHeight
                    lp.height = mMHeight
                    val childTopMargin = childView.tag as Int
                    val newTop = (childTopMargin + mNormalScrollHeight)

                    lp.setMargins(0, newTop, 0, 0)

                    childView.layoutParams = lp
                    childView.tag = newTop

                    childView.background =
                        context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
                }
            }

        }
    }

    /**
     *  开始执行动画
     */
    private fun startAnimation() {
        if (scrollDirection == SCROLL_DIRECTION_INIT) {
            return
        }
        valueAnimator?.duration = 250
        valueAnimator?.interpolator = LinearInterpolator()
        valueAnimator?.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            val v = 1.0f - animatedValue
            val translatY = mNormalScrollHeight.toFloat() * v
            val selectTranslatY = mSelectScrollHeight.toFloat() * v
            loge("ValueAnimator 动画 v $v mCount $mCurrentCount")

            for (i in 0 until getChildCount()) {
                val childView = getChildAt(i) as View
                if (scrollDirection == SCROLL_DIRECTION_UP) {
                    if (mCurrentCount == i) {

                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight =
                            mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight) * v).toInt()
                        val childTopMargin = childView.tag as Int
                        loge("mMHeight 由小变大 mMHeight$mMHeight childView${childView.tag}")
                        lp.height = mMHeight
                        val newTop = (childTopMargin - selectTranslatY).toInt()

                        lp.setMargins(0, newTop, 0, 0)
                        childView.layoutParams = lp

                        childView.background =
                            context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)
                    } else if (mCurrentCount == i + 1) {

                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight =
                            mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight) * animatedValue).toInt()
                        loge("mMHeight 由大变小 mMHeight$mMHeight mIndicatorUnSelectHeight$mIndicatorUnSelectHeight mIndicatorSelectHeight$mIndicatorSelectHeight")
                        lp.height = mMHeight
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin - translatY).toInt()
                        lp.setMargins(0, newTop, 0, 0)

                        childView.layoutParams = lp
                        childView.background =
                            context.resources.getDrawable(R.drawable.shape_item_post_video_hot)
                    } else {
                        val lp = childView.layoutParams as LayoutParams
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin - translatY).toInt()
                        lp.setMargins(0, newTop, 0, 0)
                        childView.layoutParams = lp
                    }

                    if(mCurrentCount == i-2){
                        childView.scaleX = v
                        childView.scaleY = v
                    }else if(mCurrentCount == i+3){
                        childView.scaleX = animatedValue
                        childView.scaleY = animatedValue
                    }

                } else if (scrollDirection == SCROLL_DIRECTION_DOWN) {
                    if (mCurrentCount == i - 1) {
                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight =
                            mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight) * animatedValue).toInt()
                        loge("mMHeight 由大变小 mMHeight$mMHeight mIndicatorUnSelectHeight$mIndicatorUnSelectHeight mIndicatorSelectHeight$mIndicatorSelectHeight")
                        lp.height = mMHeight
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin + selectTranslatY).toInt()
                        lp.setMargins(0, newTop, 0, 0)

                        childView.layoutParams = lp
                        childView.background =
                            context.resources.getDrawable(R.drawable.shape_item_post_video_hot)

                    } else if (mCurrentCount == i) {
                        val lp = childView.layoutParams as LayoutParams
                        val mMHeight =
                            mIndicatorUnSelectHeight + ((mIndicatorSelectHeight - mIndicatorUnSelectHeight) * v).toInt()
                        val childTopMargin = childView.tag as Int
                        loge("mMHeight 由小变大 mMHeight$mMHeight childView${childView.tag}")
                        lp.height = mMHeight
                        val newTop = (childTopMargin + translatY).toInt()

                        lp.setMargins(0, newTop, 0, 0)
                        childView.layoutParams = lp

                        childView.background =
                            context.resources.getDrawable(R.drawable.shape_item_post_video_hot_select)

                    } else {
                        val lp = childView.layoutParams as LayoutParams
                        val childTopMargin = childView.tag as Int
                        val newTop = (childTopMargin + translatY).toInt()
                        lp.setMargins(0, newTop, 0, 0)
                        childView.layoutParams = lp
                    }

                    if(mCurrentCount == i+2){
                        childView.scaleX = v
                        childView.scaleY = v
                    }else if(mCurrentCount == i-3){
                        childView.scaleX = animatedValue
                        childView.scaleY = animatedValue
                    }
                }

            }
        }
        valueAnimator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                loge("动画执行相关 onAnimationStart")
                isAnimatorIng = true
            }

            override fun onAnimationEnd(animation: Animator) {
                loge("动画执行相关 onAnimationEnd")
                isAnimatorIng = false
                for (i in 0 until getChildCount()) {
                    val childView = getChildAt(i) as View
                    val childTopMargin = childView.tag as Int
                    var mNewTop = 0

                    if (scrollDirection == SCROLL_DIRECTION_UP) {
                        if (mCurrentCount == i) {
                            mNewTop = childTopMargin - mSelectScrollHeight
                        } else {
                            mNewTop = childTopMargin - mNormalScrollHeight
                        }
                        childView.tag = mNewTop
                    } else if (scrollDirection == SCROLL_DIRECTION_DOWN) {
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
        valueAnimator?.start()
    }


    private fun getLayoutParams(i: Int,mCurrentCount:Int): LayoutParams {
        loge("getLayoutParams startStatus $startStatus mCurrentCount$mCurrentCount i$i")
        if(startStatus == START_STATUS_TOP){
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
        }else{
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
                    mHeight / 2 - mIndicatorSelectHeight / 2 - (mIndicatorItemMargin + mIndicatorUnSelectHeight)*(mCurrentCount-i)
                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            }
        }

    }

    private fun getHandlePerLayoutParams(i: Int,mCurrentCount:Int): LayoutParams {
        loge("getLayoutParams startStatus $startStatus mCurrentCount$mCurrentCount i$i")
        if(startStatus == START_STATUS_TOP){
            if (mCurrentCount == i) {
                //选中的哪一个item
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorSelectHeight)
                val marginTop: Int = mHeight / 2 - mIndicatorSelectHeight / 2

                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            } else if(mCurrentCount <i){
                //下面的
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorUnSelectHeight)
                val marginTop: Int =
                    mHeight / 2 + mIndicatorSelectHeight / 2 + mIndicatorItemMargin * (i-mCurrentCount) + mIndicatorUnSelectHeight * (i - mCurrentCount-1)
                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            }else{
                //上面的
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorUnSelectHeight)
                val marginTop: Int =
                    mHeight / 2 - mIndicatorSelectHeight / 2 - (mIndicatorItemMargin + mIndicatorUnSelectHeight) * (mCurrentCount - i)
                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            }
        }else{
            if (mCurrentCount == i) {
                //选中的哪一个item
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorSelectHeight)
                val marginTop: Int = mHeight / 2 - mIndicatorSelectHeight / 2

                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            } else if(mCurrentCount <i){
                //下面的
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorUnSelectHeight)
                val marginTop: Int =
                    mHeight / 2 + mIndicatorSelectHeight / 2 + mIndicatorItemMargin * (i-mCurrentCount) + mIndicatorUnSelectHeight * (i - mCurrentCount-1)
                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            }else{
                //上面的
                val layoutParams = LayoutParams(this.mIndicatorItemWidth, this.mIndicatorUnSelectHeight)
                val marginTop: Int =
                    mHeight / 2 - mIndicatorSelectHeight / 2 - (mIndicatorItemMargin + mIndicatorUnSelectHeight) * (mCurrentCount - i)
                layoutParams.setMargins(0, marginTop, 0, 0)
                return layoutParams
            }
        }

    }

}
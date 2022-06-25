package com.example.kotlindemo.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/23
 * @email:zhangshijie@soyoung.com
 * @Description: 视频榜单 提供的 滚动指示器 view 包含指示器 和 右侧文本
 */
class PostVideoHotIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var mTotalCount: Int = 0
    private var mCurrentCount: Int = 1

    private var indicatorView: PostVideoHotIndicator4? = null
    private var tvCount: TextView? = null

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.post_video_hot_indicator_view_layout, this, true)
        indicatorView = findViewById(R.id.indicator_video)
        tvCount = findViewById(R.id.tv_count)
    }

    /**
     *  初始化指示器
     *  totalCount：指示器总数
     *  currentCount： 当前锚定是在头部还是尾部
     */
    fun initTotalCount(totalCount: Int, currentCount: Int = 1) {

        if (currentCount < 1 || totalCount < 2 || currentCount > totalCount) {
            this.visibility = View.GONE
            return
        }

        this.visibility = View.VISIBLE
        this.mCurrentCount = currentCount
        this.mTotalCount = totalCount

        tvCount?.visibility = View.GONE

        indicatorView?.initTotalCount(totalCount, currentCount - 1)?.showIndicator()
    }

    /**
     *  开始滚动
     */
    fun setScrollCount(currentCount: Int) {
        loge("waicheng currentCount $currentCount mTotalCount$mTotalCount ${mTotalCount > 0 && currentCount > -1 && currentCount < mTotalCount}")
        if (mTotalCount > 0 && currentCount > 0 && currentCount <= mTotalCount) {
            this.mCurrentCount = currentCount
            indicatorView?.setScrollCount(currentCount - 1)
            showTextCount()
        }
    }

    private var disposableText: Disposable? = null

    /**
     * 展示进度文本 1/5
     */
    private fun showTextCount() {
        tvCount?.visibility = View.GONE

        if (mTotalCount < 6) {
            return
        }

        val sbCount = StringBuilder()
        sbCount.append(mCurrentCount).append("/").append(mTotalCount)
        tvCount?.visibility = View.VISIBLE
        tvCount?.text = sbCount.toString()

        dealHideTextCount()
    }

    /**
     *  处理隐藏进度文本
     */
    private fun dealHideTextCount() {
        disposableText?.let {
            if (!it.isDisposed) {
                it.dispose()
                disposableText = null
            }
        }

        loge("开始时间 ${System.currentTimeMillis()} ")
        disposableText =
            Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loge("开始时间  进度 it$it ${System.currentTimeMillis()} ")
                    if (it >= 2) {
                        tvCount?.visibility = View.GONE
                        disposableText?.let {
                            if (!it.isDisposed) {
                                it.dispose()
                            }
                        }

                    }
                }
    }

}
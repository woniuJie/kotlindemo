package com.example.kotlindemo.zhengze

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_zheng_ze.*
import kotlinx.android.synthetic.main.community_vote_progress_layout.view.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/9/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class CommunityVoteProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.community_vote_progress_layout, this, true)
    }

    fun startToPlay(width: Int, text: String) {
        progress_title.text = text
        progress_title.startPlay(width.toFloat())
        progress_bg.startPlay(width.toFloat())
    }
}
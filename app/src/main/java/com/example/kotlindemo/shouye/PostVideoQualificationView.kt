package com.example.kotlindemo.shouye

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import java.util.regex.Pattern


/**
 * @version
 * @author:zhangshijie
 * @Date:2021/12/14
 * @email:zhangshijie@soyoung.com
 * @Description: 给视频贴用的资质view
 */
class PostVideoQualificationView : AppCompatTextView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    fun initView() {

    }

    fun setData(bean: PostVideoQualificationBean?) {
        if (bean == null) {
            this.visibility = View.GONE
            return
        }
        this.visibility = View.VISIBLE

        text = "资质与规则"

        val color = bean.font_full_color
        if(!TextUtils.isEmpty(color) && isColor(color)){
            setTextColor(Color.parseColor(color))
        }else{
            setTextColor(Color.parseColor("#4DFFFFFF"))
        }

        val mSize = bean.font_size
        if(!TextUtils.isEmpty(mSize)){
            textSize = 8f
        }

        setOnClickListener {
            if(!TextUtils.isEmpty(bean.route_url)){
                Toast.makeText(context,bean.route_url,Toast.LENGTH_SHORT).show()
            }
        }


    }

    //正则是否是颜色格式
    fun isColor(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val telRegex = "^#([0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3})$"
        val pattern = Pattern.compile(telRegex)
        return pattern.matcher(str).matches()
    }

}
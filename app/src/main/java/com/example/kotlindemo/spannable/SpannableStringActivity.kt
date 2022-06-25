package com.example.kotlindemo.spannable

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ImageSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.util.Preconditions
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_spannable_string.*
import toast
import java.lang.reflect.Field
import java.lang.reflect.Method


class SpannableStringActivity : AppCompatActivity() {

    private var maxDisplayLength = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)

//        val str = "我叫张士杰"
//        val sp = SpannableString(str)
//        sp.setSpan(TypefaceSpan("monospace"),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        sp.setSpan(TypefaceSpan("serif"),2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        tv_spanne_one.text = sp


//        val lp = iv_glide.layoutParams
//        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
//        lp.height = 30
//        iv_glide.layoutParams = lp
//
//        val url = "https://www.baidu.com/img/bd_logo1.png"
//        Glide.with(this).load(url)
//            .into(iv_glide)
//
//
//        iv_glide.setOnClickListener {
//            toast("点击")
//            loge("点击")
//        }
//
//
//        hookOnClickListener(iv_glide)

//        ImageParamsCheckUtils.findAllImageParamsIsWrap(this)

//        val list = getAllChildViews(this.window.decorView)
//        val simpleName = this.javaClass.simpleName
//
//        if (list.isNotEmpty()) {
//            for (i in 0 until list.size) {
//                val view = list.get(i)
//                val name: CharSequence =
//                    resources.getResourceEntryName(list.get(i).getId())
//                val xml: CharSequence =
//                    resources.getResourceEntryName(R.layout.activity_spannable_string)
//
//                view.post {
//                    val widthWrap = getTargetWidth(view)
//                    val heightWrap = getTargetHeight(view)
//                    if (widthWrap == 3 || heightWrap == 3) {
//                        loge(
//                            "当前activit：$simpleName---当前布局文件为：$xml----imageview的ID：$name----宽度width是否设置了Wrap_content:${widthWrap == 3}---高度height是否设置了Wrap_content:${heightWrap == 3}----yiew${view}}"
//                        )
//                    }
//                }
//            }
//        }

        tv_spannable_tv?.text = genSpannalbeShow()


    }

    fun genSpannalbeShow():Spannable{
        val sb = SpannableStringBuilder("阿迪是否了解阿杜里斯封口机阿阿迪是否了解阿杜里斯封口机阿里就是短发剪刀手了阿迪是否了解阿杜里斯封口机阿里就是短发剪刀手了里就是短发剪刀手了")

        sb.setSpan(AbsoluteSizeSpan(dp2px(this,10f)),18,sb.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val drawable = resources.getDrawable(R.mipmap.userprofile_professional_icon)
        drawable.setBounds(0,0,drawable.intrinsicWidth,dp2px(this,10f))
        sb.setSpan(MyImageSpan(drawable),17,18,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
//        sb.setSpan(SuperSubSpan(),17,18,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return sb
    }


    fun getMaxDisplayLength(context: Context): Int {
        if (maxDisplayLength == 0) {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = Preconditions.checkNotNull(windowManager).defaultDisplay
            val displayDimensions = Point()
            display.getSize(displayDimensions)
            maxDisplayLength = Math.max(displayDimensions.x, displayDimensions.y)
        }
        return maxDisplayLength
    }

    fun getAllChildViews(view: View?): MutableList<View> {
        val list = mutableListOf<View>()
        if (view is ViewGroup) {
            val vg = view as? ViewGroup
            vg?.let {
                for (i in 0..vg.childCount) {
                    val viewChild = vg.getChildAt(i)
                    viewChild?.let {
                        if (viewChild is ImageView) {
                            list.add(viewChild)
                        }
                        list.addAll(getAllChildViews(viewChild))
                    }
                }
            }
        }
        return list
    }


    private fun getTargetWidth(view: View): Int {
        val horizontalPadding: Int = view.getPaddingLeft() + view.getPaddingRight()
        val layoutParams: ViewGroup.LayoutParams = view.getLayoutParams()
        val layoutParamSize = layoutParams?.width ?: 0

        return getTargetDimen(view, view.width, layoutParamSize, horizontalPadding)
    }

    private fun getTargetHeight(view: View): Int {
        val verticalPadding = view.paddingTop + view.paddingBottom
        val layoutParams = view.layoutParams
        val layoutParamSize = layoutParams?.height ?: 0
        return getTargetDimen(view, view.height, layoutParamSize, verticalPadding)
    }

    private fun getTargetDimen(view: View, viewSize: Int, paramSize: Int, paddingSize: Int): Int {
        val adjustedParamSize = paramSize - paddingSize
        if (adjustedParamSize > 0) {
            return 1
        }

        val adjustedViewSize = viewSize - paddingSize
        if (adjustedViewSize > 0) {
            return 2
        }

        if (!view.isLayoutRequested() && paramSize == ViewGroup.LayoutParams.WRAP_CONTENT) {
            return 3
        }
        return 0
    }


    private fun hookOnClickListener(view: View) {
        try {
            // 得到 View 的 ListenerInfo 对象
            val getListenerInfo: Method = View::class.java.getDeclaredMethod("getListenerInfo")
            getListenerInfo.setAccessible(true)
            val listenerInfo: Any = getListenerInfo.invoke(view)
            // 得到 原始的 OnClickListener 对象
            val listenerInfoClz = Class.forName("android.view.View\$ListenerInfo")
            val mOnClickListener: Field = listenerInfoClz.getDeclaredField("mOnClickListener")
            mOnClickListener.setAccessible(true)
            val originOnClickListener: View.OnClickListener =
                mOnClickListener.get(listenerInfo) as View.OnClickListener
            // 用自定义的 OnClickListener 替换原始的 OnClickListener
            val hookedOnClickListener: View.OnClickListener =
                HookedOnClickListener(originOnClickListener)
            mOnClickListener.set(listenerInfo, hookedOnClickListener)
        } catch (e: Exception) {
            loge("hook clickListener failed!${e.localizedMessage}")
        }
    }

    internal class HookedOnClickListener(private val origin: View.OnClickListener?) :
        View.OnClickListener {
        override fun onClick(v: View) {
            loge("Before click, do what you want to to.")
            origin?.onClick(v)
            loge("After click, do what you want to to.")
        }

    }
}
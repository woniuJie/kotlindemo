package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.TextUtils
import android.util.Log
import android.view.FrameMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.async.AsyncActivity
import com.example.kotlindemo.bringtofront.BringToFrontActivity
import com.example.kotlindemo.bringtofront.LineRightActivity
import com.example.kotlindemo.click.ClickActivity
import com.example.kotlindemo.countdowntime.CountDownTimeActivity
import com.example.kotlindemo.dialog.DialogActivity
import com.example.kotlindemo.dianzan.DianzanActivity
import com.example.kotlindemo.edittext.EditTextActivity
import com.example.kotlindemo.event.EventActivity
import com.example.kotlindemo.extend.GaoJieActivity
import com.example.kotlindemo.extend.funa
import com.example.kotlindemo.extend.funb
import com.example.kotlindemo.glide.GlideActivity
import com.example.kotlindemo.maoboli.MaoBoLiActivity
import com.example.kotlindemo.network.DemoActivity
import com.example.kotlindemo.oom.OOMActivity
import com.example.kotlindemo.pile.PileActivity
import com.example.kotlindemo.flexible.PullDemo1Activity
import com.example.kotlindemo.indicator.IndicatorDemoActivity
import com.example.kotlindemo.lifecycler.LifeCyclerDemoActitivy
import com.example.kotlindemo.rxjava.RxJavaDemoActitivy
import com.example.kotlindemo.shouye.WeiYiActivity
import com.example.kotlindemo.spannable.SpannableStringActivity
import com.example.kotlindemo.touchevent.TouchEventActivity
import com.example.kotlindemo.touxiang.TouXiangLunBoActivity
import com.example.kotlindemo.transitionname.DouYinAActivity
import com.example.kotlindemo.transitionname.MyTestAActivity
import com.example.kotlindemo.transitionname.TransitionAActivity
import com.example.kotlindemo.view.ViewDemoActivity
import com.example.kotlindemo.viewpager.ViewPagerActivity
import com.example.kotlindemo.xiecheng.XieCheng2Activity
import com.example.kotlindemo.xiecheng.XieChengActivity
import com.example.kotlindemo.zhengze.ZhengZeActivity
import com.example.myaarlibrary.AarActivity
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private var allFrames = 0
    private var jankyFrames = 0
    private var warn = 1000/60.toFloat()
    private var error = warn*2

    val xx: DataBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loge("我叫张士杰")

        Log.e("zsj", "onCreate: ${null == false}")
        Log.e(TAG, "onCreate: --[${xx?.str}]----")
        loge("----${TextUtils.equals(xx?.str, "null")}")
        loge("----${xx?.str == null}")

        loge("xxxyyy1")

        loge("dev_100")

        loge("release")

        loge("zsj-funa(::funb)--"+funa(::funb))

        val r_name3 =
            "https://img2.soyoung.com/tieba/ios/post/20200629/2/108d391524db8f42839644be34de95ac_540_718.jpg"
        val pattern: Pattern = Pattern.compile("_\\d+_\\d+")
        val matcher: Matcher = pattern.matcher(r_name3)
        Log.e("zsj", "onCreate: ${matcher.replaceAll("").trim()}")


        val pattern1: Pattern = Pattern.compile("_\\d+")
        val matcher1: Matcher = pattern1.matcher(r_name3)

        while (matcher1.find()) {
            Log.e("zsj", "onCreate: ${matcher1.group()}")
        }

        loge("getUrlWAndHScale ${getUrlWAndHScale(r_name3)}")


        val lsit = listOf<String>()
        loge("lsit.isNullOrEmpty()--${lsit.isNullOrEmpty()}")


        val xxx = ""
        xxx?.takeIf { it.isNotEmpty() }?.let {
            loge("11111")
        }?:  loge("22222")

        val xx = "0.0"
        if(xx.toFloat()==0f){
            loge("1234")
        }else{
            loge("12345")
        }

        aaa()

        val nstr = "\n\n十三水"
        loge("nstr${nstr.replace("\n","")}")

        val mylist = arrayListOf<Int>(1,2,3,4,5,6)
        val list111 = mylist.filter {
            it<5
        }
        list111.forEach {
            loge("mylist $it")
        }

    }

    fun aaa(seri:String = "1"){
        loge("seri$seri")
    }

    private fun getUrlWAndHScale(url: String): String {
        var scale = 0.0
        var scaleStr = ""
        var result = 0.0
        try {
            val pattern = Pattern.compile("_\\d+")
            val matcher = pattern.matcher(url)
            val list: MutableList<String> =
                ArrayList()
            while (matcher.find()) {
                list.add(matcher.group())
            }
            var w = 0
            var h = 0
            if (list.size > 1) {
                w = list[0].replace("_".toRegex(), "").toInt()
                h = list[1].replace("_".toRegex(), "").toInt()
            }
            if (w != 0 && h != 0) {
                loge("w-${w} --- h-${h}")
                scale = w.toDouble() / h
            }

            scaleStr = DecimalFormat("#.00").format(scale)
//            scaleStr = String.format("%.2f", scale)


        } catch (e: Exception) {
        }
        return scaleStr
    }

    fun onCorClick(view: View) {
        Intent(this, DemoActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onDianZanClick(view: View) {
        Intent(this, DianzanActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onXieCheng(view: View) {
        Intent(this, XieCheng2Activity::class.java).run {
            startActivity(this)
        }
    }

    fun onMaoBoLi(view: View) {
        Intent(this, MaoBoLiActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onEventClick(view: View) {
        Intent(this, EventActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onViewPager(view: View) {
        Intent(this, ViewPagerActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onAsync(view: View) {
        Intent(this, AsyncActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onOOMEvent(view: View) {
        Intent(this, OOMActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onBitmap(view: View) {
        Intent(this, GlideActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onCustTimer(view: View) {
        Intent(this, CountDownTimeActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onPipleView(view: View) {
        Intent(this, PileActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onPaizhaoView(view: View) {
        Intent(this, ClickActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onEditTextClick(view: View) {
        Intent(this, EditTextActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onAarTrunClick(view: View) {
        Intent(this, AarActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onGaoJie(view: View) {
        Intent(this, GaoJieActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onZhengZe(view: View) {
        Intent(this, ZhengZeActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onPullDemo(view: View) {
        Intent(this, PullDemo1Activity::class.java).run {
            startActivity(this)
        }
    }


    fun onSpannable(view: View) {
        Intent(this, SpannableStringActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onTouxiang(view: View) {
        Intent(this, TouXiangLunBoActivity::class.java).run {
            startActivity(this)
        }
    }
    fun onPullDialog(view: View) {
        Intent(this, DialogActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onWeiYi(view: View) {
        Intent(this, WeiYiActivity::class.java).run {
            startActivity(this)
        }
    }
    fun onTransfo(view: View) {
        Intent(this, TransitionAActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onDouYinA(view: View) {
        Intent(this, DouYinAActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onMotionaa(view: View) {
        Intent(this, MyTestAActivity::class.java).run {
            startActivity(this)
        }
    }
    fun onBringToFront(view: View) {
        Intent(this, XieChengActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onViewMeasureDemo(view: View) {
        Intent(this, ViewDemoActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onTouchEventDemo(view: View) {
        Intent(this, TouchEventActivity::class.java).run {
            startActivity(this)
        }
    }

    fun onLifecycleDemo(view: View) {
        Intent(this, LifeCyclerDemoActitivy::class.java).run {
            startActivity(this)
        }
    }
    fun onRxJavaDemo(view: View) {
        Intent(this, RxJavaDemoActitivy::class.java).run {
            startActivity(this)
        }
    }

    fun onIndicatorDemo(view: View) {
        Intent(this, IndicatorDemoActivity::class.java).run {
            startActivity(this)
        }
    }


    override fun finish() {
        super.finish()
        loge("zsj finish")
    }
}
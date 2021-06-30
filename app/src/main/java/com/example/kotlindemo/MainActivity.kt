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
import com.example.kotlindemo.click.ClickActivity
import com.example.kotlindemo.countdowntime.CountDownTimeActivity
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
import com.example.kotlindemo.spannable.SpannableStringActivity
import com.example.kotlindemo.viewpager.ViewPagerActivity
import com.example.kotlindemo.xiecheng.XieCheng2Activity
import com.example.kotlindemo.zhengze.ZhengZeActivity
import com.example.myaarlibrary.AarActivity
import java.math.BigDecimal
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


        if(BuildConfig.DEBUG){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val thread = HandlerThread("frame-stat").apply { start() }
                val handler = Handler(thread.looper)
                window.addOnFrameMetricsAvailableListener({ _, metric, _ ->
                    // 会在 handler 对应的 thread 中执行
                    val copy = FrameMetrics(metric) /* 注意需要做深拷贝, 再使用 */
                    allFrames++
                    val total = 0.000001f * copy.getMetric(FrameMetrics.TOTAL_DURATION)
//                    if (total > warn) {
                        jankyFrames++
                        var msg = String.format("Jank frame with total duration: %.2fms\n", total)
                        val layoutMeasureDurationMs = (0.000001 * copy.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION)).toFloat()
                        val drawDurationMs = (0.000001 * copy.getMetric(FrameMetrics.DRAW_DURATION)).toFloat()
                        val gpuCommandMs = (0.000001 * copy.getMetric(FrameMetrics.COMMAND_ISSUE_DURATION)).toFloat()
                        val othersMs: Float = total - layoutMeasureDurationMs - drawDurationMs - gpuCommandMs
                        val jankyPercent = jankyFrames.toFloat() / allFrames * 100
                        msg += String.format("Layout/measure: %.2fms, draw:%.2fms, gpuCommand:%.2fms others:%.2fms\n",
                            layoutMeasureDurationMs, drawDurationMs, gpuCommandMs, othersMs)
                        msg += "Janky frames: $jankyFrames/$allFrames($jankyPercent%)"
                        if(total > error){
                            Log.e("FrameStat",msg)
                        }else{
                            Log.d("FrameStat",msg)
                        }
//                    }
//                    val vsycn = copy.getMetric(FrameMetrics.VSYNC_TIMESTAMP)
//                    val intended = copy.getMetric(FrameMetrics.INTENDED_VSYNC_TIMESTAMP)
//                    Log.d("FrameStat", "is first frame: ${copy.getMetric(FrameMetrics.FIRST_DRAW_FRAME) == 1L} ")
//
//                    Log.d("FrameStat", "measure layout: ${copy.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "COMMAND_ISSUE_DURATION: ${copy.getMetric(FrameMetrics.COMMAND_ISSUE_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "处理输入事件回调的耗时: ${copy.getMetric(FrameMetrics.INPUT_HANDLING_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "draw: ${copy.getMetric(FrameMetrics.DRAW_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "total: ${copy.getMetric(FrameMetrics.TOTAL_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "ANIMATION_DURATION: ${copy.getMetric(FrameMetrics.ANIMATION_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "SWAP_BUFFERS_DURATION: ${copy.getMetric(FrameMetrics.SWAP_BUFFERS_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "SYNC_DURATION: ${copy.getMetric(FrameMetrics.SYNC_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "UNKNOWN_DELAY_DURATION: ${copy.getMetric(FrameMetrics.UNKNOWN_DELAY_DURATION) / 1000000} ms")
//                    Log.d("FrameStat", "delay draw: ${copy.getMetric(FrameMetrics.INTENDED_VSYNC_TIMESTAMP) > copy.getMetric(FrameMetrics.VSYNC_TIMESTAMP)} ")
//                    Log.d("FrameStat", "=============")
                }, handler)
            }
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
    fun onSpannable(view: View) {
        Intent(this, SpannableStringActivity::class.java).run {
            startActivity(this)
        }
    }

    override fun finish() {
        super.finish()
        loge("zsj finish")
    }
}
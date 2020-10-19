package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.async.AsyncActivity
import com.example.kotlindemo.countdowntime.CountDownTimeActivity
import com.example.kotlindemo.dianzan.DianzanActivity
import com.example.kotlindemo.event.EventActivity
import com.example.kotlindemo.glide.GlideActivity
import com.example.kotlindemo.maoboli.MaoBoLiActivity
import com.example.kotlindemo.network.DemoActivity
import com.example.kotlindemo.oom.OOMActivity
import com.example.kotlindemo.viewpager.ViewPagerActivity
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    val xx: DataBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("zsj", "onCreate: ${null == false}")
        Log.e(TAG, "onCreate: --[${xx?.str}]----")
        loge("----${TextUtils.equals(xx?.str, "null")}")
        loge("----${xx?.str==null}")

        val r_name3 = "https://img2.soyoung.com/tieba/ios/post/20200629/2/108d391524db8f42839644be34de95ac_540_718.jpg"
        val pattern: Pattern = Pattern.compile("_\\d+_\\d+")
        val matcher: Matcher = pattern.matcher(r_name3)
        Log.e("zsj", "onCreate: ${matcher.replaceAll("").trim()}" )


        val pattern1: Pattern = Pattern.compile("_\\d+")
        val matcher1: Matcher = pattern1.matcher(r_name3)

        while (matcher1.find()){
            Log.e("zsj", "onCreate: ${matcher1.group()}")
        }

        loge("getUrlWAndHScale ${getUrlWAndHScale(r_name3)}")


        val lsit = listOf<String>()
        loge("lsit.isNullOrEmpty()--${lsit.isNullOrEmpty()}")
    }

    private fun getUrlWAndHScale(url: String): String {
        var scale = 0.0
        var scaleStr = ""
        var result=0.0
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
        Intent(this, SecondActivity::class.java).run {
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
}
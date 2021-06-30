package com.example.kotlindemo.zhengze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import java.util.regex.Matcher
import java.util.regex.Pattern

class ZhengZeActivity : AppCompatActivity() {

    private val XHS_RANGE =
        Pattern.compile("\\[[^\\]]+\\]", Pattern.CASE_INSENSITIVE)

    private fun getMatcher(matchStr: CharSequence): Matcher {
        return XHS_RANGE.matcher(matchStr)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zheng_ze)

        var str = "第二次皮秒[:163]阿斯顿发生"
//        var regex = "\b[Cc][Aa][Rr]\b"
//        loge("正则表达式--" + regexChange(str, regex))


        val m: Matcher = getMatcher(str)
        while (m.find()) {
            val key = m.group()
            loge("zhengze--key--${key}")
        }

    }

    fun regexChange(str: String?, regex: String): String? {
        var s = ""
        if (str != null) {
            val p = Pattern.compile(regex)
            val m = p.matcher(str)
            m.find()
        }
        return s
    }
}
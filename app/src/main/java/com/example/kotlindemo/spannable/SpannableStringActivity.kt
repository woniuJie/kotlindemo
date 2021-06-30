package com.example.kotlindemo.spannable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TypefaceSpan
import androidx.core.text.set
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_spannable_string.*

class SpannableStringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)

        val str = "我叫张士杰"
        val sp = SpannableString(str)
        sp.setSpan(TypefaceSpan("monospace"),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        sp.setSpan(TypefaceSpan("serif"),2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_spanne_one.text = sp
    }
}
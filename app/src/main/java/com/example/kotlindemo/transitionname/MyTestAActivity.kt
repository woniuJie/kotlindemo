package com.example.kotlindemo.transitionname

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_douyin_aactivity.*
import kotlinx.android.synthetic.main.activity_my_test_a_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/2/18
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyTestAActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_test_a_layout)

        val url = "https://img2.soyoung.com/tieba/ios/post/20220104/7/ba323c1d59d0e81283_570.jpg"
        Glide.with(this).load(url)
            .into(iv_zsj111)

        ll_aazsj?.setOnClickListener {
            startActivity(Intent(this, MyTestBActivity::class.java))
        }
    }
}
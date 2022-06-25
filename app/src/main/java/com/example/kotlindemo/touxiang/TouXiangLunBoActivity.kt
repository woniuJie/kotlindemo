package com.example.kotlindemo.touxiang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_tou_xiang_lun_bo.*

class TouXiangLunBoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tou_xiang_lun_bo)

//        val list = mutableListOf<UserAvatarEntity>()
//        list.add(UserAvatarEntity("https://img2.soyoung.com/message/ios/20181019/4/b340254c4d8741b586f62fa2798aa4d4.jpg"))
//        list.add(UserAvatarEntity("https://img2.soyoung.com/message/ios/20200119/5/92966fae112b7482374a8771ad33e1f1.jpg"))
//        list.add(UserAvatarEntity("https://img2.soyoung.com/user/20191127/6/9ec4784edba4e94130510709b613d660.jpg"))
//        list.add(UserAvatarEntity("https://img2.soyoung.com/user/20191219/4/31cf0a5430bbe24e7b0ede83cfe1cc51.jpg"))
//        list.add(UserAvatarEntity("https://img1.soyoung.com/user/2013082018/20130820183343627.jpg"))
//        ticketview?.setBrowseEntities(list)
//
//        ticketview1?.setBrowseEntities(list)
    }

    override fun onDestroy() {
        super.onDestroy()
//        ticketview?.onDestroy()
//        ticketview1?.onDestroy()
    }
}
package com.example.kotlindemo.dialog

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_dialog_layout.*
import org.json.JSONObject

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/11/22
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mao_bo_li)

        bt_dialog.setOnClickListener {
            val view =
                LayoutInflater.from(this).inflate(R.layout.view_dialog_zsj_layout, null, false)

        }

        val list = mutableListOf<String>()
        val map = HashMap<String,List<String>>()
        list.add("a")
        list.add("b")
        map.put("title",list)

    }
}
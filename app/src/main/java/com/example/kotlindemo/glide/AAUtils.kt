package com.example.kotlindemo.glide

import android.os.Bundle
import com.example.kotlindemo.viewpager.BlankFragment

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/9/9
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class AAUtils {




    companion object {
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                }
            }
    }
}
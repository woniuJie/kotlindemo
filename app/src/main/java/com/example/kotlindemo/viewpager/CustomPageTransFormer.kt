package com.example.kotlindemo.viewpager

import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * @version
 * @author:zhangshijie
 * @Date:2020/7/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class CustomPageTransFormer : ViewPager.PageTransformer {
    companion object {
        const val MIN_SCALE = 0.9f
    }

    override fun transformPage(page: View, position: Float) {
        val scale = Math.max(MIN_SCALE, 1 - Math.abs(position))
        if(position < -1.0f){
            page.scaleY = MIN_SCALE
        }else if(position <=0.0f){
            page.scaleY = scale
        }else if (position<=1.0f){
            page.scaleY = scale
        }else{
            page.scaleY = MIN_SCALE
        }
    }

}

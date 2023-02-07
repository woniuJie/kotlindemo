package com.example.kotlindemo.clipchlidren

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.kotlindemo.R
import com.example.kotlindemo.dp2px
import kotlinx.android.synthetic.main.item_sub_bottom_bar_layout.view.*

class SySubBottomBar  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_sub_bottom_bar_layout,this)

    }

    fun setdata(color:String,i:Int){
        setBackgroundColor(Color.parseColor(color))
    }
}
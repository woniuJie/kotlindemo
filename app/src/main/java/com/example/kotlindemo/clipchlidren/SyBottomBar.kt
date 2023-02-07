package com.example.kotlindemo.clipchlidren

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.kotlindemo.dp2px

class SyBottomBar  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var x = mutableListOf<String>("#ff00ff","#00ff00","#ffff00","#0000ff")


    fun setData() {
        removeAllViews()
        for (i in 0..3){

            if(i==1){
//                val itemView = LinearLayout(context)
                val itemView = SySubBottomBar2(context)

                setItemLayoutParam(itemView,LayoutParams.MATCH_PARENT, dp2px(context,70f))
                itemView.setBackgroundColor(Color.RED)
                addView(itemView)
            }else{
                val itemview = SySubBottomBar(context)
                itemview.setdata(x.get(i),i)
                setItemLayoutParam(itemview,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
                addView(itemview)
            }

        }
    }

    private fun setItemLayoutParam(itemView: View,width:Int,height:Int) {
        val param = LayoutParams(
            width,
            height
        )
        param.weight = 1f
        param.gravity = Gravity.CENTER
        itemView.layoutParams = param
    }
}
package com.example.kotlindemo.bringtofront

import android.graphics.Rect
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.android.synthetic.main.activity_bring_to_front_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/3/2
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class BringToFrontActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bring_to_front_layout)



//        val constraintSet = ConstraintSet().apply {
//            clone(front_layout)
//            //button_1原本位于父布局左上角，要分别清理两处现有的约束
//            clear(R.id.view_3, ConstraintSet.TOP)
//
//            //重新建立约束
//            //让button_1低边至于button_2顶边，并有4dp的间隔
//            connect(R.id.view_3, ConstraintSet.TOP, R.id.view_2, ConstraintSet.BOTTOM)
//            //让button_1右边与button_2右边对齐，无需间隔
////            connect(R.id.button_1, ConstraintSet.START, R.id.button_2, ConstraintSet.START)
////            让button_1左边与button_2左边对齐，无需间隔
////            connect(R.id.button_1, ConstraintSet.END, R.id.button_2, ConstraintSet.END)
//        }
//
////        view_1?.setOnClickListener {
//            TransitionManager.beginDelayedTransition(front_layout)
//            //应用修改
//            constraintSet.applyTo(front_layout)
////        }
//
//
////        front_layout.visibility = View.VISIBLE
////        view_4.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
////            override fun onGlobalLayout() {
////                Log.e("zsj", "onGlobalLayout: ", )
////                view_4.viewTreeObserver.removeOnGlobalLayoutListener(this)
////            }
////        })
//        view_4.scrollTo(0,-100)

        val tvX = findViewById<TextView>(R.id.tv_title_xxxx)
        tvX.text = "我叫张士杰"
        val tvLayout = tvX.layout
//        val bound = Rect()
//        loge("tv_title_xxxx $tv_title_xxxx text ${tv_title_xxxx?.text?.toString()}")
//        val line = tvLayout?.getLineForOffset(tv_title_xxxx?.text?.length?:1-1)?:0
//        tvLayout?.getLineBounds(line,bound)
//        val right = bound.right
//       loge("right $right")

        val xx = tvLayout?.getLineRight(0)
        val xx1 = tvLayout?.getLineRight(1)
        loge("xx $xx $xx1 tvLayout$tvLayout")



    }
}
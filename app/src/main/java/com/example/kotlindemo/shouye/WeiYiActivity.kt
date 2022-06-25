package com.example.kotlindemo.shouye

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_weiyi_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/12/18
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class WeiYiActivity : AppCompatActivity() {

    private var istag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weiyi_layout)

        view_jianbian?.background = getTopMengCengGradientDrawable("")

        val bean = PostVideoQualificationBean()
        bean.route_url = "路由"
        bean.font_full_color = "#4DFFFFFF"
        bean.font_size = "8"
        view_video.setData(bean)



//        val drawable = GradientDrawable()
//        val colors =
//            intArrayOf(Color.parseColor("#00000000"), Color.parseColor("#0000ff" ?: "#00AB84"))
//        drawable.colors = colors
//        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
//        view_jianbian.background = drawable



        val left = tv_go.left
        var top = tv_go.top
        Log.e("zsj", "onCreate: left $left top$top" )


        val animator= ValueAnimator.ofObject(
            CategotyPointEvaluator(),
            CategotyPoint(0f,0f),//动画开始属性值
            CategotyPoint(
                (200f).toFloat(),
                (200f).toFloat()
            )//动画结束值
        )

        animator.addUpdateListener {//手动更新TextView的x和y 属性
            val point = it.animatedValue as CategotyPoint
            tv_go.x = point.x
            tv_go.y = point.y
            Log.e("zsj", "onCreate:point.x ${point.x} --${point.y}" )
        }
        animator.duration = 5000

        bt_do.setOnClickListener {
            layer.alpha = 0.3f
            if(!istag){
                animator.start()
            }else{
                animator.reverse()
//                animator.resume()
//                animator.cancel()
//                animator.removeAllListeners()
            }
            istag = !istag
        }





        var istag1 = false
        bt_do1.setOnClickListener {
            if(!istag1){
                val shakeAnimator = ObjectAnimator.ofFloat(tv_go1, "rotation", 0f, 20f, 0f, -20f, 0f)
                shakeAnimator?.interpolator = LinearInterpolator()
                shakeAnimator?.duration = 300
                tv_go1.tag = shakeAnimator
                (shakeAnimator as? ObjectAnimator)?.repeatCount = ValueAnimator.INFINITE
                shakeAnimator?.start()
            }else{
                (tv_go1.tag as? ObjectAnimator)?.reverse()
                (tv_go1.tag as? ObjectAnimator)?.resume()
                tv_go1.tag = null
            }
            istag1 = !istag1
        }



//
//        val mGestureDetector = GestureDetector(gestureListener())
//
//        bt_do.setOnTouchListener(object : View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                return mGestureDetector.onTouchEvent(event)
//            }
//        })

//        bt_do.setOnLongClickListener(object :View.OnLongClickListener{
//            override fun onLongClick(v: View?): Boolean {
//                Log.e("zsj", "onLongClick: " )
//                return false
//            }
//        })
//        bt_do.setOnTouchListener(object :View.OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                Log.e("zsj", "onTouch: ${event?.action}" )
//                return false
//            }
//
//        })



    }

    fun getTopMengCengGradientDrawable(moduleColor: String?): Drawable? {
        val drawable = GradientDrawable()
        val colors =
            intArrayOf(Color.parseColor("#00000000"), Color.parseColor("#0000ff" ?: "#00AB84"))
        drawable.colors = colors
        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
        return drawable
    }


    class ButtonListener : View.OnClickListener,View.OnTouchListener{
        override fun onClick(v: View?) {
            Log.e("zsj", "onClick" )

        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            Log.e("zsj", "onTouch: event${event?.action}" )
            return false
        }

    }


    private class gestureListener : GestureDetector.OnGestureListener{
        override fun onDown(e: MotionEvent?): Boolean {
            Log.e("zsj", "onDown: " )
            return true
        }

        override fun onShowPress(e: MotionEvent?) {
            Log.e("zsj", "onShowPress: " )

        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.e("zsj", "onSingleTapUp: " )

            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.e("zsj", "onScroll: " )

            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.e("zsj", "onLongPress: " )

        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.e("zsj", "onFling: " )

            return true
        }

    }
}
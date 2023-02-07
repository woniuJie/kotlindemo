package com.example.kotlindemo.donghua

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.graphics.RectF
import android.media.AudioTrack
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import com.gyf.immersionbar.ImmersionBar

class DonghuaActivity : AppCompatActivity(){
    protected var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donghua_layout)

        AudioTrack

        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.statusBarDarkFont(true, 0.2f)
            ?.navigationBarWithKitkatEnable(false)
            ?.init()

        val img = findViewById<ImageView>(R.id.iv_dognhua)

        img.viewTreeObserver.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                img.viewTreeObserver.removeOnGlobalLayoutListener(this)
                dealImg(img)
            }
        })
    }

    private fun dealImg(img:ImageView){
        val viewGroup = findViewById<ViewGroup>(android.R.id.content) as ViewGroup?
        val frameLayout = FrameLayout(this)
        viewGroup?.addView(frameLayout)
        val params = frameLayout.layoutParams
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        frameLayout.layoutParams = params

        //引导图片的创建
        val guideVideoView = PostGuideMaskView(this)
        val rect = Rect()
        img?.getGlobalVisibleRect(rect)

        loge("rect $rect img$img")

        val rectF = RectF()
        rectF.left = rect.left.toFloat()
        rectF.top = rect.top.toFloat()
        rectF.bottom = rect.bottom.toFloat()
        rectF.right = rect.right.toFloat()
        guideVideoView.setRectF(rectF)
        guideVideoView.radius =  50
        frameLayout.addView(guideVideoView)


        val animatorSet = AnimatorSet()
        animatorSet
            .play(scaleAni(img, "scaleX", 0.7f, 1.0f, 600, 1000))
            .with(scaleAni(img, "scaleY", 0.7f, 1.0f,600, 1000))
        animatorSet.start()


    }

    fun scaleAni(
        view: View?,
        propertyName: String?,
        from: Float,
        to: Float,
        time: Long,
        delayTime: Long
    ): ObjectAnimator? {
        val ani = ObjectAnimator.ofFloat(view, propertyName, from, to)
        ani.interpolator = DecelerateInterpolator()
        ani.startDelay = delayTime
        ani.duration = time

        return ani
    }
}
package com.example.kotlindemo.pile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.activity_pile.*

class PileActivity : AppCompatActivity() {
    var urls = arrayOf(
        "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
        "http://img2.imgtn.bdimg.com/it/u=1939271907,257307689&fm=21&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg",
        "http://img0.imgtn.bdimg.com/it/u=2263418180,3668836868&fm=206&gp=0.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pile)



        val inflate = LayoutInflater.from(this)
        urls.forEach {

            val circleView:RoundedImageView = inflate.inflate(R.layout.circle_layout,pilelayout,false) as RoundedImageView


//            val roundedImageView = RoundedImageView(this)
//            roundedImageView.cornerRadius = 45f
//            roundedImageView.borderWidth = 1f
//            roundedImageView.borderColor = Color.WHITE
//
//            val layoutParams = FrameLayout.LayoutParams(20,20)

            Glide.with(this)
                .load("https://img2.soyoung.com/message/ios/20200730/8/3223b7e6856e741e23874beeb660ce0d.jpg")
                .into(circleView)

            pilelayout.addView(circleView)

        }

        Glide.with(this)
                .load("https://img2.soyoung.com/message/ios/20200730/8/3223b7e6856e741e23874beeb660ce0d.jpg")

                .into(roundImage)

        val drawable = resources.getDrawable(R.drawable.ic_txue,null)
        iv_txue.setImageDrawable(drawable)


        val imgHtml = ""
        val url: String =
            imgHtml.substring(imgHtml.indexOf("src=") + 5, imgHtml.indexOf("\" style=\"width"))
        loge(url)

    }

    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}

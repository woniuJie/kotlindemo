package com.example.kotlindemo.extend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)


        val myView = MyView1(this)
        myView.setaListener(object : MyView1.AListener{
            override fun onPlay() {

            }
        })

        myView.setaListener {

        }

        val myView1 = MyView(this)
        myView1.setAALisetner{

        }


    }

    val a =  fun(params:Int):String{
        return params.toString()
    }
}
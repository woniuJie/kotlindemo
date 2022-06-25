package com.example.kotlindemo.lifecycler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import com.example.kotlindemo.touchevent.TouchEventActivity

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/15
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class LifeCyclerDemoActitivy : Activity() ,LifecycleOwner{

    val lifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReportFragment.injectIfNeededIn(this)
        setContentView(R.layout.activity_lifecycler_layout)

    }

    fun OnJump(view: View) {
        Intent(this, TouchEventActivity::class.java).run {
            startActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val observer = MyObserver()
        lifecycle.addObserver(observer)
    }

    override fun onStop() {
        super.onStop()
    }

    class MyObserver : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            loge("DefaultLifecycleObserver---onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            loge("DefaultLifecycleObserver---onStart")

        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            loge("DefaultLifecycleObserver---onResume")

        }

        override fun onPause(owner: LifecycleOwner) {
            super.onPause(owner)
            loge("DefaultLifecycleObserver---onPause")

        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            loge("DefaultLifecycleObserver---onPause")

        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
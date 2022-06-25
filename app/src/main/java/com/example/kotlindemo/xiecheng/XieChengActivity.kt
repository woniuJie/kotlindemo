package com.example.kotlindemo.xiecheng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.example.kotlindemo.R
import com.example.kotlindemo.coroutineScopeMain
import com.example.kotlindemo.loge
import com.example.kotlindemo.toast
import kotlinx.android.synthetic.main.activity_xie_cheng.*
import kotlinx.coroutines.*

class XieChengActivity : AppCompatActivity() {

    companion object {
        const val TAG = "zsj"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loge("onCreate 111--${System.currentTimeMillis()}")

        super.onCreate(savedInstanceState)
        loge("onCreate 222--${System.currentTimeMillis()}")

        setContentView(R.layout.activity_xie_cheng)
       loge("onCreate 333--${System.currentTimeMillis()}")

        loge("oncrate 444--lp?.width:${iv_xiecheng?.height}")

        tv_xiecheng.setText("xxxxxxxx")

        val layoutpara = tv_xiecheng.layoutParams as RelativeLayout.LayoutParams
        layoutpara.width = 10
        tv_xiecheng.layoutParams = layoutpara

        Log.e(TAG, "onCreate: 111")
        coroutineScopeMain.launch {
            aa()
            Log.e(TAG, "onCreate: 333")
        }
        Log.e(TAG, "onCreate: 444")

        GlobalScope.launch {
            loge("当前线程1"+Thread.currentThread().name+"---"+(Looper.myLooper() == Looper.getMainLooper()))
            CoroutineScope(Dispatchers.Main).launch {
                loge("当前线程2"+Thread.currentThread().name+"---"+(Looper.myLooper() == Looper.getMainLooper()))

            }
            loge("当前线程3"+Thread.currentThread().name+"---"+(Looper.myLooper() == Looper.getMainLooper()))
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.e(TAG, "onCreate: ---GlobalScope.Dispatchers---${Thread.currentThread().name}")

        }

        coroutineScopeMain.launch {
            val bitmap = ViewModel.getImage()
            iv_xiecheng.setImageBitmap(bitmap)

            bitmap?.let {
                val bitmap1 = ViewModel.decodeBitmap(it)
                iv_chenge.setImageBitmap(bitmap1)
            }
        }


        var mList = mutableListOf(1, 2, 3, 4, 5)
        var x = mList.map { it + 1 }.filter { it > 7 }

        println(x.size)

        val xxx = with(x) {
            if (x.size > 0) {
                "第一位${first()}" + "第二位${last()}"
            }
        }
        println(xxx)

        val let = mList.let {
            it.add(1)
            111
        }
        println(let)

        val also = mList.also {
            it.add(6)
            222
        }
        println(also)

        Log.e(TAG, "onCreate: qqq")

        /**超时取消*/
        GlobalScope.launch {
            val user = async {
                aa()
            }

            val timeJob = launch {
                delay(5000)
                user.cancel()
            }

            val u = user.await()
            timeJob.cancel()
            print(u)

        }

        coroutineScopeMain.launch {
            val a = async { add(1,2) }
            val b = async { add(2,3) }
            val c = a.await()+b.await()
            Log.e(TAG, "onCreate: async---${c}" )
        }

    }

    override fun onResume() {
        loge("onResume 111--${System.currentTimeMillis()}")
        super.onResume()
        loge("onResume 222--${System.currentTimeMillis()}")

        loge("onResume 444--lp?.width:${iv_xiecheng?.height}  ${iv_xiecheng?.measuredHeight}")
    }

    suspend fun add(a: Int, b: Int): Int {
        return withContext(Dispatchers.IO){
            delay(2000)
            return@withContext a+b
        }
    }


    suspend fun aa() {
        withContext(Dispatchers.IO) {
            Log.e(TAG, "aa: aaaaaaaa")
            delay(1000)
        }
    }


    suspend fun bb() {
        withContext(Dispatchers.IO) {
            Log.e(TAG, "b: bbbbbb")
            delay(10000)
        }
    }
}
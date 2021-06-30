package com.example.kotlindemo.xiecheng

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import kotlinx.coroutines.*

class XieCheng2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xie_cheng2)

        CoroutineScope(Dispatchers.Main).launch {
            loge("thread---${Looper.getMainLooper()== Looper.myLooper()}")
            loge("thread---${Thread.currentThread().id}")
            val a = async {  add(1, 2)}
            val b = async {  add(2,3)}
            val e = a.await()+b.await();

        }
    }

    suspend fun add(a: Int, b: Int): Int {
        return withContext(Dispatchers.IO) {
            loge("thread${Thread.currentThread().id}")
            loge("thread${Looper.getMainLooper()== Looper.myLooper()}")
            loge("thread-----------")
            delay(2000)
            return@withContext a + b
        }
    }

}
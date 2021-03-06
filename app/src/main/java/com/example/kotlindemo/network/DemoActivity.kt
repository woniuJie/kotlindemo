package com.example.kotlindemo.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlindemo.R
import kotlinx.coroutines.*
import loge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import top.jowanxu.wanandroidclient.bean.HomeListResponse
import kotlin.concurrent.thread

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        thread {
            initRetrofit()
        }

        CoroutineScope(Dispatchers.Main).launch {
            val result = xxx()
            Log.e("TAG", "initRetrofit: $result")
        }
    }

    fun initRetrofit(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.wanandroid.com/")
            .build()
        val service = retrofit.create(ApiService::class.java)
        val call = service.getHomeList()
        call.enqueue(object : Callback<HomeListResponse>{
            override fun onResponse(
                call: Call<HomeListResponse>?,
                response: Response<HomeListResponse>?
            ) {
                loge("zsj","数据${response}")
            }

            override fun onFailure(call: Call<HomeListResponse>?, t: Throwable?) {
                loge("zsj","失败${t?.printStackTrace()}")
            }
        })
    }

    suspend fun xxx() : HomeListResponse {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.wanandroid.com/")
            .build()

        val service = retrofit.create(ApiService::class.java)

        return withContext(Dispatchers.IO){
            service.getHomeListSu()
        }
    }
}
package com.example.kotlindemo.network

import retrofit2.Call
import retrofit2.http.GET
import top.jowanxu.wanandroidclient.bean.HomeListResponse

/**
 *  @author zhangshijie on 2020/7/6
 *  description:
 */
interface ApiService {
    @GET("article/list/1/json")
    fun getHomeList() : Call<HomeListResponse>

    @GET("article/list/1/json")
    suspend fun getHomeListSu() : HomeListResponse
}
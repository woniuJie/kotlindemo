package com.example.kotlindemo.xiecheng

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

object ViewModel {
    const val PHOTO_URL =
        "https://m.360buyimg.com/mobilecms/s376x240_jfs/t1/49601/16/12206/115887/5d91b4d5E34709952/aba2bcb4855e6e52.png!q70.jpg.dpg"
    suspend fun getImage(): Bitmap ?= withContext(Dispatchers.IO) {
        Log.e("zsj", "getImage: "+Thread.currentThread().name)

        val connection = URL(PHOTO_URL).openConnection() as HttpURLConnection
        var bitmap: Bitmap? = null
        if (connection.responseCode == 200) {
            bitmap = BitmapFactory.decodeStream(connection.inputStream)
        }
        return@withContext bitmap
    }

    suspend fun decodeBitmap(bitmap: Bitmap)= withContext(Dispatchers.Default){
        Log.e("zsj", "decodeBitmap: "+Thread.currentThread() )
        Bitmap.createBitmap(bitmap,0,0,bitmap.width/2,bitmap.height/2)
    }

}
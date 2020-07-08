package com.example.kotlindemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemo.test.coroutineScopeMain
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.*
import java.net.URL
import javax.net.ssl.HttpsURLConnection
class SecondActivity : AppCompatActivity() {

    companion object {
        const val PHOTO_URL =
            "https://m.360buyimg.com/mobilecms/s376x240_jfs/t1/49601/16/12206/115887/5d91b4d5E34709952/aba2bcb4855e6e52.png!q70.jpg.dpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        coroutineScopeMain.launch {
            val bitmap = suspendingGetImage()
            bitmap?.let {
                imageview1.setImageBitmap(it)
                val bitmap2 = suspendingCropBitmap(it, 2, 2)
                imageview2.setImageBitmap(bitmap2)
                val bitmap3 = suspendingCropBitmap(it, 3, 3)
                imageview3.setImageBitmap(bitmap3)
            }
        }
    }

    private suspend fun suspendingGetImage(): Bitmap? = withContext(Dispatchers.IO) {
        val connection = URL(PHOTO_URL).openConnection() as HttpsURLConnection
        var bitmap: Bitmap? = null
        if (connection.responseCode == 200) {
            bitmap = BitmapFactory.decodeStream(connection.inputStream)
        }
        return@withContext bitmap
    }

    private suspend fun suspendingCropBitmap(bitmap: Bitmap, sliceX: Int, sliceY: Int) =
        withContext(Dispatchers.Default) {
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width / sliceX, bitmap.height / sliceY)
        }


}
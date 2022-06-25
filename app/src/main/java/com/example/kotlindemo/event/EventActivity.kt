package com.example.kotlindemo.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.kotlindemo.R
import com.example.kotlindemo.onClick
import kotlinx.android.synthetic.main.activity_event.*
import kotlin.math.log

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)



        ll_event.onClick {
            Log.e("zsj", "onCreate: 下层")
        }

        view_event.onClick {
            Log.e("zsj", "onCreate: 上层" )
        }

        rel_z.onClick {
            Log.e("zsj", "onCreate: 父亲")
        }
    }
}
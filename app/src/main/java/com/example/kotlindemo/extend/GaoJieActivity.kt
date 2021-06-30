package com.example.kotlindemo.extend

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.coroutineScopeMain
import com.example.kotlindemo.loge
import com.example.kotlindemo.xiecheng.XieChengActivity
import kotlinx.android.synthetic.main.activity_edit_text.view.*
import kotlinx.android.synthetic.main.activity_gao_jie.*
import kotlinx.coroutines.*
import java.util.ArrayList

class GaoJieActivity : AppCompatActivity() {
    companion object{
        const val TAG = "zsj"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gao_jie)

        val HHA = HHA()
        HHA.HHHH(object :HHA.ClickListener{
            override fun method() {

            }
        })

        val b = fun(params:Int) : String{
            return params.toString()
        }

        loge("zsj"+b(1))
//
//        val b111: (Int) -> String = {
//            return it.toString() // it 可以被推断出是 Int 类型
//        }


        val bbb = ::a
        Log.e("zsj", "onCreate: ::a-"+bbb(2) )


        fun map(a:Int,b:Int,fn:(Int,Int)->Int):String{
            return fn(a,b).toString()
        }

        fun aandb(a:Int,b:Int):Int{
            return a+b
        }

        fun achengb(a:Int,b:Int):Int{
            return a*b
        }

        Log.e(TAG, "onCreate: map-aandb:${map(1,2,::aandb)}")
        Log.e(TAG, "onCreate: map-achengb:${map(1,2,::achengb)}")

        fun findName(name:String):(Int)->Int{
            fun name1(a:Int):Int{
                return a+1
            }
            fun name2(b:Int):Int{
                return b+2
            }

            return when(name){
                "zhang"-> ::name1
                else -> ::name2
            }
        }
        var xx = findName("zhang")
        Log.e(TAG, "onCreate: findName:${xx(1)}" )

        fun findname1(name:String):(Int)->Int{
            when(name){
                "zhang"->return { n:Int->
                    n*n
                }
                else -> return {n->
                    n+n
                }
            }
        }

        var yy = findname1("xx")
        Log.e(TAG, "onCreate: findname1${yy(4)}" )

        var lambdaList = ArrayList<(Int)->Int>()
        fun collonFun(fn:(Int)->Int)
        {
            lambdaList.add(fn)
        }

        collonFun {it*it}
        Log.e(TAG, "onCreate: lambdaList${lambdaList.size}" )
        for (i in lambdaList.indices){
            Log.e(TAG, "onCreate: ${lambdaList[i](i+3)}" )
        }

        var square = { n:Int->
            n*n
        }

        var square1 = { n:Int->
            n*n
        }(3)

        Log.e(TAG, "onCreate: square${square(2)}" )
        Log.e(TAG, "onCreate: square${square1}" )



        var square2:(Int)->Int = {n->n*n}

        var square3:(Int)->Int = {
            it*it
        }

        var xxyy = fun(x:Int,y:Int):Int{
            return x+y
        }
        Log.e(TAG, "onCreate: ${xxyy(1,2)}" )

        CoroutineScope(Dispatchers.Main).launch {
            Log.e("zsj", "onCreate:CoroutineScope(Dispatchers.Main)--- " )
            val a = async { add(1,2) }
            val b = async { add(2,3) }
            val c = a.await()+b.await()
            Log.e("zsj", "onCreate: async---${c}" )
        }

    }

    suspend fun add(a: Int, b: Int): Int {
        return withContext(Dispatchers.IO){
            delay(2000)
            return@withContext a+b
        }
    }

    fun bbb(){
        loge("bbb1")

    }


    fun a(b:Int):String{
        return b.toString()
    }

    lateinit var onClick :(View) -> Unit
    fun setOnClickListener1(onClick: (View) -> Unit) {
        this.onClick = onClick
    }


}
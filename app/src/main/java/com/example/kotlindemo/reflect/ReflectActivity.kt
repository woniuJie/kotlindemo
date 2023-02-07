package com.example.kotlindemo.reflect

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.clipchlidren.ClipChilderenActivity
import com.example.kotlindemo.loge
import com.example.kotlindemo.textview.MyTextDemoActivity
import java.lang.reflect.Proxy

class ReflectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect_layout)


        val clazz = Class.forName("com.example.kotlindemo.reflect.ChildDemo")
        val fields = clazz.fields
        fields.forEach {
            loge("变量 fields ${it.type.name} --- ${it.name}")
        }

        loge("--------------------")

        val declaredFields = clazz.declaredFields
        declaredFields.forEach {
            loge("变量 declaredFields ${it.type.name} --- ${it.name}")
        }

        loge("--------------------")

        val methods = clazz.methods
        methods.forEach {
            loge("方法 methods ${it.returnType.name} --- ${it.name} ")
        }

        loge("--------------------")

        val declaredMethods = clazz.declaredMethods
        declaredMethods.forEach {
            loge("方法 declaredMethods ${it.returnType.name} --- ${it.name} ")
        }

        loge("类名 name ${ChildDemo.ChildChildDemo::class.java.name}")
        loge("类名 simpleName ${ChildDemo.ChildChildDemo::class.java.simpleName}")
        loge("类名 canonicalName ${ChildDemo.ChildChildDemo::class.java.canonicalName}")

        loge("--------------------")

        loge("类名 name ${ChildDemo::class.java.name}")
        loge("类名 simpleName ${ChildDemo::class.java.simpleName}")
        loge("类名 canonicalName ${ChildDemo::class.java.canonicalName}")

        try {
            Class.forName(ChildDemo.ChildChildDemo::class.java.name)
        }catch (e:Exception){
            loge("error 1 ${e.localizedMessage}")
        }

        try {
            Class.forName(ChildDemo::class.java.canonicalName)
        }catch (e:Exception){
            loge("error 2 ${e.localizedMessage}")
        }

        val liuwangshu: IShop = LiuWangShu()
        val dynamicPurchasing = DynamicPurchasing(liuwangshu)
        val loader = liuwangshu.javaClass.classLoader
        val pur = Proxy.newProxyInstance(
            loader, arrayOf<Class<*>>(
                IShop::class.java
            ), dynamicPurchasing
        ) as IShop
        pur.buy()


    }

    fun onTurnRefrelt(view: View) {

        val field = Activity::class.java.getDeclaredField("mInstrumentation")
        field.isAccessible = true
        val instrumentation = field.get(this) as Instrumentation
        val instrumentationProxy = InstrumentationProxy(instrumentation)
        field.set(this,instrumentationProxy)

        Intent(this, MyTextDemoActivity::class.java).run {
            startActivity(this)
        }
    }



}
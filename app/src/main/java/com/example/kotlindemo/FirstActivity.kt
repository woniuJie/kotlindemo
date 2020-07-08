package com.example.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemo.test.*
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity(), Impl {

    var name: String? = "zsj"

    var user = User()

    var age: Int = 1
    var charx: Char = 'C'
    var boo: Boolean = true
    var intarr: IntArray = intArrayOf(4)
    var str: String = "xxx"
    var strArr: Array<String>? = null

    lateinit var view: View

    val list1233 : List<String> ?= null

    lateinit var map: Map<String, String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        tv_name.text = name

        println("zsj-----" + name?.length)
        println("zsj-----" + add(name))

        println("zsj-----" + user.age)
        strArr = arrayOf("", "")

        var person: Persion = Man()
        (person as? Man)?.add()
        if(person is Man){
            person.add()
        }

        Sample.name
        Sample.m()
        Sample1.AA.age

        toast(this, "xxx")

        val lists = listOf("xx", "ww")

        var user = User("xxx")

        val listener1 = object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
        }

        val str: Array<String> = arrayOf("", "")


        val list = listOf("zsj")
        list.toMutableList().add("xxx")

        var list1 = mutableListOf("zsj")
        list1.add("xxx")


        map = mapOf("" to "", "" to "")
        map.get("")

        val car = Car("", "")


        val xx: String? = null
        println("zsj---add1-" + add1(xx))
        sayHi("", false, age = 12)


        val h1 = "zzz"
        val h2 = "ccc"

        val test = """
            hi $h1
            my name is $h2 \n
            
        """.trimIndent()
        println("zsj-----------------")
        println(test)

        val s = "xx"
        when {
            s.endsWith("x") -> println("")
            s.equals("xx") -> println("")
        }

        val arr = intArrayOf(1, 2, 3)
        for (item in arr) {

        }

        for (i in 0..10) {

        }

        val str123: Array<String> = arrayOf("")

        val intarray1 = intArrayOf(1, 2, 3)
        val list123 = listOf(1, 2, 3)

        println("zsj-list-size" + list123.size)

        intarray1.forEach {
            println("$it xxx")
        }

        val filter1 = intarray1.filter { it > 2 }

        val map1 = intarray1.map { it + 1 }
//
        intarray1
            .flatMap { listOf(it + 1, 101) }
            .forEach { println("zsj $it") }

        println("zsj------------")

        val square = sequenceOf(1, 2, 3, 4)
        val result = square.map {
            println("zsj-map-$it")
            it + 1
        }.filter {
            println("zsj-filter$it")
            it % 3 == 0
        }

//        println("zsj-result" + result.first())

        println("zsj------------")

        val mylist = listOf(1, 2, 3)
        val result1 = mylist.map {
            println("zsj-map$it")
            it + 1
        }.filter {
            println("zsj-filter$it")
            it > 3
        }

    }

    suspend fun aaa(){

    }
    fun add(name: String?): String? {
        return name
    }

    fun add1(name: String?) = name

    fun sayHi(name: String = "zsj") {

    }

    fun sayHi(name: String = "xxx", isName: Boolean = false, age: Int) {

    }
}
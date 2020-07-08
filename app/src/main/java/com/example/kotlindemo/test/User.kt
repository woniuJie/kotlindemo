package com.example.kotlindemo.test

/**
 *  Created by zhangshijie on 2020/6/22
 *  Describe:
 */
open class User {

    constructor() {

    }

    constructor(name: String) {

    }

    var name: String? = null
    fun run() {
        name = "zsj"
    }

    val age = "123"
        get() {
            return field + "22"
        }

    companion object {
        const val name: String = ""
    }

    object A{
        object B{
            object C{

            }
        }
    }
}
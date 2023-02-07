package com.example.kotlindemo.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.loge
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava_demo_layout.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2022/6/18
 * @email:zhangshijie@soyoung.com
 * @Description: 
 */
class RxJavaDemoActitivy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_demo_layout)


        Observable.just("111","222","333","444","555","666","777","888")
            .flatMap {
                Observable.just(it+"xxxx")
            }.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {  })

        Observable.create(object :  ObservableOnSubscribe<Any> {
            override fun subscribe(emitter: ObservableEmitter<Any>) {
                emitter.onNext("2")
//                emitter.onComplete()
                emitter.onError(Throwable("xxx"))
            }

        }).subscribe(object : Consumer<Any>{
            override fun accept(t: Any?) {
                loge("t$t")
            }
        },object : Consumer<Throwable>{
            override fun accept(t: Throwable?) {
                loge("异常t${t?.localizedMessage}")

            }
        })
    }

    val observerA = object : Observer<Any>{
        override fun onSubscribe(d: Disposable) {
            loge("onSubscribe")
        }

        override fun onNext(t: Any) {
            loge("t$t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            loge("onComplete")

        }

    }
}
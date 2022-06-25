package com.example.kotlindemo.shouye

import android.animation.TypeEvaluator

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/12/18
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class CategotyPointEvaluator  : TypeEvaluator<CategotyPoint> {
    /**
     * 根据插值器计算出当前对象的属性的百分比fraction,估算去属性当前具体的值
     * @param fraction 属性改变的百分比
     * @param startValue 对象开始的位置，例如这里点坐标开始位置：屏幕左上角位置
     * @param endValue 对象结束的位置，例如这里点坐标结束的位置:屏幕右下角位置
     */
    override fun evaluate(fraction: Float, startValue: CategotyPoint, endValue: CategotyPoint): CategotyPoint {
        val left: Float = startValue?.x + fraction * (endValue.x - startValue.x)
        val right: Float = startValue.y + fraction * (endValue.y - startValue.y)
        return CategotyPoint(left, right)
    }
}


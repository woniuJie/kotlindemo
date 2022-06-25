package com.example.kotlindemo.flexible.flexible.callback;

/**
 * @author:zhangshijie
 * @Date:2021/9/7
 * 下拉监听
 */
public interface OnPullListener {

    /**
     * 下拉
     * @param offset
     */
    void onPull(int offset);

    /**
     * 松开
     */
    void onRelease();
}

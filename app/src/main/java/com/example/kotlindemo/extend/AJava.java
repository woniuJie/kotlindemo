package com.example.kotlindemo.extend;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:zhangshijie
 * @Date:2021/3/1
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
public class AJava {

    public static void main(String[] args) {
        List<? extends AAAA > list = new ArrayList<>();
        BBBB b = new BBBB();
        BBBB b1 = (BBBB) list.get(0);
        List<BBBB> list1 = new ArrayList<>();

        list = list1;

    }
}

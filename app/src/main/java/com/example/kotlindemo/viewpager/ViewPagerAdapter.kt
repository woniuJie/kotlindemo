package com.example.kotlindemo.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * @version
 * @author:zhangshijie
 * @Date:2020/7/23
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class ViewPagerAdapter(fm: FragmentManager, val mFragmengLists: List<Fragment>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mFragmengLists[position]
    }

    override fun getCount(): Int {
        return mFragmengLists.size
    }
}
package com.example.kotlindemo.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * @author qiuweilong
 * 创建日期：2019/9/18
 * 描述 懒加载FragmentAdapter  在fragment的onresume里面调用请求网络可以实现懒加载
 */
class LazyResumeFragmentAdapter(
    fm: FragmentManager?,
    private val mFragments: List<Fragment>?,
    private val titles: List<String>?
) : FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val isOverrideItemPosition = true
    override fun getItemPosition(`object`: Any): Int {
        return if (isOverrideItemPosition) {
            PagerAdapter.POSITION_NONE
        } else {
            super.getItemPosition(`object`)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        if (titles != null && position >= 0 && position < titles.size) {
            title = titles[position]
        }
        return title
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return mFragments?.size ?: 0
    }

}
package com.example.kotlindemo.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlindemo.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.activity_view_pager.view.*

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var adapter: ViewPagerAdapter
    private var mLists = mutableListOf<Fragment>()
    private val scaleTransforer = ScaleInTransformer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val fragment1 = BlankFragment.newInstance("", "")
        val fragment2 = BlankFragment.newInstance("", "")
        val fragment3 = BlankFragment.newInstance("", "")
        val fragment4 = BlankFragment.newInstance("", "")
        val fragment5 = BlankFragment.newInstance("", "")

        mLists.add(fragment1)
        mLists.add(fragment2)
        mLists.add(fragment3)
        mLists.add(fragment4)
        mLists.add(fragment5)
        adapter = ViewPagerAdapter(supportFragmentManager, mLists)
        viewpager.adapter = adapter
        viewpager.pageMargin = 40
        viewpager.clipChildren = false
        viewpager.offscreenPageLimit = 3
        viewpager.setPageTransformer(true, scaleTransforer)



    }
}


package com.example.kotlindemo.viewpager

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import com.example.kotlindemo.async.AsyncLayoutInflatePlus
import com.example.kotlindemo.spannable.ImageParamsCheckUtils
import kotlinx.android.synthetic.main.activity_spannable_string.*
import kotlinx.android.synthetic.main.fragment_blank.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var rootView: View? = null


    private val mAdapter by lazy {
        MyViewPagerImAdapter(context!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://www.baidu.com/img/bd_logo1.png"
        Glide.with(this).load(url)
            .into(iv_black_img)

        ImageParamsCheckUtils.findAllImageParamsIsWrap(activity, rootView, R.layout.fragment_blank)
        ImageParamsCheckUtils.findAllImageParamsIsWrapByRecyclerView(rootView)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_viewpager.layoutManager = layoutManager
        recycler_viewpager.adapter = mAdapter

        val list = mutableListOf<String>()
        list.add("22")
        list.add("22")

        mAdapter.setNewData(list)


    }



    companion object {
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
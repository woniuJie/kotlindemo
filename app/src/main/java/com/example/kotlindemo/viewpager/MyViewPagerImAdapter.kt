package com.example.kotlindemo.viewpager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemo.R
import kotlinx.android.synthetic.main.activity_view_pager.*

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/7/20
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class MyViewPagerImAdapter(val mContext: Context) :
    RecyclerView.Adapter<MyViewPagerImAdapter.MyViewHohder>() {

    var list = mutableListOf<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHohder {
        return MyViewHohder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_viewpager_layout, parent, false)
        )
    }

    fun setNewData(mlist: List<String>) {
        list.addAll(mlist)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHohder, position: Int) {

        val url = "https://www.baidu.com/img/bd_logo1.png"
        holder.imageview1?.let {
            Glide.with(mContext).load(url)
                .into(it)
        }
        holder.imageview2?.let {
            Glide.with(mContext).load(url)
                .into(it)
        }

    }


    class MyViewHohder(converView: View) : RecyclerView.ViewHolder(converView) {
        var imageview1: ImageView? = null
        var imageview2: ImageView? = null

        init {
            imageview1 = converView.findViewById(R.id.iv_item_viewpager1)
            imageview2 = converView.findViewById(R.id.iv_item_viewpager2)
        }
    }

}
package com.example.kotlindemo

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

/**
 * @version
 * @author:zhangshijie
 * @Date:2021/7/20
 * @email:zhangshijie@soyoung.com
 * @Description:
 */
class ImageParamsCheckUtils {

    companion object {
        fun findAllImageParamsIsWrap(mActivity: FragmentActivity?,rootView:View?,layoutRes:Int) {
            rootView?:return
            rootView?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                    val list = getAllChildImageViews(rootView)
                    val simpleName = mActivity?.javaClass?.simpleName

                    if (list.isNotEmpty()) {
                        for (i in 0 until list.size) {
                            val view = list.get(i)

                            var name: CharSequence? = ""
                            try {
                                name =  mActivity?.resources?.getResourceEntryName(list.get(i).getId())
                            }catch (e:Exception){

                            }
                            val xml: CharSequence? =
                                    mActivity?.resources?.getResourceEntryName(layoutRes)

                            view.post {
                                val widthWrap = getTargetWidth(view)
                                val heightWrap = getTargetHeight(view)
                                if (widthWrap == 3 || heightWrap == 3) {
                                    Log.e("zsj", "findAllImageParamsIsWrap: "+
                                            "当前activit：$simpleName---当前布局文件为：$xml----imageview的ID：$name----宽度width是否设置了Wrap_content:${widthWrap == 3}---高度height是否设置了Wrap_content:${heightWrap == 3}----view${view}}"
                                    )
                                }
                            }
                        }
                    }
                }
            })
        }

        private fun getAllChildImageViews(view: View?): MutableList<View> {
            val list = mutableListOf<View>()
            if (view is ViewGroup) {
                val vg = view as? ViewGroup
                vg?.let {
                    for (i in 0..vg.childCount) {
                        val viewChild = vg.getChildAt(i)
                        viewChild?.let {
                            if (viewChild is ImageView) {
                                list.add(viewChild)
                            }
                            list.addAll(getAllChildImageViews(viewChild))
                        }
                    }
                }
            }else{
                if(view is ImageView){
                    list.add(view)
                }
            }
            return list
        }

        fun findAllImageParamsIsWrapByRecyclerView(rootView: View?){
            val list = getAllChildRecyclerViews(rootView)

            if(list.isNotEmpty()){
                for (i in 0 until list.size){
                    val recyclerView = list.get(i)

                    recyclerView?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                        override fun onGlobalLayout() {
                            recyclerView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)

                            for (i in 0 until recyclerView.childCount){
                                val childView = recyclerView.getChildAt(i)?:return

                                val ivList = getAllChildImageViews(childView)
                                if (ivList.isNotEmpty()) {
                                    for (i in 0 until ivList.size) {
                                        val view = ivList.get(i)
//                                        val name: CharSequence? =
//                                            mActivity?.resources?.getResourceEntryName(list.get(i).getId())
//                                        val xml: CharSequence? =
//                                            mActivity?.resources?.getResourceEntryName(layoutRes)

                                        view.post {
                                            val widthWrap = getTargetWidth(view)
                                            val heightWrap = getTargetHeight(view)
                                            if (widthWrap == 3 || heightWrap == 3) {
                                                Log.e("zsj", "onGlobalLayout: "+
                                                        "recyclerview ${recyclerView.adapter?.javaClass?.simpleName} ----宽度width是否设置了Wrap_content:${widthWrap == 3}---高度height是否设置了Wrap_content:${heightWrap == 3}----view${view}}"
                                                )
                                            }
                                        }
                                    }
                                }

                            }

                        }
                    })
                }
            }
        }


        fun getAllChildRecyclerViews(view: View?): MutableList<RecyclerView> {
            val list = mutableListOf<RecyclerView>()
            if (view is ViewGroup) {
                val vg = view as? ViewGroup
                vg?.let {
                    for (i in 0..vg.childCount) {
                        val viewChild = vg.getChildAt(i)
                        viewChild?.let {
                            if (viewChild is RecyclerView) {
                                list.add(viewChild as RecyclerView)
                            }
                            list.addAll(getAllChildRecyclerViews(viewChild))
                        }
                    }
                }
            }else{
                if (view is RecyclerView) {
                    list.add(view as RecyclerView)
                }
            }
            return list
        }

        private fun getTargetWidth(view: View): Int {
            val horizontalPadding: Int = view.getPaddingLeft() + view.getPaddingRight()
            val layoutParams: ViewGroup.LayoutParams = view.getLayoutParams()
            val layoutParamSize = layoutParams?.width ?: 0

            return getTargetDimen(view, view.width, layoutParamSize, horizontalPadding)
        }

        private fun getTargetHeight(view: View): Int {
            val verticalPadding = view.paddingTop + view.paddingBottom
            val layoutParams = view.layoutParams
            val layoutParamSize = layoutParams?.height ?: 0
            return getTargetDimen(view, view.height, layoutParamSize, verticalPadding)
        }

        private fun getTargetDimen(
                view: View,
                viewSize: Int,
                paramSize: Int,
                paddingSize: Int
        ): Int {
            val adjustedParamSize = paramSize - paddingSize
            if (adjustedParamSize > 0) {
                return 1
            }

            val adjustedViewSize = viewSize - paddingSize
            if (adjustedViewSize > 0) {
                return 2
            }

            if (!view.isLayoutRequested && paramSize == ViewGroup.LayoutParams.WRAP_CONTENT) {
                return 3
            }
            return 0
        }

    }

}
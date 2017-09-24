package com.ndeveat.pinpost.Categories.PushCategory

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.Category.CategoryModel
import com.ndeveat.pinpost.DataCenter
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class PushCateogryAdapter : RecyclerView.Adapter<PushCategoryHolder>() {
    val mCategory: ArrayList<CategoryModel>
    val mPushSocialServices: ArrayList<CategoryModel>

    init {
        mCategory = DataCenter.instance.Categories
        mPushSocialServices = ArrayList<CategoryModel>()
    }

    override fun getItemCount(): Int = mCategory.size

    override fun onBindViewHolder(holder: PushCategoryHolder?, position: Int) {
        val category = mCategory[position]
        val dataCenter = DataCenter.instance

        holder!!.setCategoryBackground(category.background)
        holder.mIconLayer?.setOnClickListener {
            if (holder.check()) {
                mPushSocialServices.add(category)
            } else {
                mPushSocialServices.remove(category)
            }
        }
        holder.setCategoryIcon(category.drawable)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PushCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.push_category_view, parent, false)
        val holder = PushCategoryHolder(view)
        return holder
    }
}
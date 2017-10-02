package com.ndeveat.pinpost.Categories.Push

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.SNSModel
import com.ndeveat.pinpost.DataCenter
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class PushCateogryAdapter : RecyclerView.Adapter<PushCategoryHolder>() {
    val mSNS: ArrayList<SNSModel>
    val mPushSocialServices: ArrayList<SNSModel>

    init {
        mSNS = DataCenter.instance.SNSList
        mPushSocialServices = ArrayList<SNSModel>()
    }

    override fun getItemCount(): Int = mSNS.size

    override fun onBindViewHolder(holder: PushCategoryHolder?, position: Int) {
        val category = mSNS[position]
        val dataCenter = DataCenter.instance

        holder!!.setCategoryBackground(category.snsMainColor)
        holder.mIconLayer?.setOnClickListener {
            if (holder.check()) {
                mPushSocialServices.add(category)
            } else {
                mPushSocialServices.remove(category)
            }
        }
        holder.setCategoryIcon(category.snsPlusImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PushCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.push_category_view, parent, false)
        val holder = PushCategoryHolder(view)
        return holder
    }
}
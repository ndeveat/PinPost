package com.ndeveat.pinpost.Ui.Categories.Push

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class PushCateogryAdapter : RecyclerView.Adapter<PushCategoryHolder>() {
    interface PushCategoryEvent {
        fun add(snsName: String)
        fun remove(snsName: String)
    }

    val mSocialNetwork: ArrayList<SocialNetworkModel>
    var mPushCategoryEvent: PushCategoryEvent? = null

    init {
        mSocialNetwork = Manager.instance.snsList
    }

    override fun getItemCount(): Int = mSocialNetwork.size

    override fun onBindViewHolder(holder: PushCategoryHolder?, position: Int) {
        val category = mSocialNetwork[position]
        val dataCenter = Manager.instance

        holder!!.setCategoryBackground(category.snsMainColor)
        holder.mIconLayer?.setOnClickListener {
            if (holder.check()) {
                mPushCategoryEvent?.add(category.snsType.toString())
            } else {
                mPushCategoryEvent?.remove(category.snsType.toString())
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
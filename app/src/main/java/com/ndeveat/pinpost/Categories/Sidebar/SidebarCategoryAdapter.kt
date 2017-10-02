package com.ndeveat.pinpost.Categories.Sidebar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.SNSModel
import com.ndeveat.pinpost.DataCenter
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class SidebarCategoryAdapter : RecyclerView.Adapter<SidebarCategoryHolder>() {
    val mSNS: ArrayList<SNSModel>

    init {
        mSNS = DataCenter.instance.SNSList
    }

    override fun getItemCount(): Int = mSNS.size

    override fun onBindViewHolder(holder: SidebarCategoryHolder?, position: Int) {
        val sns = mSNS[position]
        holder!!.setEmail(sns.email)
        holder.setSnsIcon(sns.snsPlusImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SidebarCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.ui_socialnetwork_bar, parent, false)
        val holder = SidebarCategoryHolder(view)
        return holder
    }
}
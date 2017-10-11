package com.ndeveat.pinpost.Ui.Categories.Sidebar

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class SidebarCategoryAdapter : RecyclerView.Adapter<SidebarCategoryHolder>() {
    val mSocialNetwork: ArrayList<SocialNetworkModel>

    init {
        mSocialNetwork = Manager.instance.SNSList
    }

    override fun getItemCount(): Int = mSocialNetwork.size

    override fun onBindViewHolder(holder: SidebarCategoryHolder, position: Int) {
        val sns = mSocialNetwork[position]

        holder.setEmail(if (sns.isLogin) sns.email else null)
        holder.setSnsIcon(sns.snsPlusImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SidebarCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.side_socialnetwork_bar, parent, false)
        val holder = SidebarCategoryHolder(view)
        return holder
    }
}
package com.ndeveat.pinpost.Ui.Categories.Sidebar

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class SidebarCategoryAdapter : RecyclerView.Adapter<SidebarCategoryHolder>() {
    interface SidebarEvent {
        fun login(snsType: SocialNetworkType)
        fun logout(snsType: SocialNetworkType)
    }

    val mSocialNetwork: ArrayList<SocialNetworkModel>
    var sidebarEvent: SidebarEvent? = null

    init {
        mSocialNetwork = Manager.instance.snsList
    }

    override fun getItemCount(): Int = mSocialNetwork.size

    override fun onBindViewHolder(holder: SidebarCategoryHolder, position: Int) {
        val sns = mSocialNetwork[position]

        holder.setEmail(if (sns.isLogin) sns.email else null)
        holder.setSnsIcon(sns.snsPlusImage)
        holder.login?.setOnClickListener {
            if (sns.isLogin) {
                logout(it.context, sns)
            } else {
                login(it.context, sns)
            }
        }
    }

    fun login(context: Context, sns: SocialNetworkModel) {
        val loginAlert = AlertDialog.Builder(context)
        loginAlert.setTitle("로그인 하시겠습니까?")
        loginAlert.setPositiveButton("예",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    sidebarEvent?.login(sns.snsType)
                    notifyDataSetChanged()
                })
        loginAlert.setNegativeButton("아니요",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
        loginAlert.show()
    }

    fun logout(context: Context, sns: SocialNetworkModel) {
        val logoutAlert = AlertDialog.Builder(context)
        logoutAlert.setTitle("로그아웃 하시겠습니까?")
        logoutAlert.setPositiveButton("예",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    sidebarEvent?.logout(sns.snsType)
                    this.notifyDataSetChanged()
                })
        logoutAlert.setNegativeButton("아니요",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.cancel()
                })
        logoutAlert.show()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SidebarCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.side_socialnetwork_bar, parent, false)
        val holder = SidebarCategoryHolder(view)
        return holder
    }
}
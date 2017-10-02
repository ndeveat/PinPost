package com.ndeveat.pinpost.Categories.Sidebar

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.ndeveat.pinpost.Ui.TextView
import kotlinx.android.synthetic.main.push_category_view.view.*
import kotlinx.android.synthetic.main.ui_socialnetwork_bar.view.*

/**
 * Created by ndeveat on 2017. 10. 2..
 */

class SidebarCategoryHolder : RecyclerView.ViewHolder {
    var mEmail: TextView? = null
    var mIcon: ImageView? = null
    var mLogin: FrameLayout? = null

    var isCheck = false

    constructor(view: View) : super(view) {
        mEmail = view.email
        mIcon = view.icon
        mLogin = view.login
    }

    fun setSnsIcon(drawable: Drawable) {
        mIcon!!.setImageDrawable(drawable)
    }

    fun setEmail(email: String) {
        mEmail!!.text = email
    }
}
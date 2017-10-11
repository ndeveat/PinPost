package com.ndeveat.pinpost.Ui.Categories.Sidebar

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.View.TextView
import kotlinx.android.synthetic.main.side_socialnetwork_bar.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundDrawable

/**
 * Created by ndeveat on 2017. 10. 2..
 */

class SidebarCategoryHolder : RecyclerView.ViewHolder {
    var emailText: TextView? = null
    var iconImage: ImageView? = null

    var loginOutline: FrameLayout? = null
    var loginIcon: FrameLayout? = null
    var login: FrameLayout? = null

    constructor(view: View) : super(view) {
        emailText = view.email
        iconImage = view.icon
        login = view.login
        loginOutline = view.login_outline
        loginIcon = view.login_icon
    }

    fun setSnsIcon(drawable: Drawable) {
        iconImage?.setImageDrawable(drawable)
    }

    fun setEmail(email: String?) {
        emailText?.text = if (email == null) "로그인 해주세요" else email
        setLoginButtonn(if (email == null) false else true)
    }

    fun setLoginButtonn(isLogin: Boolean) {
        val context = itemView.context
        loginIcon?.background =
                if (isLogin)
                    ContextCompat.getDrawable(context, R.drawable.icon_cross)
                else
                    ContextCompat.getDrawable(context, R.drawable.icon_plus)

        loginOutline?.background!!.setColorFilter(
                if (isLogin)
                    ContextCompat.getColor(context, R.color.pinpost)
                else
                    ContextCompat.getColor(context, android.R.color.darker_gray), PorterDuff.Mode.MULTIPLY)
    }
}
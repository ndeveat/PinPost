package com.ndeveat.pinpost.Ui.Categories.Push

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.push_category_view.view.*

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class PushCategoryHolder : RecyclerView.ViewHolder {
    var mIconLayer: FrameLayout? = null
    var mIconImage: ImageView? = null
    var mCheckLayout: FrameLayout? = null

    var isCheck = false

    constructor(view: View) : super(view) {
        mIconImage = view.push_category_icon
        mIconLayer = view.push_category_icon_background
        mCheckLayout = view.push_category_checklayer
    }

    fun check() : Boolean {
        isCheck = !isCheck
        if (isCheck) {
            mCheckLayout!!.visibility = View.VISIBLE
        } else {
            mCheckLayout!!.visibility = View.GONE
        }
        return isCheck
    }


    fun setCategoryBackground(color: Int) {
        //mIconLayer?.background?.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }

    fun setCategoryIcon(drawable: Drawable) {
        mIconImage?.setImageDrawable(drawable)
    }
}
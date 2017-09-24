package com.ndeveat.pinpost.Categories.Category

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.category_view.view.*

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class CategoryHolder : RecyclerView.ViewHolder {
    var mIconLayer: FrameLayout
    var mIcon: ImageView
    var mCount: TextView

    constructor(view: View) : super(view) {
        mIconLayer = view.category_background
        mIcon = view.category_icon
        mCount = view.category_count
    }

    fun setCategoryBackground(color: Int) {
        mIconLayer.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }

    fun setCategoryIcon(drawable: Drawable) {
        mIcon.setImageDrawable(drawable)
    }

    fun setCategoryCount(count: Int) {
        mCount.text = count.toString()
    }
}
package com.ndeveat.pinpost.Categories

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.categories_view.view.*

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class CategoriesHolder : RecyclerView.ViewHolder {
    var mBackground: FrameLayout
    var mIcon: ImageView
    var mCount: TextView

    constructor(view: View) : super(view) {
        mBackground = view.category_background
        mIcon = view.category_icon
        mCount = view.category_count
    }
}
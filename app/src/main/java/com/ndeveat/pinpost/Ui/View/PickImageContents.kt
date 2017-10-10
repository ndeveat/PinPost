package com.ndeveat.pinpost.Ui.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.ui_image_preview_contents.view.*

/**
 * Created by ndeveat on 2017. 9. 27..
 */

class PickImageContents {
    var image: ImageView? = null
    var delete: FrameLayout? = null

    constructor(view: View) {
        init(view)
    }

    fun init(view: View) {
        image = view.image
        delete = view.clear
    }
}
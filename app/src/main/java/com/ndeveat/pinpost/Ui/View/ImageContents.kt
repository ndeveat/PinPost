package com.ndeveat.pinpost.Ui.View

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.ui_image_preview_contents.view.*

/**
 * Created by ndeveat on 2017. 9. 27..
 */

class ImageContents : FrameLayout {
    var image: ImageView? = null
    var delete: FrameLayout? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    fun init() {
        val inflate = LayoutInflater.from(context)
        val view = inflate.inflate(R.layout.ui_image_preview_contents, null)
        addView(view)

        image = view.image
        delete = view.clear
    }
}
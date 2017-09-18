package com.ndeveat.pinpost.Tag

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView
import com.ndeveat.pinpost.R
import org.jetbrains.anko.backgroundDrawable
import org.w3c.dom.Text

/**
 * Created by ndeveat on 2017. 9. 18..
 */

class Tag : com.ndeveat.pinpost.Base.TextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    override fun init() {
        super.init()

        background = ContextCompat.getDrawable(context, R.drawable.tag)
    }
}
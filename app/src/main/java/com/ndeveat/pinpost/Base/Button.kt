package com.ndeveat.pinpost.Base

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by ndeveat on 2017. 9. 24..
 */
open class Button : TextView {
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
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicUltraLight.otf")
    }
}
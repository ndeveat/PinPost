package com.ndeveat.pinpost.Base

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by ndeveat on 2017. 9. 18..
 */

open class TextView : TextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    open fun init() {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/NanumBarunGothicUltraLight.otf")
    }
}
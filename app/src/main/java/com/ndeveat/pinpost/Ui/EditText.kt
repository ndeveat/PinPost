package com.ndeveat.pinpost.Ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText

/**
 * Created by ndeveat on 2017. 9. 25..
 */

open class EditText : EditText {
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
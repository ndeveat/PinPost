package com.ndeveat.pinpost

import com.ndeveat.pinpost.Categories.SNSModel

/**
 * Created by ndeveat on 2017. 9. 23..
 */

class DataCenter private constructor() {
    val SNSList = ArrayList<SNSModel>()

    private object Holder {
        val instance = DataCenter()
    }

    companion object {
        val instance: DataCenter by lazy { Holder.instance }
    }
}
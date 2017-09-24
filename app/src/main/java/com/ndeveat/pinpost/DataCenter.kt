package com.ndeveat.pinpost

import com.ndeveat.pinpost.Categories.Category.CategoryModel

/**
 * Created by ndeveat on 2017. 9. 23..
 */

class DataCenter private constructor() {
    val SocialNetworkServices = HashMap<SocialNetworkType, Int>()
    val Categories = ArrayList<CategoryModel>()

    private object Holder {
        val instance = DataCenter()
    }

    companion object {
        val instance: DataCenter by lazy { Holder.instance }
    }
}
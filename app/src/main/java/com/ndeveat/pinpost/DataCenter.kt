package com.ndeveat.pinpost

/**
 * Created by ndeveat on 2017. 9. 23..
 */

class DataCenter private constructor() {
    val SocialNetworkServices = HashMap<SocialNetworkType, Int>()

    private object Holder {
        val instance = DataCenter()
    }

    companion object {
        val instance: DataCenter by lazy { Holder.instance }
    }
}
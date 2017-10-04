package com.ndeveat.pinpost

import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel

/**
 * Created by ndeveat on 2017. 9. 23..
 */

// 로그인 관리
class Manager private constructor() {
    val SNSList = ArrayList<SocialNetworkModel>()

    private object Holder {
        val instance = Manager()
    }

    companion object {
        val instance: Manager by lazy { Holder.instance }
    }
}
package com.ndeveat.pinpost.Ui.Post

import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 20..
 */

// 데이터
// 발행한 SNS
data class PostPreviewModel(
        var id : Int,
        var title: String?,
        var text: String?,
        var images: ArrayList<String>?,
        var pushSns: ArrayList<SocialNetworkType>?)
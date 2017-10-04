package com.ndeveat.pinpost.Ui.Post

import com.ndeveat.pinpost.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 20..
 */

// 데이터
// 발행한 SNS
data class PostPreviewModel(var text: String, var pushSns: ArrayList<SocialNetworkType>)
package com.ndeveat.pinpost.Ui.Categories

import android.graphics.drawable.Drawable

/**
 * Created by ndeveat on 2017. 9. 20..
 */

data class SocialNetworkModel(
        var snsType: SocialNetworkType,
        var snsMainImage: Drawable,
        var snsPlusImage: Drawable,
        var snsMainColor: Int,
        var isLogin: Boolean,
        var email: String,
        var count: Int)
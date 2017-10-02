package com.ndeveat.pinpost.Categories

import android.graphics.drawable.Drawable
import com.ndeveat.pinpost.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 20..
 */

data class SNSModel(
        var snsType: SocialNetworkType,
        var snsMainImage: Drawable,
        var snsPlusImage: Drawable,
        var snsMainColor: Int,
        var isLogin: Boolean,
        var email: String,
        var count: Int)
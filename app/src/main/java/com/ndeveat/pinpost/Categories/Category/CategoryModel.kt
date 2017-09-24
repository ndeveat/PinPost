package com.ndeveat.pinpost.Categories.Category

import android.graphics.drawable.Drawable
import com.ndeveat.pinpost.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 20..
 */

data class CategoryModel(var snsType: SocialNetworkType, var drawable: Drawable, var background: Int)
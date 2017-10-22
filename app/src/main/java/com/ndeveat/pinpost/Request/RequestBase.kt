package com.ndeveat.pinpost.Request

import com.ndeveat.pinpost.Ui.Post.PostPreviewModel

/**
 * Created by ndeveat on 2017. 10. 22..
 */

interface RequestBase {
    fun request(post: RequestData)
}
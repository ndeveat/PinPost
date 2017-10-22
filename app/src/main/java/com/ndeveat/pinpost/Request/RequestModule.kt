package com.ndeveat.pinpost.Request

import android.content.Context

/**
 * Created by ndeveat on 2017. 10. 22..
 */

class RequestModule(val context: Context) {
    val facebook = RequestFacebook(context)
    val twitter = RequestTwitter(context)
}
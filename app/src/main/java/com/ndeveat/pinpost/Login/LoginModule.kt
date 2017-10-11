package com.ndeveat.pinpost.Login

import android.app.Activity

/**
 * Created by ndeveat on 2017. 10. 12..
 */

class LoginModule(activity: Activity) {
    val facebookLogin: FacebookLogin

    init {
        facebookLogin = FacebookLogin(activity)
    }
}
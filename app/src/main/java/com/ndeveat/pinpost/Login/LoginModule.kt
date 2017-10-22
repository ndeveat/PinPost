package com.ndeveat.pinpost.Login

import android.app.Activity
import android.content.Intent

/**
 * Created by ndeveat on 2017. 10. 12..
 */

class LoginModule(activity: Activity) {
    val facebookLogin: FacebookLogin
    val twitterLogin: TwitterLogin

    init {
        facebookLogin = FacebookLogin(activity)
        twitterLogin = TwitterLogin(activity)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookLogin.onActivityResult(requestCode, resultCode, data)
        twitterLogin.onActivityResult(requestCode, resultCode, data)
    }
}
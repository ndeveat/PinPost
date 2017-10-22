package com.ndeveat.pinpost.Login

import android.app.Activity
import android.content.Intent

/**
 * Created by ndeveat on 2017. 10. 12..
 */

class LoginModule(activity: Activity) {
    val loginFacebook: LoginFacebook
    val loginTwitter: LoginTwitter

    init {
        loginFacebook = LoginFacebook(activity)
        loginTwitter = LoginTwitter(activity)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loginFacebook.onActivityResult(requestCode, resultCode, data)
        loginTwitter.onActivityResult(requestCode, resultCode, data)
    }
}
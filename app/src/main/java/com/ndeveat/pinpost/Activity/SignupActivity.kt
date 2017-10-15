package com.ndeveat.pinpost.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*
import com.facebook.ProfileTracker
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Login.FacebookLogin
import com.ndeveat.pinpost.Manager

class SignupActivity : AppCompatActivity() {
    lateinit var facebookLogin: FacebookLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        facebookLogin()
        twitterLogin()
        googleLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookLogin.onActivityResult(requestCode, resultCode, data)
    }

    fun facebookLogin() {
        facebookLogin = FacebookLogin(this)
    }

    fun twitterLogin() {

    }

    fun googleLogin() {

    }

    fun getUserData() {
        facebookLogin.getData {
            Log.d("User Data", it.toString())
        }
    }
}

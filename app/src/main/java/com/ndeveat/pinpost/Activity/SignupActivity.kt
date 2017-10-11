package com.ndeveat.pinpost.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val callbackManager = CallbackManager.Factory.create()
        facebook_login.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.d("Facebook Login Result", result.toString())
            }

            override fun onCancel() {
                // TODO
            }

            override fun onError(error: FacebookException?) {
                error?.printStackTrace()
            }
        })

    }
}

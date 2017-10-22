package com.ndeveat.pinpost.Login

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.util.Log
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.TwitterAuthToken
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.services.AccountService
import android.R.attr.data
import com.twitter.sdk.android.core.Twitter


/**
 * Created by ndeveat on 2017. 10. 22..
 */

class TwitterLogin(val activity: Activity) : LoginBase {
    lateinit var twitter: Twitter
    lateinit var authClient: TwitterAuthClient

    init {
        val config = TwitterConfig.Builder(activity)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(
                        TwitterAuthConfig(
                                activity.getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                                activity.getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build()

        Twitter.initialize(config)

        authClient = TwitterAuthClient()
    }

    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login() {
        authClient.authorize(activity, object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val session = result!!.data
                val userName = session.userName
                Log.d("UserName", userName)

                authClient.requestEmail(session, object : Callback<String>() {
                    override fun success(result: Result<String>?) {
                        val email = result
                        if (email!!.data == null) {
                            Log.d("Email", "is Null")
                        } else {
                            val loginData = LoginData("Twitter", userName, email.data)
                            Log.d("Twitter Login", loginData.toString())
                            Manager.instance.setSnsData(activity, SocialNetworkType.Twitter, loginData)
                        }
                    }

                    override fun failure(exception: TwitterException?) {
                        exception!!.printStackTrace()
                    }
                })
            }

            override fun failure(exception: TwitterException?) {
                exception!!.printStackTrace()
            }
        })
    }

    override fun logout() {
        authClient.cancelAuthorize()

        Manager.instance.setSnsLogin(activity, SocialNetworkType.Twitter, false)
    }

    override fun getData(callback: (LoginData?) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        authClient.onActivityResult(requestCode, resultCode, data)
    }
}
package com.ndeveat.pinpost.Login

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*
import android.os.Bundle
import com.facebook.GraphRequest
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import kotlin.collections.ArrayList


/**
 * Created by ndeveat on 2017. 10. 11..
 */

class LoginFacebook(activity: Activity) : LoginBase {
    lateinit var callbackManager: CallbackManager
    val activity = activity

    init {
        init()
    }

    override fun init() {
        callbackManager = CallbackManager.Factory.create()
        // 로그이 콜백
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                getData { value ->
                    Log.d("Login SNS Facebook", value.toString())
                    if (value != null)
                        Manager.instance.setSnsData(activity, SocialNetworkType.Facebook, value)
                }
            }

            override fun onCancel() {
                Toast.makeText(activity, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException?) {
                error?.printStackTrace()
            }
        })
    }

    // TODO : 콜백 등록하기
    override fun getData(callback: (LoginData?) -> Unit) {
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null) {
            val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
                val name = `object`.getString("name")
                val email = `object`.getString("email")
                val loginData = LoginData("Facebook", name, email)

                callback.invoke(loginData)
            }

            val parameters = Bundle()
            parameters.putString("fields", "name,email")
            request.parameters = parameters
            request.executeAsync()
        } else {
            Log.d("Tag", "로그인을 하셔야지")
            callback.invoke(null)
        }
    }

    fun isLogin(): Boolean = if (AccessToken.getCurrentAccessToken() == null) false else true

    override fun login() {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().logInWithPublishPermissions(activity, Arrays.asList("publish_actions"))
    }

    override fun logout() {
        LoginManager.getInstance().logOut()
        Manager.instance.setSnsLogin(activity, SocialNetworkType.Facebook, false)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
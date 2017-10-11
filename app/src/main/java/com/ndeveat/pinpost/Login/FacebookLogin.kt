package com.ndeveat.pinpost.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*
import android.os.Bundle
import com.facebook.GraphResponse
import org.json.JSONObject
import com.facebook.GraphRequest
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Activity.MainActivity
import com.ndeveat.pinpost.Manager
import org.jetbrains.anko.intentFor


/**
 * Created by ndeveat on 2017. 10. 11..
 */

class FacebookLogin(activity: Activity) : LoginBase {
    lateinit var callbackManager: CallbackManager
    val activity = activity

    init {
        init()
    }

    override fun init() {
        callbackManager = CallbackManager.Factory.create()
        // 회원가입
        login()
    }

    // TODO : 콜백 등록하기
    override fun getData(callback: (LoginData?) -> Unit) {
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null) {
            val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
                Log.v("LoginActivity", response.toString())

                // Application code
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
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                // TODO : 있던 유저인지 체크한다
                // 유저를 등록한다.
                getData { value ->
                    if (value != null) {
                        Ion.with(activity).load(Manager.baseUrl + "/user/signup")
                                .setBodyParameter("sns_name", value.snsName)
                                .setBodyParameter("name", value.userName)
                                .setBodyParameter("email", value.userEmail)
                                .asJsonObject()
                                .setCallback { e, result ->
                                    if (result == null)
                                        e.printStackTrace()
                                    else {
                                        Log.d("Facebook Login callback", result.toString())
                                        // result : boolean
                                        // signup : boolean
                                        val res = result["result"].asBoolean
                                        val signup = result["signup"].asBoolean
                                        if (res && signup) {
                                            Toast.makeText(activity, "회원가입을 축하드립니다.", Toast.LENGTH_SHORT).show()
                                            mainActivity()
                                        } else if (res) {
                                            mainActivity()
                                        } else {
                                            Toast.makeText(activity, "오류가 발생하였습니다", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                }
                    }
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

    override fun logout() {
        LoginManager.getInstance().logOut()
    }

    fun mainActivity() {
        val intent = activity.intentFor<MainActivity>()
        activity.startActivity(intent)
        activity.finish()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
package com.ndeveat.pinpost

import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import android.R.id.edit
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE


/**
 * Created by ndeveat on 2017. 9. 23..
 */

// 로그인 관리
class Manager private constructor() {
    class User {
        var userName = ""
        var userId = ""
        var userEmail = ""
        var isLogin = false
    }

    val snsList = ArrayList<SocialNetworkModel>()
    val user = User()

    private object Holder {
        val instance = Manager()
    }

    fun getUserData(activity: Activity) {
        val sharedPref = activity.getSharedPreferences("User", Context.MODE_PRIVATE)
        user.isLogin = sharedPref.getBoolean("isLogin", false)
        user.userName = sharedPref.getString("userName", "")
        user.userEmail = sharedPref.getString("userEmail", "")
        user.userId = sharedPref.getString("userId", "")
    }

    fun setUserData(activity: Activity, user: User) {
        val sharedPref = activity.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putBoolean("isLogin", user.isLogin)
        editor.putString("userName", user.userName)
        editor.putString("userEmail", user.userEmail)
        editor.putString("userId", user.userId)
        editor.apply()
        editor.commit()

        getUserData(activity)
    }

    companion object {
        val instance: Manager by lazy { Holder.instance }

        val baseUrl = "http://45.76.205.51:3000"
        val signup = "/user/signup"
        val signin = "/user/signin"
        val postlist = "/post/postlist"
        val posting = "/post/posting"
        val media = "/media/"
    }
}
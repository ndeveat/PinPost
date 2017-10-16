package com.ndeveat.pinpost

import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel

/**
 * Created by ndeveat on 2017. 9. 23..
 */

// 로그인 관리
class Manager private constructor() {
    class User {
        var userName = ""
        var userId = ""
        var userEmail = ""
    }

    val snsList = ArrayList<SocialNetworkModel>()
    val user = User()

    private object Holder {
        val instance = Manager()
    }

    companion object {
        val instance: Manager by lazy { Holder.instance }

        val baseUrl = "http://45.76.205.51:3000"
        val signup = "/user/signup"
        val signin = "/user/signin"
        val postlist = "/post/postlist"
        val posting = "/post/posting"
    }
}
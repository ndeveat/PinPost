package com.ndeveat.pinpost

import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import android.app.Activity
import android.content.Context
import android.util.Log
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Login.LoginData
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.ndeveat.pinpost.Ui.Post.PostPreviewModel


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
    val posts = ArrayList<PostPreviewModel>()

    private object Holder {
        val instance = Manager()
    }

    fun getPost(context: Context, page: Int) {
        Ion.with(context)
                .load(Manager.baseUrl + Manager.postlist)
                .setBodyParameter("user_id", Manager.instance.user.userId)
                .setBodyParameter("page", page.toString())
                .asJsonObject()
                .setCallback { e, result ->
                    if (result != null) {
                        val posts = result["posts"].asJsonArray
                        posts.forEachIndexed { index, it ->
                            val post = it.asJsonObject
                            val postId = post["id"].asInt

                            if (this.posts.find { it.id == postId } == null) {
                                var images: ArrayList<String>? = ArrayList<String>()
                                val imageData = post["images"].asString
                                val imageList = imageData.split(',')
                                imageList.forEach {
                                    if (it != "")
                                        images?.add(it)
                                }
                                if (images!!.size == 0)
                                    images = null

                                val sns = ArrayList<SocialNetworkType>()
                                val snsData = post["sns"].asString
                                val snsList = snsData.split(',')
                                val socialNetworkList = SocialNetworkType.values()

                                snsList.forEach { value ->
                                    if (value != "") {
                                        val n = socialNetworkList.find { it.name == value }
                                        Log.d("N", n.toString())
                                        sns.add(n!!)
                                    }
                                }

                                var postTitle: String? = if (post["title"].asString != "") post["title"].asString else null
                                var postContents: String? = if (post["contents"].asString != "") post["contents"].asString else null

                                Log.d("Post", "${postId}, ${postTitle}, ${postContents}, ${images}, ${sns}")

                                this.posts.add(PostPreviewModel(
                                        postId,
                                        postTitle,
                                        postContents,
                                        images,
                                        sns
                                ));
                            }
                        }

                        this.posts.sortBy { -it.id }
                    } else {
                        e.printStackTrace()
                    }
                }
    }

    fun getPostCount(context: Context) {
        val url = "${Manager.baseUrl}${Manager.sns}${Manager.instance.user.userId}"
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback { e, result ->
                    if (result != null) {
                        if (result.has("sns")) {
                            val snsArray = result["sns"].asJsonArray
                            snsArray.forEach { sns ->
                                val snsData = sns.asJsonObject
                                Manager.instance.snsList.find { it.snsType == SocialNetworkType.valueOf(snsData["name"].asString) }!!.count = snsData["count"].asInt
                            }
                        }
                    } else {
                        e.printStackTrace()
                    }
                }
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

    fun setSnsData(activity: Activity, socialNetworkType: SocialNetworkType, loginData: LoginData) {
        Log.d("SNS Data" + socialNetworkType.toString(), loginData.toString())

        val sharedPref = activity.getSharedPreferences(socialNetworkType.toString(), Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", loginData.userEmail)
        editor.putString("name", loginData.userName)
        editor.putBoolean("isLogin", true)
        editor.apply()
        editor.commit()

        getSnsData(activity, socialNetworkType)
    }

    fun getSnsData(activity: Activity, socialNetworkType: SocialNetworkType): LoginData? {
        val sharedPref = activity.getSharedPreferences(socialNetworkType.toString(), Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        if (isLogin) {
            val email = sharedPref.getString("email", "")
            val name = sharedPref.getString("name", "")
            val loginData = LoginData(socialNetworkType.toString(), name, email)

            val sns = Manager.instance.snsList.find { it.snsType == socialNetworkType }
            sns!!.isLogin = isLogin
            sns.email = email

            return loginData
        }
        return null
    }

    companion object {
        val instance: Manager by lazy { Holder.instance }

        val baseUrl = "http://45.76.205.51:3000"
        val signup = "/user/signup"
        val signin = "/user/signin"
        val postlist = "/post/postlist"
        val posting = "/post/posting"
        val media = "/media/"
        val sns = "/sns/"
    }
}
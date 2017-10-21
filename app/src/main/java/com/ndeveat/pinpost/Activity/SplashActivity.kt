package com.ndeveat.pinpost.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import org.jetbrains.anko.intentFor
import android.util.Log
import com.ndeveat.pinpost.Login.LoginModule


/*
* 기초 처리하는 부분
*
* 처음 시작하는 사람
* 이전에 시작하던 사람
* */

class SplashActivity : Activity() {
    lateinit var loginModule: LoginModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 로컬 데이터 불러오기
        Manager.instance.getUserData(this@SplashActivity)

        loadSocialNetworkData()
        loginSocialNetworkData()

        Handler().postDelayed({
            if (!Manager.instance.user.isLogin)
                startActivity(intentFor<LoginActivity>())
            else {
                startActivity(intentFor<MainActivity>())
            }
            finish()
        }, 500)
    }

    /*
    * 기존의 SNS데이터를 수집함
    * */
    fun loadSocialNetworkData() {
        if (Manager.instance.snsList.size > 0)
            return

        Manager.instance.getPostCount(this)
        Manager.instance.getPost(this, 0)

        Manager.instance.snsList.add(
                SocialNetworkModel(
                        SocialNetworkType.Facebook,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_facebook_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_facebook_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsFacebook),
                        false,
                        "",
                        0))

        Manager.instance.snsList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tstory,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTstory),
                        false,
                        "",
                        0))

        Manager.instance.snsList.add(
                SocialNetworkModel(
                        SocialNetworkType.Twitter,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "",
                        0))

        Manager.instance.snsList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tumblr,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00008),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "",
                        0))

        Manager.instance.snsList.add(
                SocialNetworkModel(
                        SocialNetworkType.NaverBlog,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "",
                        0))
    }

    /*
    * 로그인된 SNS를 등록함
    * */
    fun loginSocialNetworkData() {
        // 서버에서 로그인된 데이터를 가져옴
        loginModule = LoginModule(this)

        SocialNetworkType.values().forEach { sns ->
            val data = Manager.instance.getSnsData(this@SplashActivity, sns)
            if (data != null) {
                val snsData = Manager.instance.snsList.find { it.snsType == sns }
                snsData!!.email = data.userEmail
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginModule.facebookLogin.onActivityResult(requestCode, resultCode, data)
    }
}
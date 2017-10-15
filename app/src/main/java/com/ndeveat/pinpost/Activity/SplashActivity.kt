package com.ndeveat.pinpost.Activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import org.jetbrains.anko.intentFor
import android.content.pm.PackageManager
import android.provider.SyncStateContract.Helpers.update
import android.content.pm.PackageInfo
import android.hardware.camera2.params.Face
import android.util.Base64
import android.util.Log
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Login.FacebookLogin
import com.ndeveat.pinpost.Login.LoginModule
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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

        // load SNS
        loadSocialNetworkData()
        // login SNS
        loginSocialNetworkData()


        // 로그인된 회원정보 가져오기
        if (loginModule.facebookLogin.isLogin()) {
            loginModule.facebookLogin.getData {
                if (it != null) {
                    Ion.with(this@SplashActivity)
                            .load(Manager.baseUrl + Manager.getUser)
                            .setBodyParameter("email", it.userEmail)
                            .setBodyParameter("sns", it.snsName)
                            .asJsonObject()
                            .setCallback { e, result ->
                                Log.d("User ", result.toString())
                            }
                }
            }
        }

        // TODO
        // 회원 가입 및 로그인 추가
        // 회원가입이 되어있다면 넘어간다.
        // 앱의 로컬 데이터 베이스에 정보들을 저장한다.
        if (!loginModule.facebookLogin.isLogin())
            startActivity(intentFor<LoginActivity>())
        else
            startActivity(intentFor<MainActivity>())

        finish()
    }

    /*
    * 기존의 SNS데이터를 수집함
    * */
    fun loadSocialNetworkData() {
        if (Manager.instance.SNSList.size > 0)
            return

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Facebook,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_facebook_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_facebook_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsFacebook),
                        false,
                        "ndeveat@gmail.com",
                        0))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tstory,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTstory),
                        false,
                        "ndeveat@gmail.com",
                        0))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Twitter,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        0))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tumblr,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00008),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        0))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.NaverBlog,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        0))
    }

    /*
    * 로그인된 SNS를 등록함
    * */
    fun loginSocialNetworkData() {
        // 서버에서 로그인된 데이터를 가져옴
        loginModule = LoginModule(this)
        if (loginModule.facebookLogin.isLogin()) {
            Log.d("SNS Login", "facebook logined")
            Manager.instance.SNSList.find { it.snsType == SocialNetworkType.Facebook }!!.isLogin = true
        }
    }
}
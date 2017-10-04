package com.ndeveat.pinpost.Activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.SocialNetworkType
import org.jetbrains.anko.intentFor

/*
* 기초 처리하는 부분
*
* 처음 시작하는 사람
* 이전에 시작하던 사람
* */

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // add category
        addSocialNetworkData()
        updateSocialNetworkData()

        // TODO
        // 회원 가입 및 로그인 추가
        // 회원가입이 되어있다면 넘어간다.
        // 앱의 로컬 데이터 베이스에 정보들을 저장한다.
        startActivity(intentFor<MainActivity>())

        finish()
    }

    fun addSocialNetworkData() {
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
                        5))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tstory,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tstory_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTstory),
                        false,
                        "ndeveat@gmail.com",
                        2))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Twitter,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_twitter_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        2))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.Tumblr,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_tumblr_00008),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        2))

        Manager.instance.SNSList.add(
                SocialNetworkModel(
                        SocialNetworkType.NaverBlog,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00001),
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.sns_naverblog_00007),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter),
                        false,
                        "ndeveat@gmail.com",
                        2))
    }

    fun updateSocialNetworkData() {
        for (data in Manager.instance.SNSList) {

        }
    }
}
package com.ndeveat.pinpost.Activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.ndeveat.pinpost.Categories.Category.CategoryModel
import com.ndeveat.pinpost.DataCenter
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.SocialNetworkType
import org.jetbrains.anko.intentFor
import kotlin.concurrent.thread

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

        // add social network
        DataCenter.instance.SocialNetworkServices.put(SocialNetworkType.Facebook, 1)
        DataCenter.instance.SocialNetworkServices.put(SocialNetworkType.Tstory, 5)
        DataCenter.instance.SocialNetworkServices.put(SocialNetworkType.Twitter, 5)

        // add category
        addSocialNetworkCount()

        startActivity(intentFor<MainActivity>())

        finish()
    }

    fun addSocialNetworkCount() {
        DataCenter.instance.Categories.add(
                CategoryModel(
                        SocialNetworkType.Facebook,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.facebook00001),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsFacebook)))
        DataCenter.instance.Categories.add(
                CategoryModel(
                        SocialNetworkType.Tstory,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.tstory00001),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTstory)))
        DataCenter.instance.Categories.add(
                CategoryModel(
                        SocialNetworkType.Twitter,
                        ContextCompat.getDrawable(this@SplashActivity, R.drawable.twitter00001),
                        ContextCompat.getColor(this@SplashActivity, R.color.snsTwitter)))
    }
}
package com.ndeveat.pinpost.Activity

import android.app.Activity
import android.os.Bundle
import com.ndeveat.pinpost.R
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

        startActivity(intentFor<MainActivity>())

        finish()
    }
}
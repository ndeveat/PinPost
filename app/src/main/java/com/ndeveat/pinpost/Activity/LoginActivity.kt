package com.ndeveat.pinpost.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Login.LoginFacebook
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

class LoginActivity : AppCompatActivity() {
    lateinit var loginFacebook: LoginFacebook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 로그인
        login_signin.setOnClickListener {
            startActivity(intentFor<SigninActivity>())
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginFacebook.onActivityResult(requestCode, resultCode, data)
    }
}
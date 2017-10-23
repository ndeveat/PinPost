package com.ndeveat.pinpost.Activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo

import java.util.ArrayList

import com.ndeveat.pinpost.R

import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.*
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Manager
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.intentFor

/**
 * A login screen that offers login via email/password.
 */
class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // 회원가입
        login_signup.setOnClickListener {
            startActivity(intentFor<SignupActivity>())
        }

        signin_button.setOnClickListener {
            signin_process.visibility = View.GONE
            signin_form.visibility = View.VISIBLE

            val email = signin_email.text.toString()
            val password = signin_password.text.toString()

            if (isEmailValidation()) {
                Ion.with(this@SigninActivity)
                        .load(Manager.baseUrl + Manager.signin)
                        .setBodyParameter("email", email)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback { e, result ->
                            if (result != null) {
                                val loginResult = result["result"].asBoolean
                                if (loginResult) {
                                    val user = result["user"].asJsonObject
                                    val mUser = Manager.User()
                                    mUser.isLogin = true
                                    mUser.userEmail = user["email"].asString
                                    mUser.userId = user["id"].asString
                                    mUser.userName = user["name"].asString

                                    Manager.instance.setUserData(this@SigninActivity, mUser)

                                    val intent = intentFor<MainActivity>()
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)

                                    finish()
                                } else {
                                    Toast.makeText(this@SigninActivity, "이메일 혹은 비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                e.printStackTrace()
                            }

                            signin_form.visibility = View.VISIBLE
                            signin_process.visibility = View.GONE
                        }
            }
        }
    }

    // Email validation check
    fun isEmailValidation(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(signin_email.text).matches()
    }
}


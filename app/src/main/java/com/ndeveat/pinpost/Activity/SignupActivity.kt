package com.ndeveat.pinpost.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ndeveat.pinpost.R
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Manager
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.intentFor

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signup_button.setOnClickListener {
            if (isEmailValidationn()) {
                // 회원가입 Progress 실행
                signup_progress.visibility = View.VISIBLE
                signup_form.visibility = View.GONE

                val email = signup_email.text.toString()
                val name = signup_username.text.toString()
                val password = signup_password.text.toString()

                Ion.with(this@SignupActivity)
                        .load(Manager.baseUrl + Manager.signup)
                        .setBodyParameter("name", name)
                        .setBodyParameter("email", email)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback { e, result ->
                            if (result != null) {
                                Log.d("Signup Result", result.toString())

                                val user = result["user"].asJsonObject
                                val mUser = Manager.User()
                                mUser.isLogin = true
                                mUser.userEmail = user["email"].asString
                                mUser.userId = user["id"].asString
                                mUser.userName = user["name"].asString

                                Manager.instance.setUserData(this@SignupActivity, mUser)

                                val intent = intentFor<MainActivity>()
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            } else {
                                e.printStackTrace()
                            }
                            signup_progress.visibility = View.GONE
                            signup_form.visibility = View.VISIBLE
                        }
            } else {
                Toast.makeText(this@SignupActivity, "이메일 형식을 지켜주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isEmailValidationn(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(signup_email.text).matches()
    }
}


package com.ndeveat.pinpost.Login

import android.app.Activity

/**
 * Created by ndeveat on 2017. 10. 11..
 */

interface LoginBase {
    fun init()
    fun login()
    fun logout()
    fun getData(callback: (LoginData?) -> Unit)
}
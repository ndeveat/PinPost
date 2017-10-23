package com.ndeveat.pinpost.Ui

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by ndeveat on 2017. 10. 23..
 */

class EditNotification : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
    }
}
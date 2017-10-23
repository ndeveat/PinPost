package com.ndeveat.pinpost.Ui

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Request.RequestModule

/**
 * Created by ndeveat on 2017. 10. 23..
 * 포스트 삭제하기
 */

class RemoveNotification : Service() {
    lateinit var notifyManager: NotificationManager
    lateinit var notification: Notification.Builder

    lateinit var requestModule: RequestModule

    override fun onCreate() {
        super.onCreate()

        requestModule = RequestModule(applicationContext)
        notifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        notification(intent!!)
        removeServer(intent)
        return START_STICKY
    }

    fun notification(intent: Intent) {
        val contentIntent = PendingIntent.getActivity(this@RemoveNotification, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notification = Notification.Builder(this@RemoveNotification)
        notification.setContentTitle("포스트 삭제")
                .setSmallIcon(R.drawable.icon_pencil_00001)
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            notification.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }
        notifyManager.notify(1235, notification.build())
    }

    fun updateManagerData() {
        Manager.instance.getPostCount(applicationContext)
    }

    fun removeServer(intent: Intent) {
        val postId = intent.extras.getInt("post_id", -1)

        Ion.with(applicationContext).load(Manager.baseUrl + Manager.postremove)
                .setBodyParameter("user_id", Manager.instance.user.userId)
                .setBodyParameter("post_id", postId.toString())
                .asJsonObject()
                .setCallback { e, result ->
                    if (result == null)
                        e.printStackTrace()

                    updateManagerData()
                    stopService(intent)
                }
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()

        notifyManager.cancel(1235)
    }
}
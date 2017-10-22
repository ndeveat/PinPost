package com.ndeveat.pinpost.Ui

import android.app.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.facebook.FacebookSdk
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Activity.MainActivity
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Request.RequestData
import com.ndeveat.pinpost.Request.RequestModule
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.ndeveat.pinpost.Utils.RealPathUtil
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.intentFor
import org.json.JSONObject
import java.io.File

/**
 * Created by ndeveat on 2017. 10. 20..
 */

class PushNotification : Service() {

    lateinit var notifyManager: NotificationManager
    lateinit var notification: Notification.Builder

    lateinit var requestModule : RequestModule

    override fun onBind(p0: Intent?): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        notifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        requestModule = RequestModule(applicationContext)

        notification(intent!!)
        uploadServer(intent)

        return START_STICKY
    }

    fun notification(intent: Intent) {
        val contentIntent = PendingIntent.getActivity(this@PushNotification, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notification = Notification.Builder(this@PushNotification)
        notification.setContentTitle("제목")
                .setContentText("내용")
                .setSmallIcon(R.drawable.icon_pencil_00001)
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())
                .setProgress(100, 0, true)
                .setOngoing(true)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            notification.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }
        notifyManager.notify(1234, notification.build())
    }

    fun uploadServer(intent: Intent) {
        val title = intent.extras.getString("title")
        val contents = intent.extras.getString("contents")
        val images = intent.extras.getParcelableArrayList<Uri>("images")
        val sns = intent.extras.getStringArrayList("sns")

        val requestData = RequestData(title, contents, images, sns)

        val snsData = JSONObject()
        var snsString = ""
        sns.forEach { snsString += it + "," }
        snsString.dropLast(1)
        snsData.put("sns", snsString)

        val ion = Ion.with(this@PushNotification).load(Manager.baseUrl + Manager.posting).setTimeout(1000 * 6)
        ion.setMultipartParameter("title", title)
                .setMultipartParameter("contents", contents)
                .setMultipartParameter("user_id", Manager.instance.user.userId)
                .setMultipartParameter("sns", snsData.toString())

        images!!.forEachIndexed { index, uri ->
            val imagePath = RealPathUtil.getRealPath(this@PushNotification, uri)
            val file = File(imagePath)
            ion.setMultipartFile("image{$index}", file)
        }
        ion.uploadProgress { downloaded, total ->
            Log.d("Progress", downloaded.toString())
            notification.setProgress(total.toInt(), downloaded.toInt(), false)
            notifyManager.notify(1234, notification.build())
        }.asJsonObject().setCallback { e, result ->
            if (result != null) {
                val success = result["result"].asBoolean
                if (success) {
                    Log.d("Result", result.toString())

                    // Manager Data 업데이트
                    updateManagerData()
                    // 등록한 SNS에 포스트 올리기
                    snsRequest(sns, requestData)
                } else {

                }

                stopService(intent)
            } else {
                e.printStackTrace()
            }
        }
    }

    fun updateManagerData() {
        Manager.instance.getPost(applicationContext, 0)
        Manager.instance.getPostCount(applicationContext)
    }

    fun snsRequest(sns: ArrayList<String>, requestData: RequestData) {
        sns.forEach {
            val snsType = SocialNetworkType.valueOf(it)
            when (snsType) {
                SocialNetworkType.Facebook -> {
                    // Facebook posting
                    requestModule.facebook.request(requestData)
                }
                SocialNetworkType.Twitter -> {
                    requestModule.twitter.request(requestData)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        notifyManager.cancel(1234)
    }
}
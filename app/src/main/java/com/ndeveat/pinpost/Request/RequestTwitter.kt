package com.ndeveat.pinpost.Request

import android.content.Context
import com.twitter.sdk.android.tweetcomposer.ComposerActivity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.util.Log
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Utils.RealPathUtil
import com.twitter.sdk.android.core.TwitterCore
import twitter4j.StatusUpdate
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import java.io.File


/**
 * Created by ndeveat on 2017. 10. 22..
 */

class RequestTwitter(val context: Context) : RequestBase {
    override fun request(post: RequestData) {
        Thread {
            try {
                val accesstoken = AccessToken(
                        "921944923770724352-uVTyfMPfPZ4LgdT0nDDDr6GQ6rNIZjx",
                        "Hccit9whs1npFhCw1qksMYiehWoKdEkhTTzfa4KvCnFu6")
                val twitter = TwitterFactory.getSingleton()
                twitter.setOAuthConsumer(
                        context.getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        context.getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET))
                twitter.oAuthAccessToken = accesstoken
                // val user = twitter.verifyCredentials()
                val statusUpdate = StatusUpdate(post.contents)
                val mediaIds = ArrayList<Long>()
                post.images?.forEach {
                    val file = File(RealPathUtil.getRealPath(context, it))
                    val media = twitter.uploadMedia(file)
                    mediaIds.add(media.mediaId)
                }
                statusUpdate.setMediaIds(mediaIds.toLongArray())

                // posting twitter
                twitter.updateStatus(statusUpdate)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}
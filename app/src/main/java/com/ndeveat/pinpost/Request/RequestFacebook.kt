package com.ndeveat.pinpost.Request

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.share.Share
import com.facebook.share.ShareApi
import com.facebook.share.Sharer
import com.facebook.share.model.ShareMediaContent
import com.facebook.share.model.SharePhoto
import com.ndeveat.pinpost.Ui.Post.PostPreviewModel
import com.facebook.GraphResponse
import com.facebook.GraphRequest
import retrofit2.http.GET
import com.facebook.AccessToken.getCurrentAccessToken
import com.facebook.GraphRequest.TAG
import retrofit2.http.POST
import com.facebook.AccessToken.getCurrentAccessToken
import com.ndeveat.pinpost.Utils.RealPathUtil
import android.R.attr.data
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import android.R.attr.data
import android.R.attr.bitmap


/**
 * Created by ndeveat on 2017. 10. 22..
 */

class RequestFacebook(val context: Context) : RequestBase {
    override fun request(post: RequestData) {
        if (AccessToken.getCurrentAccessToken() != null) {
            Log.d("AccessToken", "장전완료")


        }
    }
}
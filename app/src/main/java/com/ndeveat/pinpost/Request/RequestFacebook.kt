package com.ndeveat.pinpost.Request

import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.share.Share
import com.facebook.share.ShareApi
import com.facebook.share.Sharer
import com.facebook.share.model.ShareMediaContent
import com.facebook.share.model.SharePhoto
import com.ndeveat.pinpost.Ui.Post.PostPreviewModel

/**
 * Created by ndeveat on 2017. 10. 22..
 */

class RequestFacebook : RequestBase {
    override fun request(post: RequestData) {
        if (AccessToken.getCurrentAccessToken() != null) {
            Log.d("AccessToken", "장전완료")

            val params = Bundle()
            params.putString("message", "PinPost 테스트 중");

            GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/feed",
                    params,
                    HttpMethod.POST,
                    object : GraphRequest.Callback {
                        override fun onCompleted(response: GraphResponse?) {
                            Log.d("Share Complated", response.toString())
                        }
                    }
            ).executeAsync()
        }
    }
}
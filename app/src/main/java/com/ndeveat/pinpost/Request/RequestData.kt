package com.ndeveat.pinpost.Request

import android.net.Uri

/**
 * Created by ndeveat on 2017. 10. 22..
 */

data class RequestData(var title: String?, var contents: String?, var images: ArrayList<Uri>?, var sns: ArrayList<String>?)
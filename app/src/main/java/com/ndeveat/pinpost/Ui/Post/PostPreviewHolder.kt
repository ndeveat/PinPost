package com.ndeveat.pinpost.Ui.Post

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.SocialNetworkType
import com.ndeveat.pinpost.Ui.View.TextView
import kotlinx.android.synthetic.main.post_contents_image.view.*
import kotlinx.android.synthetic.main.post_contents_text.view.*
import kotlinx.android.synthetic.main.post_preview.view.*
import kotlinx.android.synthetic.main.push_sns_icon.view.*

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class PostPreviewHolder : RecyclerView.ViewHolder {
    var text: TextView? = null
    var pushSnsContainer: LinearLayout? = null
    var imageContainer: LinearLayout? = null

    var context: Context? = null

    constructor(view: View) : super(view) {
        text = view.post_preview_text
        pushSnsContainer = view.post_preview_push_sns_container
        imageContainer = view.post_preview_image_container

        this.context = view.context
    }

    fun setText(text: String) {
        this.text?.text = text
    }

    fun setPushSns(snsList: ArrayList<SocialNetworkType>) {
        if (pushSnsContainer != null) {
            for (sns in snsList) {
                val drawable = Manager.instance.SNSList.filter { it.snsType == sns }[0].snsPlusImage
                val view = LayoutInflater.from(this.context).inflate(R.layout.push_sns_icon, pushSnsContainer, false)
                view.push_sns_icon.setImageDrawable(drawable)

                pushSnsContainer!!.addView(view)
            }
        }
    }

    fun setImages(imageList: ArrayList<String>) {
        if (imageContainer != null) {
            for (image in imageList) {
                val view = LayoutInflater.from(context).inflate(R.layout.post_contents_image, imageContainer, false)
                val imageView = view.image

                Log.d("ImageUrl", image)
                Glide.with(context).load(image).into(imageView)

                imageContainer!!.addView(view)
            }
        }
    }
}
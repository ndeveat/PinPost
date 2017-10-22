package com.ndeveat.pinpost.Ui.Post

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.ndeveat.pinpost.Ui.View.PostImageViewer
import com.ndeveat.pinpost.Ui.View.TextView
import kotlinx.android.synthetic.main.post_preview.view.*
import kotlinx.android.synthetic.main.push_sns_icon.view.*

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class PostPreviewHolder : RecyclerView.ViewHolder {
    var title: TextView? = null
    var text: TextView? = null
    var pushSnsContainer: LinearLayout? = null

    var context: Context? = null
    var imageContent: PostImageViewer? = null

    constructor(view: View, context: Context) : super(view) {
        title = view.post_title
        text = view.post_preview_text
        pushSnsContainer = view.post_preview_push_sns_container
        imageContent = view.post_contents_image
        this.context = context
    }

    fun setTitle(title: String?) {
        if (title != null) {
            this.title!!.text = title
            this.title!!.visibility = View.VISIBLE
        } else {
            this.title!!.visibility = View.GONE
        }
    }

    fun setText(text: String?) {
        if (text != null)
            this.text!!.text = text
        else {
            if (title != null) {
                this.text!!.visibility = View.GONE
            } else {
                this.text!!.text = ""
                this.text!!.visibility = View.VISIBLE
            }
        }
    }

    fun setPushSns(snsList: ArrayList<SocialNetworkType>?) {
        pushSnsContainer!!.removeAllViews()
        if (pushSnsContainer != null && snsList != null) {
            for (sns in snsList) {
                val drawable = Manager.instance.snsList.filter { it.snsType == sns }[0].snsPlusImage
                val view = LayoutInflater.from(this.context).inflate(R.layout.push_sns_icon, pushSnsContainer, false)
                view.push_sns_icon.setImageDrawable(drawable)

                pushSnsContainer!!.addView(view)
            }
        }
    }

    fun setImages(imageList: ArrayList<String>?) {
        if (imageList != null) {
            imageContent!!.addImage(imageList)
            imageContent!!.visibility = View.VISIBLE
        } else {
            imageContent!!.visibility = View.GONE
        }
    }
}
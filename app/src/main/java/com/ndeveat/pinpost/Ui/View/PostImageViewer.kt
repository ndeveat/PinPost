package com.ndeveat.pinpost.Ui.View

import android.content.Context
import android.graphics.Typeface
import android.media.Image
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_contents_image.view.*

/**
 * Created by ndeveat on 2017. 10. 5..
 */

class PostImageViewer : LinearLayout {
    var imageCount = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    fun init() {
        val inflate = LayoutInflater.from(context)
        val view = inflate.inflate(R.layout.post_contents_image, null)
        addView(view)

        container = view.image_container
        subContainer = view.sub_image_container

        mainImage = view.main_image
        subImage = view.sub_image
        plusImage = view.plus_image
        plusImageParent = view.plus_image_parent
        plusImageOver = view.plus_image_over
        plusImageText = view.plus_image_over_text
    }

    var container: LinearLayout? = null
    var subContainer: LinearLayout? = null

    var mainImage: ImageView? = null
    var subImage: ImageView? = null

    var plusImage: ImageView? = null
    var plusImageParent: FrameLayout? = null
    var plusImageOver: FrameLayout? = null
    var plusImageText: TextView? = null

    fun addImage(images: ArrayList<String>) {
        imageCount = 0
        plusImageOver!!.visibility = View.GONE

        // 이미지 개수
        if (images.size == 1) {
            // Weight Sum
            // View visible
            subContainer!!.visibility = View.GONE
            subImage!!.visibility = View.GONE
            plusImageParent!!.visibility = View.GONE
        } else if (images.size == 2) {
            // Weight Sum
            //subContainer!!.layoutParams = LinearLayout.LayoutParams(subContainer!!.layoutParams.width, subContainer!!.layoutParams.height, 1f)
            // View visible
            subContainer!!.visibility = View.VISIBLE
            subImage!!.visibility = View.VISIBLE
            plusImageParent!!.visibility = View.GONE
        } else if (images.size >= 3) {
            // Weight Sum
            //subContainer!!.layoutParams = LinearLayout.LayoutParams(subContainer!!.layoutParams.width, subContainer!!.layoutParams.height, 2f)
            // View visible
            subContainer!!.visibility = View.VISIBLE
            subImage!!.visibility = View.VISIBLE
            plusImageParent!!.visibility = View.VISIBLE
        }
        images.forEachIndexed { index, s ->
            val url = Manager.baseUrl + Manager.media + s
            Ion.with(context)
                    .load(url)
                    .asJsonObject()
                    .setCallback { e, result ->
                        if (result != null) {
                            if (!result["media"].isJsonNull) {
                                val imageData = result["media"].asJsonObject
                                val imageUrl = Manager.baseUrl + "/images/" + imageData["id"].asString + "." + imageData["type"].asString
                                when (index) {
                                    0 -> {
                                        Picasso.with(context)
                                                .load(imageUrl)
                                                .placeholder(R.drawable.image_placeholder)
                                                .transform(CompressionBitmap(1280, 720))
                                                .into(mainImage)
                                    }
                                    1 -> {
                                        Picasso.with(context)
                                                .load(imageUrl)
                                                .placeholder(R.drawable.image_placeholder)
                                                .transform(CompressionBitmap(1280, 720))
                                                .into(subImage)
                                    }
                                    2 -> {
                                        Picasso.with(context)
                                                .load(imageUrl)
                                                .placeholder(R.drawable.image_placeholder)
                                                .transform(CompressionBitmap(1280, 720))
                                                .into(plusImage)
                                    }
                                // 이상일 시
                                    else -> {
                                        plusImageOver!!.visibility = View.VISIBLE
                                        plusImageText!!.text = "+${(index - 2)}"
                                    }
                                }
                            }
                        } else {
                            e.printStackTrace()
                        }
                    }
        }
    }
}
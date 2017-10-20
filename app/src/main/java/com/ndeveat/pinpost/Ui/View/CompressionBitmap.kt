package com.ndeveat.pinpost.Ui.View

import android.graphics.Bitmap
import com.squareup.picasso.Transformation


/**
 * Created by ndeveat on 2017. 10. 17..
 * 이미지 압축하기
 */
class CompressionBitmap(private val maxWidth: Int, private val maxHeight: Int) : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val targetWidth: Int
        val targetHeight: Int
        val aspectRatio: Double

        if (source.width > source.height) {
            targetWidth = maxWidth
            aspectRatio = source.height.toDouble() / source.width.toDouble()
            targetHeight = (targetWidth * aspectRatio).toInt()
        } else {
            targetHeight = maxHeight
            aspectRatio = source.width.toDouble() / source.height.toDouble()
            targetWidth = (targetHeight * aspectRatio).toInt()
        }

        val result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
        if (result != source) {
            source.recycle()
        }
        return result
    }

    override fun key(): String = maxWidth.toString() + "x" + maxHeight;
}
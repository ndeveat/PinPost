package com.ndeveat.pinpost.Ui.Post

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.R

class PostPreviewAdapter : RecyclerView.Adapter<PostPreviewHolder>() {
    class PostPreviewDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.top = 0
        }
    }

    var mPosts: ArrayList<PostPreviewModel>
    var mHolderCount = 0

    init {
        mPosts = ArrayList<PostPreviewModel>()
    }

    override fun getItemCount(): Int = mPosts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostPreviewHolder {
        //val view = if (mHolderCount == 0)
        //    LayoutInflater.from(parent!!.context).inflate(R.layout.post_preview_top, parent, false)
        //else
        //    LayoutInflater.from(parent!!.context).inflate(R.layout.post_preview, parent, false)

        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.post_preview, parent, false)
        val holder = PostPreviewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PostPreviewHolder?, position: Int) {
        val post = mPosts[position]

        // PostView Setting
        holder!!.setTitle(post.title)
        holder.setText(post.text)
        holder.setPushSns(post.pushSns)
        holder.setImages(post.images)
    }
}
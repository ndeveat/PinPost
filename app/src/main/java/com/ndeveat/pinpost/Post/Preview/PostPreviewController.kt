package com.ndeveat.pinpost.Post.Preview

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class PostPreviewController : RecyclerView.Adapter<PostPreviewView>() {
    class PostPreviewDecoration : RecyclerView.ItemDecoration {
        constructor() : super() {

        }

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.top = 0
        }
    }

    var mPosts: ArrayList<PostPreviewModel>

    init {
        mPosts = ArrayList<PostPreviewModel>()
    }

    override fun getItemCount(): Int = mPosts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostPreviewView {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.post_preview, parent, false)
        val holder = PostPreviewView(view)
        return holder
    }

    override fun onBindViewHolder(holder: PostPreviewView?, position: Int) {
        val post = mPosts[position]
    }
}
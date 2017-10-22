package com.ndeveat.pinpost.Ui.Post

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R

class PostPreviewAdapter : RecyclerView.Adapter<PostPreviewHolder>() {
    class PostPreviewDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect!!.top = 0
        }
    }

    var mPosts: ArrayList<PostPreviewModel>

    init {
        mPosts = ArrayList<PostPreviewModel>(Manager.instance.posts)
    }

    fun lastPost(): Boolean {
        if (Manager.instance.posts.size > 0) {
            val post = Manager.instance.posts[0]
            val nextPost = mPosts.find { it.id == post.id }
            if (nextPost == null) {
                Log.d("LastPost", post.toString())
                mPosts.add(0, post)
                notifyItemInserted(0)
                return true
            }
        }
        return false
    }

    override fun getItemCount(): Int = mPosts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostPreviewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.post_preview, parent, false)
        val holder = PostPreviewHolder(view, parent.context)
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
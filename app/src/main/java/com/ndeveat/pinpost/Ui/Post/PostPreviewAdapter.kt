package com.ndeveat.pinpost.Ui.Post

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.support.annotation.Nullable
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Activity.EditorActivity
import com.ndeveat.pinpost.Activity.LoginActivity
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.R.id.contents
import com.ndeveat.pinpost.Ui.PushNotification
import com.ndeveat.pinpost.Ui.RemoveNotification
import org.jetbrains.anko.intentFor

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
        // 포스트 옵션 생성
        holder.option?.setOnClickListener {
            val dialog = AlertDialog.Builder(holder.context!!)
            dialog.setTitle("옵션")
            dialog.setItems(arrayOf("수정", "삭제"),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        // 수정
                        if (i == 0) {
                            val intent = holder.context!!.intentFor<EditorActivity>()
                            intent.putExtra("Type", "Edit")
                            holder.context!!.startActivity(intent)
                        }
                        // 삭제
                        else {
                            // 포스트 리스트에서 삭제
                            if (mPosts.contains(post))
                                mPosts.remove(post)
                            if (Manager.instance.posts.contains(post))
                                Manager.instance.posts.remove(post)
                            // 아이템 변경 콜백
                            this.notifyItemRemoved(position)
                            // 데이터 베이스에서 제거하기
                            removeNotification(holder.context!!, post)
                        }
                    })
            dialog.show()
        }
    }

    fun removeNotification(context: Context, post: PostPreviewModel) {
        val intent = context.intentFor<RemoveNotification>()
        intent.putExtra("post_id", post.id)
        intent.putExtra("user_id", Manager.instance.user.userId)

        context.startService(intent)
    }
}
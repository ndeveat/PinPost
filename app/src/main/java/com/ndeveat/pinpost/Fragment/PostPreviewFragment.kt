package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Post.Preview.PostPreviewController
import com.ndeveat.pinpost.Post.Preview.PostPreviewModel
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.fragment_postlist.view.*

/**
 * Created by ndeveat on 2017. 9. 18..
 */

class PostPreviewFragment : Fragment() {
    var mLayoutManager: LinearLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mPostViewAdapter: PostPreviewController? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_postlist, container, false)

        mPostViewAdapter = PostPreviewController()
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView = rootView.post_preview_list
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mPostViewAdapter
        mRecyclerView!!.addItemDecoration(PostPreviewController.PostPreviewDecoration())

        for (i in 0..9)
            mPostViewAdapter!!.mPosts.add(PostPreviewModel("yeah"))

        return rootView
    }

    companion object {
        fun newInstance(): PostPreviewFragment {
            val fragment = PostPreviewFragment()
            return fragment
        }
    }
}
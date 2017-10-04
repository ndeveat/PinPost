package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Post.PostPreviewAdapter
import com.ndeveat.pinpost.Ui.Post.PostPreviewModel
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.SocialNetworkType
import kotlinx.android.synthetic.main.fragment_postlist.view.*

/**
 * Created by ndeveat on 2017. 9. 18..
 */

class PostviewFragment : Fragment() {
    var mLayoutManager: LinearLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mPostViewAdapter: PostPreviewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_postlist, container, false)

        mPostViewAdapter = PostPreviewAdapter()
        mLayoutManager = LinearLayoutManager(context)
        mRecyclerView = rootView.post_preview_list
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mPostViewAdapter
        mRecyclerView!!.addItemDecoration(PostPreviewAdapter.PostPreviewDecoration())


        var pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Facebook, SocialNetworkType.Tstory, SocialNetworkType.Tumblr)
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("yeah", pushSocialDatas))
        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Twitter, SocialNetworkType.NaverBlog, SocialNetworkType.Tumblr)
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("yeah", pushSocialDatas))
        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.NaverBlog, SocialNetworkType.Tstory, SocialNetworkType.Facebook)
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("yeah", pushSocialDatas))
        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Tumblr, SocialNetworkType.Tstory, SocialNetworkType.Twitter)
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("yeah", pushSocialDatas))

        return rootView
    }

    companion object {
        fun newInstance(): PostviewFragment {
            val fragment = PostviewFragment()
            return fragment
        }
    }
}
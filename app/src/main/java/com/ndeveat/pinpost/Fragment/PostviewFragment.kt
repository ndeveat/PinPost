package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Post.PostPreviewAdapter
import com.ndeveat.pinpost.Ui.Post.PostPreviewModel
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import kotlinx.android.synthetic.main.fragment_postlist.view.*
import kotlin.concurrent.thread

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
        var images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/013.jpg", "http://junsueg5737.dothome.co.kr/images/02.jpg")

        mPostViewAdapter!!.mPosts.add(PostPreviewModel(null, null, null, null))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트1", null, null, null))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트2", "테스트2 내용", null, null))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트3", "테스트3 내용", images, null))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트4", "테스트4 내용", null, pushSocialDatas))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트5", "테스트5 내용", images, pushSocialDatas))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel(null, "테스트6 내용", images, pushSocialDatas))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트7", null, images, pushSocialDatas))
        mPostViewAdapter!!.mPosts.add(PostPreviewModel(null, null, images, pushSocialDatas))

        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Facebook, SocialNetworkType.Tstory, SocialNetworkType.Tumblr)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/013.jpg", "http://junsueg5737.dothome.co.kr/images/02.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트8", "테스트8 내용", images, pushSocialDatas))

        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Tstory, SocialNetworkType.Twitter, SocialNetworkType.NaverBlog)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/010.jpg", "http://junsueg5737.dothome.co.kr/images/08.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트9", "테스트9 내용", images, pushSocialDatas))

        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.NaverBlog, SocialNetworkType.Tumblr, SocialNetworkType.Twitter)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/016.jpg", "http://junsueg5737.dothome.co.kr/images/07.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트10", "테스트10 내용", images, pushSocialDatas))

        return rootView
    }

    companion object {
        fun newInstance(): PostviewFragment {
            val fragment = PostviewFragment()
            return fragment
        }
    }
}
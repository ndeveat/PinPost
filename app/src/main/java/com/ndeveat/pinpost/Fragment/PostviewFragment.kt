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
        var images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/01.jpg", "http://junsueg5737.dothome.co.kr/images/02.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트 입니다1", images, pushSocialDatas))


        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Twitter, SocialNetworkType.NaverBlog, SocialNetworkType.Tumblr)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/03.jpg", "http://junsueg5737.dothome.co.kr/images/04.jpg", "http://junsueg5737.dothome.co.kr/images/09.jpg", "http://junsueg5737.dothome.co.kr/images/013.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트 입니다2", images, pushSocialDatas))


        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.NaverBlog, SocialNetworkType.Tstory, SocialNetworkType.Facebook)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/05.jpg", "http://junsueg5737.dothome.co.kr/images/06.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트 입니다3", images, pushSocialDatas))

        pushSocialDatas = arrayListOf<SocialNetworkType>(SocialNetworkType.Tumblr, SocialNetworkType.Tstory, SocialNetworkType.Twitter)
        images = arrayListOf<String>("http://junsueg5737.dothome.co.kr/images/07.jpg", "http://junsueg5737.dothome.co.kr/images/08.jpg", "http://junsueg5737.dothome.co.kr/images/015.jpg", "http://junsueg5737.dothome.co.kr/images/017.jpg")
        mPostViewAdapter!!.mPosts.add(PostPreviewModel("테스트 입니다4", images, pushSocialDatas))

        return rootView
    }

    companion object {
        fun newInstance(): PostviewFragment {
            val fragment = PostviewFragment()
            return fragment
        }
    }
}
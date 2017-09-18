package com.ndeveat.pinpost

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ndeveat on 2017. 9. 18..
 */

class PostListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_postlist, container, false)
        return rootView
    }

    companion object {
        fun newInstance(): PostListFragment {
            val fragment = PostListFragment()
            return fragment
        }
    }
}
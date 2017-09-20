package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.CategoriesAdapter
import com.ndeveat.pinpost.Categories.CategoriesModel
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*


/**
 * Created by ndeveat on 2017. 9. 18..
 */

class CategoriesFragment : Fragment() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoriesAdapter: CategoriesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_categories, container, false)

        mCategoriesAdapter = CategoriesAdapter()
        mRecyclerView = rootView.categories_list
        mLayoutManager = GridLayoutManager(context, 2)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mCategoriesAdapter
        mRecyclerView!!.addItemDecoration(CategoriesAdapter.CategoriesDecoration(2, 50))

        for (i in 0..4)
            mCategoriesAdapter!!.mCategories.add(CategoriesModel("ASD", 5))

        return rootView
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}
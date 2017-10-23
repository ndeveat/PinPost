package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.Ui.Categories.Category.CategoryAdapter
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.fragment_categories.view.*


/**
 * Created by ndeveat on 2017. 9. 18..
 */

class CategoriesFragment : Fragment() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoryAdapter: CategoryAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_categories, container, false)

        mCategoryAdapter = CategoryAdapter()
        mRecyclerView = rootView.categories_list
        mRecyclerView!!.overScrollMode = View.OVER_SCROLL_NEVER

        mLayoutManager = GridLayoutManager(context, 3)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mCategoryAdapter
        mRecyclerView!!.addItemDecoration(CategoryAdapter.CategoriesDecoration(3, 50))

        getPostCount()

        return rootView
    }

    fun getPostCount() {
        Handler().postDelayed({
            mCategoryAdapter!!.updateCategoryCount()
            getPostCount()
        }, 2000)
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}
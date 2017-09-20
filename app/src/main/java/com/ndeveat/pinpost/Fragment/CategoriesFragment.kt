package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
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
        mLayoutManager = GridLayoutManager(context, 3)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mCategoriesAdapter
        mRecyclerView!!.addItemDecoration(CategoriesAdapter.CategoriesDecoration(3, 50))

        mCategoriesAdapter!!.mCategories.add(
                CategoriesModel(
                        "Facebook",
                        ContextCompat.getDrawable(context, R.drawable.facebook00001),
                        ContextCompat.getColor(context, R.color.snsFacebook)))
        mCategoriesAdapter!!.mCategories.add(
                CategoriesModel(
                        "Tstory",
                        ContextCompat.getDrawable(context, R.drawable.tstory00001),
                        ContextCompat.getColor(context, R.color.snsTstory)))
        mCategoriesAdapter!!.mCategories.add(
                CategoriesModel(
                        "Twitter",
                        ContextCompat.getDrawable(context, R.drawable.twitter00001),
                        ContextCompat.getColor(context, R.color.snsTwitter)))

        return rootView
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}
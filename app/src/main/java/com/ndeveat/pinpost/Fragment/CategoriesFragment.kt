package com.ndeveat.pinpost.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.CategoryAdapter
import com.ndeveat.pinpost.Categories.CategoryModel
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.SocialNetworkType
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
        mLayoutManager = GridLayoutManager(context, 3)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mCategoryAdapter
        mRecyclerView!!.addItemDecoration(CategoryAdapter.CategoriesDecoration(3, 50))

        addSocialNetworkCount()

        return rootView
    }

    fun addSocialNetworkCount() {
        mCategoryAdapter!!.mCategories.add(
                CategoryModel(
                        SocialNetworkType.Facebook,
                        ContextCompat.getDrawable(context, R.drawable.facebook00001),
                        ContextCompat.getColor(context, R.color.snsFacebook)))
        mCategoryAdapter!!.mCategories.add(
                CategoryModel(
                        SocialNetworkType.Tstory,
                        ContextCompat.getDrawable(context, R.drawable.tstory00001),
                        ContextCompat.getColor(context, R.color.snsTstory)))
        mCategoryAdapter!!.mCategories.add(
                CategoryModel(
                        SocialNetworkType.Twitter,
                        ContextCompat.getDrawable(context, R.drawable.twitter00001),
                        ContextCompat.getColor(context, R.color.snsTwitter)))
    }


    companion object {
        fun newInstance(): CategoriesFragment {
            val fragment = CategoriesFragment()
            return fragment
        }
    }
}
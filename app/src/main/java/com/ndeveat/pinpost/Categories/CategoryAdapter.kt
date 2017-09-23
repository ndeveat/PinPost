package com.ndeveat.pinpost.Categories

import android.graphics.PorterDuff
import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.DataCenter
import com.ndeveat.pinpost.R

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class CategoryAdapter : RecyclerView.Adapter<CategoryHolder>() {
    class CategoriesDecoration(spanCount: Int, spacing: Int) : RecyclerView.ItemDecoration() {
        val spanCount: Int
        val spacing: Int

        init {
            this.spacing = spacing
            this.spanCount = spanCount
        }

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            val position = parent!!.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            outRect!!.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        }
    }

    var mCategories: ArrayList<CategoryModel>

    init {
        mCategories = ArrayList<CategoryModel>()

        // update
        updateCategoryCount()
    }

    override fun getItemCount(): Int = mCategories.size

    override fun onBindViewHolder(holder: CategoryHolder?, position: Int) {
        val category = mCategories[position]
        val dataCenter = DataCenter.instance

        holder!!.setCategoryBackground(category.background)
        holder.setCategoryIcon(category.drawable)
        if (dataCenter.SocialNetworkServices.containsKey(category.snsType))
            holder.setCategoryCount(dataCenter.SocialNetworkServices.get(category.snsType)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.categories_view, parent, false)
        val holder = CategoryHolder(view)
        return holder
    }

    fun updateCategoryCount() {
        Handler().postDelayed({
            notifyDataSetChanged()
            updateCategoryCount()
        }, 1500)
    }
}
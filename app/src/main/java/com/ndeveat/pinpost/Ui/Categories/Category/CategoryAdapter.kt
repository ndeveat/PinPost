package com.ndeveat.pinpost.Ui.Categories.Category

import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
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

    lateinit var mSocialNetwork: List<SocialNetworkModel>

    init {
        mSocialNetwork = Manager.instance.snsList.filter { it.isLogin }
        // update
        updateCategoryCount()
    }

    override fun getItemCount(): Int = mSocialNetwork.size

    override fun onBindViewHolder(holder: CategoryHolder?, position: Int) {
        val category = mSocialNetwork[position]
        val dataCenter = Manager.instance

        holder!!.itemView.visibility = if (category.count > 0) View.VISIBLE else View.GONE
        holder.setCategoryBackground(category.snsMainColor)
        holder.setCategoryIcon(category.snsPlusImage)
        holder.setCategoryCount(dataCenter.snsList.filter { it.snsType == category.snsType }[0].count)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.category_view, parent, false)
        val holder = CategoryHolder(view)
        return holder
    }

    fun updateCategoryCount() {
        mSocialNetwork = Manager.instance.snsList.filter { it.count > 0 }
        Handler().postDelayed({
            notifyDataSetChanged()
            updateCategoryCount()
        }, 1500)
    }
}
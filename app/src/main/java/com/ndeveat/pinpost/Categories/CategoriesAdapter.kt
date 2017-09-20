package com.ndeveat.pinpost.Categories

import android.graphics.PorterDuff
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * Created by ndeveat on 2017. 9. 20..
 */

class CategoriesAdapter : RecyclerView.Adapter<CategoriesHolder>() {
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

            //outRect!!.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            //outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            //if (position >= spanCount) {
            //    outRect.top = spacing; // item top
            //}
        }
    }

    var mCategories: ArrayList<CategoriesModel>

    init {
        mCategories = ArrayList<CategoriesModel>()
    }

    override fun getItemCount(): Int = mCategories.size

    override fun onBindViewHolder(holder: CategoriesHolder?, position: Int) {
        val category = mCategories[position]
        holder!!.mBackground.background.setColorFilter(category.color, PorterDuff.Mode.MULTIPLY)
        holder.mIcon.setImageDrawable(category.drawable)
        holder.mCount.text = (0).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.categories_view, parent, false)
        val holder = CategoriesHolder(view)
        return holder
    }
}
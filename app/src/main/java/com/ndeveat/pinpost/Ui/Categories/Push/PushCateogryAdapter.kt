package com.ndeveat.pinpost.Ui.Categories.Push

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkModel
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType

/**
 * Created by ndeveat on 2017. 9. 24..
 */

class PushCateogryAdapter : RecyclerView.Adapter<PushCategoryHolder>() {
    interface PushCategoryEvent {
        fun add(snsName: String)
        fun remove(snsName: String)
    }

    val socialNetworkList: List<SocialNetworkModel>
    var pushCategoryEvent: PushCategoryEvent? = null

    val checkList = ArrayList<SocialNetworkType>()

    init {
        socialNetworkList = Manager.instance.snsList.filter { it.isLogin }
    }

    override fun getItemCount(): Int = socialNetworkList.size

    override fun onBindViewHolder(holder: PushCategoryHolder?, position: Int) {
        val category = socialNetworkList[position]
        holder!!.setCategoryBackground(category.snsMainColor)

        checkList.forEach {
            if (category.snsType == it) {
                if (!holder.isCheck) {
                    holder.check()
                }
            }
        }

        holder.mIconLayer?.setOnClickListener {
            if (holder.check()) {
                pushCategoryEvent?.add(category.snsType.toString())
            } else {
                pushCategoryEvent?.remove(category.snsType.toString())
            }
        }
        holder.setCategoryIcon(category.snsPlusImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PushCategoryHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.push_category_view, parent, false)
        val holder = PushCategoryHolder(view)
        return holder
    }
}
package com.ndeveat.pinpost.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.ndeveat.pinpost.Categories.Category.CategoryAdapter
import com.ndeveat.pinpost.Categories.PushCategory.PushCateogryAdapter
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_push.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.content.Intent
import org.jetbrains.anko.intentFor


class PushActivity : AppCompatActivity() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoryAdapter: PushCateogryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        val supportToolbar = toolbar
        setSupportActionBar(supportToolbar.pinpost_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        mCategoryAdapter = PushCateogryAdapter()
        mRecyclerView = push_category_list
        mLayoutManager = GridLayoutManager(this@PushActivity, 3)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mCategoryAdapter
        mRecyclerView!!.addItemDecoration(CategoryAdapter.CategoriesDecoration(3, 50))

        push_button.setOnClickListener {
            val intent = intentFor<MainActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
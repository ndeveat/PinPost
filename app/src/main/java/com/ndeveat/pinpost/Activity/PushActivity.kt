package com.ndeveat.pinpost.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.ndeveat.pinpost.Ui.Categories.Category.CategoryAdapter
import com.ndeveat.pinpost.Ui.Categories.Push.PushCateogryAdapter
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_push.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.net.Uri
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.ndeveat.pinpost.Ui.PushNotification
import org.jetbrains.anko.intentFor


class PushActivity : AppCompatActivity() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoryAdapter: PushCateogryAdapter? = null

    var title = ""
    var contents = ""
    var images: ArrayList<Uri>? = null
    var sns = ArrayList<String>()
    var tag = ""

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

        // Add Push Category Event
        mCategoryAdapter!!.pushCategoryEvent = object : PushCateogryAdapter.PushCategoryEvent {
            override fun add(snsName: String) {
                sns.add(snsName)
            }

            override fun remove(snsName: String) {
                sns.remove(snsName)
            }
        }

        title = intent.extras.getString("Title")
        contents = intent.extras.getString("Contents")
        images = intent.extras.getParcelableArrayList("Images")
        tag = intent.extras.getString("Tag")

        val type = intent.extras.getString("Type")
        if (type == "Edit") {
            val sns = intent.extras.getString("Sns")
            val snsList = sns.split(',')
            snsList.forEach { sit ->
                if (sit != "")
                    mCategoryAdapter!!.
                            checkList.add(SocialNetworkType.values().find { it == SocialNetworkType.valueOf(sit) }!!)
            }
        }

        push_button.setOnClickListener {
            postingNotification()

            val intent = intentFor<MainActivity>()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            finish()
        }
    }

    fun postingNotification() {
        val intent = intentFor<PushNotification>()
        intent.putExtra("title", title)
        intent.putExtra("contents", contents)
        intent.putExtra("images", images)
        intent.putExtra("sns", sns)
        intent.putExtra("tag", tag)

        startService(intent)
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
package com.ndeveat.pinpost.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import com.ndeveat.pinpost.Ui.Categories.Category.CategoryAdapter
import com.ndeveat.pinpost.Ui.Categories.Push.PushCateogryAdapter
import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_push.*
import kotlinx.android.synthetic.main.toolbar.view.*
import android.net.Uri
import com.koushikdutta.ion.Ion
import com.ndeveat.pinpost.Utils.RealPathUtil
import java.io.File


class PushActivity : AppCompatActivity() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoryAdapter: PushCateogryAdapter? = null

    var mTitle = ""
    var mContents = ""
    var mImages: ArrayList<Uri>? = null

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


        mTitle = intent.extras.getString("Title")
        mContents = intent.extras.getString("Contents")
        mImages = intent.extras.getParcelableArrayList("Images")

        Log.d("Title", mTitle)
        Log.d("Contents", mContents)
        Log.d("Images", mImages?.size.toString())

        push_button.setOnClickListener {
            // Ion을 이용해서 파일 전송 테스트
            val ion = Ion.with(this@PushActivity).load("http://192.168.0.16:3000/post/posting").setTimeout(1000 * 6)
            ion.setMultipartParameter("title", mTitle)
            ion.setMultipartParameter("contents", mContents)

            mImages!!.forEachIndexed { index, uri ->
                val imagePath = RealPathUtil.getRealPath(this@PushActivity, uri)
                val file = File(imagePath)
                Log.d("File", file.path)
                ion.setMultipartFile("image{$index}", file)
            }

            ion.asJsonObject().setCallback { e, result ->
                if (result != null) {
                    Log.d("Result", result.toString())
                } else {
                    e.printStackTrace()
                }
            }
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
package com.ndeveat.pinpost.Activity

import android.content.Intent
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
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.Utils.RealPathUtil
import org.jetbrains.anko.intentFor
import org.json.JSONObject
import java.io.File


class PushActivity : AppCompatActivity() {
    var mLayoutManager: GridLayoutManager? = null
    var mRecyclerView: RecyclerView? = null
    var mCategoryAdapter: PushCateogryAdapter? = null

    var title = ""
    var contents = ""
    var image: ArrayList<Uri>? = null
    var sns = ArrayList<String>()

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
        mCategoryAdapter!!.mPushCategoryEvent = object : PushCateogryAdapter.PushCategoryEvent {
            override fun add(snsName: String) {
                sns.add(snsName)
            }

            override fun remove(snsName: String) {
                sns.remove(snsName)
            }
        }

        title = intent.extras.getString("Title")
        contents = intent.extras.getString("Contents")
        image = intent.extras.getParcelableArrayList("Images")

        push_button.setOnClickListener {
            // Ion을 이용해서 파일 전송 테스트
            val ion = Ion.with(this@PushActivity).load(Manager.baseUrl + Manager.posting).setTimeout(1000 * 6)
            // Title
            ion.setMultipartParameter("title", title)
            // Contents
            ion.setMultipartParameter("contents", contents)
            // User ID
            ion.setMultipartParameter("user_id", Manager.instance.user.userId)
            // SNS Data
            val snsData = JSONObject()
            var snsString = ""
            sns.forEach { snsString += it + "," }
            snsString.dropLast(1)
            snsData.put("sns", snsString)
            ion.setMultipartParameter("sns", snsData.toString())

            image!!.forEachIndexed { index, uri ->
                val imagePath = RealPathUtil.getRealPath(this@PushActivity, uri)
                val file = File(imagePath)
                ion.setMultipartFile("image{$index}", file)
            }

            ion.asJsonObject().setCallback { e, result ->
                if (result != null) {
                    val success = result["result"].asBoolean
                    if (success) {
                        // 글을 작성합니다.
                        val intent = intentFor<MainActivity>()
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    } else {
                        // 임시 저장소로 이동합니다
                    }
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
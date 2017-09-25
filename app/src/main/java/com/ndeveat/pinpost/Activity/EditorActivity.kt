package com.ndeveat.pinpost.Activity

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
import android.widget.TextView

import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_container.*
import kotlinx.android.synthetic.main.editor_container.view.*
import kotlinx.android.synthetic.main.main_container.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.intentFor

class EditorActivity : AppCompatActivity() {

    var mEditorTitle: TextView? = null
    var mEditorEmptyView: View? = null
    var mEditorContents: TextView? = null
    var mEditorContainer: ScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        // Set home button
        val supportToolbar = toolbar
        setSupportActionBar(supportToolbar.pinpost_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        mEditorContainer = editor_scrollview
        mEditorContents = editor_contents_text
        mEditorContents?.isFocusableInTouchMode = true

        // Set Editor variable
        mEditorTitle = editor_title
        mEditorEmptyView = editor_empty_view
        mEditorEmptyView?.setOnClickListener {
            mEditorContents!!.requestFocus()
        }

        // Set push button
        editor_push_button.setOnClickListener {
            Log.d("push", "push")
            val intent = intentFor<PushActivity>()
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

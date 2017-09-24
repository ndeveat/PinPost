package com.ndeveat.pinpost.Activity

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem

import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.main_container.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.intentFor

class EditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val supportToolbar = toolbar
        setSupportActionBar(supportToolbar.pinpost_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

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

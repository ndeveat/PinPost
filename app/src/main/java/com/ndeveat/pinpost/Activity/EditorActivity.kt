package com.ndeveat.pinpost.Activity

import android.Manifest
import android.graphics.PorterDuff
import android.net.Uri
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
import gun0912.tedbottompicker.TedBottomPicker
import java.util.ArrayList
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission


class EditorActivity : AppCompatActivity() {

    var mEditorTitle: TextView? = null
    var mEditorEmptyView: View? = null
    var mEditorContents: TextView? = null
    var mEditorContainer: ScrollView? = null

    var bottomSheetDialogFragment: TedBottomPicker? = null

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
        // 권한 체크하기
        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                // Toast.makeText(this@EditorActivity, "Permission Granted", Toast.LENGTH_SHORT).show()

                bottomSheetDialogFragment = TedBottomPicker.Builder(this@EditorActivity)
                        .setOnMultiImageSelectedListener(object : TedBottomPicker.OnMultiImageSelectedListener {
                            override fun onImagesSelected(uriList: ArrayList<Uri>?) {

                            }
                        })
                        .setPeekHeight(800)
                        .showTitle(false)
                        .setCompleteButtonText("Done")
                        .setEmptySelectionText("No Select")
                        .create()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                Toast.makeText(this@EditorActivity, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()

        // Set push button
        editor_push_button.setOnClickListener {
            Log.d("push", "push")
            val intent = intentFor<PushActivity>()
            intent.putExtra("Title", mEditorTitle!!.text)
            intent.putExtra("Contents", mEditorContents!!.text)
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
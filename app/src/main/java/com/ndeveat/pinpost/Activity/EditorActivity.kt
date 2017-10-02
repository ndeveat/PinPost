package com.ndeveat.pinpost.Activity

import android.Manifest
import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView

import com.ndeveat.pinpost.R
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.editor_container.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.intentFor
import gun0912.tedbottompicker.TedBottomPicker
import java.util.ArrayList
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ndeveat.pinpost.Ui.ImageContents
import kotlinx.android.synthetic.main.editor_bottom_layer.*


class EditorActivity : AppCompatActivity() {

    var mEditorTitle: TextView? = null
    var mEditorEmptyView: View? = null
    var mEditorContents: TextView? = null
    var mEditorImageContents: LinearLayout? = null
    val mImages = ArrayList<Uri>()

    var bottomSheetDialogFragment: TedBottomPicker? = null
    var inputManager: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Set home button
        val supportToolbar = toolbar
        setSupportActionBar(supportToolbar.pinpost_toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        mEditorContents = editor_contents_text
        mEditorContents?.isFocusableInTouchMode = true

        // Set Editor variable
        mEditorImageContents = editor_contents_images
        mEditorTitle = editor_title
        mEditorEmptyView = editor_empty_view
        mEditorEmptyView?.setOnClickListener {
            Log.d("EditorEmptyView", "Click")
            mEditorContents!!.requestFocus()
            // When user click the emptyView call this function
            inputManager!!.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT)
        }
        // 권한 체크하기
        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                // Toast.makeText(this@EditorActivity, "Permission Granted", Toast.LENGTH_SHORT).show()

                bottomSheetDialogFragment = TedBottomPicker.Builder(this@EditorActivity)
                        .setOnMultiImageSelectedListener(object : TedBottomPicker.OnMultiImageSelectedListener {
                            override fun onImagesSelected(uriList: ArrayList<Uri>?) {
                                addImages(uriList!!)
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

        // Update Post
        // Set push button
        editor_push_button.setOnClickListener {
            Log.d("push", "push")
            val intent = intentFor<PushActivity>()
            intent.putExtra("Title", mEditorTitle!!.text.toString())
            intent.putExtra("Contents", mEditorContents!!.text.toString())
            intent.putExtra("Images", mImages)
            startActivity(intent)
        }

        // ---------------
        // Set Editor Component

        editor_add_component_image.setOnClickListener {
            bottomSheetDialogFragment?.show(supportFragmentManager)
        }
        editor_add_component_location.setOnClickListener {

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

    fun addImages(uris: ArrayList<Uri>) {
        removeImages()

        mImages.clear()

        for (uri in uris) {
            val image = ImageContents(this)
            image.image!!.setImageURI(uri)
            image.delete!!.setOnClickListener {
                removeImage(image)
            }
            image.tag = uri
            mImages.add(uri)
            mEditorImageContents!!.addView(image)
        }
    }

    fun removeImages() {
        mEditorImageContents!!.removeAllViews()
    }

    fun removeImage(view: View) {
        mEditorImageContents!!.removeView(view)
        if (mImages.contains(view.tag))
            mImages.remove(view.tag)
    }
}
package com.ndeveat.pinpost.Activity

import android.Manifest
import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
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
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.ndeveat.pinpost.Ui.View.PickImageContents
import kotlinx.android.synthetic.main.editor_bottom_layer.*


class EditorActivity : AppCompatActivity() {

    lateinit var editorTitle: TextView
    lateinit var editorEmptyView: View
    lateinit var editorContents: TextView
    lateinit var editorImageContents: LinearLayout
    val mImages = ArrayList<Uri>()
    lateinit var editorTag: TextView

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

        editorContents = editor_contents_text
        editorContents.isFocusableInTouchMode = true

        // Set Editor variable
        editorImageContents = editor_contents_images
        editorTitle = editor_title
        editorTag = editor_tag
        editorEmptyView = editor_empty_view
        editorEmptyView.setOnClickListener {
            Log.d("EditorEmptyView", "Click")
            editorContents.requestFocus()
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
                        .setCompleteButtonText("확인")
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
            val intent = intentFor<PushActivity>()
            intent.putExtra("Title", editorTitle.text.toString())
            intent.putExtra("Contents", editorContents.text.toString())
            intent.putExtra("Images", mImages)
            intent.putExtra("Tag", editorTag.text.toString())

            startActivity(intent)
        }

        // ---------------
        // Set Editor Component

        editor_add_component_image.setOnClickListener {
            bottomSheetDialogFragment?.show(supportFragmentManager)
        }

        editor_add_component_tag.setOnClickListener {
            editor_tag_parent.visibility = if (editor_tag_parent.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        editor_add_component_title.setOnClickListener {
            editor_title_parent.visibility = if (editor_title_parent.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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
        image_container_parent.visibility = View.VISIBLE
        if (uris.size == 0) {
            image_container_parent.visibility = View.GONE
        }

        for (uri in uris) {
            val view = LayoutInflater.from(this@EditorActivity).inflate(R.layout.editor_image_contents, null)
            val image = PickImageContents(view)
            val px = resources.getDimension(R.dimen.pick_image).toInt()
            image.image?.layoutParams = FrameLayout.LayoutParams(px, px)
            // image.image?.setImageURI(uri)
            Glide.with(this)
                    .load(uri)
                    .centerCrop()
                    .into(image.image)
            // image remove callback
            image.delete?.setOnClickListener {
                removeImage(view)
            }
            view.tag = uri
            mImages.add(uri)
            editorImageContents.addView(view)
        }
    }

    fun removeImages() {
        editorImageContents.removeAllViews()
    }

    fun removeImage(view: View) {
        editorImageContents.removeView(view)
        if (mImages.contains(view.tag))
            mImages.remove(view.tag)
        if (mImages.size == 0) {
            image_container_parent.visibility = View.GONE
        }
    }
}
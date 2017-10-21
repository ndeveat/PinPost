package com.ndeveat.pinpost.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ndeveat.pinpost.Ui.Categories.Sidebar.SidebarCategoryAdapter
import com.ndeveat.pinpost.Fragment.CategoriesFragment
import com.ndeveat.pinpost.Fragment.PostviewFragment
import com.ndeveat.pinpost.Login.LoginModule
import com.ndeveat.pinpost.Manager
import com.ndeveat.pinpost.R
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType
import com.ndeveat.pinpost.Ui.Categories.SocialNetworkType.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_container.view.*
import kotlinx.android.synthetic.main.side_bar.*
import kotlinx.android.synthetic.main.tablayout_thumb.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mViewPager: ViewPager? = null
    private var mTabLayout: TabLayout? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null

    private var mSidebarLayoutManager: LinearLayoutManager? = null
    private var mSidebarSnsAdapter: SidebarCategoryAdapter? = null

    lateinit var loginModule: LoginModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportToolbar = contents.toolbar
        setSupportActionBar(supportToolbar.pinpost_toolbar)

        var view: View

        // set tabLayout
        mTabLayout = supportToolbar.pinpost_tablayout

        view = LayoutInflater.from(this@MainActivity).inflate(R.layout.tablayout_thumb, null)
        mTabLayout?.addTab(mTabLayout!!.newTab().setCustomView(view))
        mTabLayout?.getTabAt(0)?.customView?.thumb?.text = "MY"
        //mTabLayout?.getTabAt(0)?.customView?.thumb?.setTextColor(android.R.color.holo_blue_light)

        view = LayoutInflater.from(this@MainActivity).inflate(R.layout.tablayout_thumb, null)
        mTabLayout?.addTab(mTabLayout!!.newTab().setCustomView(view))
        mTabLayout?.getTabAt(1)?.customView?.thumb?.text = "카테고리"
        //mTabLayout?.getTabAt(1)?.customView?.thumb?.setTextColor(android.R.color.darker_gray)

        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //tab!!.customView!!.thumb!!.setTextColor(android.R.color.darker_gray)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager!!.currentItem = mTabLayout!!.selectedTabPosition
                //tab!!.customView!!.thumb!!.setTextColor(android.R.color.holo_blue_light)
            }
        })

        // set pager
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager = contents.container
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout?.getTabAt(position)?.select()
            }
        })

        // set fab button
        contents.fab.setOnClickListener { view ->
            // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //         .setAction("Action", null).show()

            val intent = intentFor<EditorActivity>()
            startActivity(intent)
        }

        // set drawer
        mDrawerLayout = drawer_layout
        mDrawerToggle =
                ActionBarDrawerToggle(this@MainActivity, mDrawerLayout, supportToolbar.pinpost_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        mDrawerToggle?.syncState()
        // set Sidebar

        mSidebarLayoutManager = LinearLayoutManager(this@MainActivity)
        mSidebarSnsAdapter = SidebarCategoryAdapter()
        sidebar_snslist.adapter = mSidebarSnsAdapter
        sidebar_snslist.layoutManager = mSidebarLayoutManager

        sidebar_user_email.text = Manager.instance.user.userEmail
        sidebar_user_name.text = Manager.instance.user.userName

        loginModule = LoginModule(this@MainActivity)

        mSidebarSnsAdapter!!.sidebarEvent = object : SidebarCategoryAdapter.SidebarEvent {
            override fun login(snsType: SocialNetworkType) {
                when (snsType) {
                    Facebook -> {
                        loginModule.facebookLogin.login()
                    }
                    Twitter -> TODO()
                    Tstory -> TODO()
                    Tumblr -> TODO()
                    NaverBlog -> TODO()
                }
            }

            override fun logout(snsType: SocialNetworkType) {
                when (snsType) {
                    Facebook -> {
                        loginModule.facebookLogin.logout()
                    }
                    Twitter -> TODO()
                    Tstory -> TODO()
                    Tumblr -> TODO()
                    NaverBlog -> TODO()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        loginModule.facebookLogin.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // menuInflater.inflate(R.menu.menu_main2, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        //return if (id == R.id.action_settings) {
        //    true
        //} else super.onOptionsItemSelected(item)

        return super.onOptionsItemSelected(item)
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return if (position == 0)
                PostviewFragment.newInstance()
            else
                CategoriesFragment.newInstance()
        }

        override fun getCount(): Int = 2
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

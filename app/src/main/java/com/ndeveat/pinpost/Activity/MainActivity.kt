package com.ndeveat.pinpost.Activity

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Menu
import android.view.MenuItem
import com.ndeveat.pinpost.Fragment.CategoriesFragment
import com.ndeveat.pinpost.Fragment.PostPreviewFragment
import com.ndeveat.pinpost.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mViewPager: ViewPager? = null
    private var mTabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set toolbar title
        toolbar.title = "핀 포스트"
        setSupportActionBar(toolbar)

        // set tabLayout
        mTabLayout = tablayout
        mTabLayout?.addTab(tablayout.newTab().setIcon(R.drawable.icon_menu2))
        mTabLayout?.addTab(tablayout.newTab().setIcon(R.drawable.icon_human2))
        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager!!.currentItem = tablayout.selectedTabPosition
            }
        })

        // set pager
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager = container
        mViewPager!!.adapter = mSectionsPagerAdapter
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout!!.getTabAt(position)?.select()
            }
        })

        // set fab button
        fab.setOnClickListener { view ->
            // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //         .setAction("Action", null).show()
        }
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
                PostPreviewFragment.newInstance()
            else
                CategoriesFragment.newInstance()
        }

        override fun getCount(): Int = 2
    }
}

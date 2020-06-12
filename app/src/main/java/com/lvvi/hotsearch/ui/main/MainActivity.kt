package com.lvvi.hotsearch.ui.main

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.adapter.SectionsPagerAdapter
import com.umeng.analytics.MobclickAgent

class MainActivity : BaseActivity() {

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

}
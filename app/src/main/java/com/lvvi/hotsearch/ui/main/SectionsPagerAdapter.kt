package com.lvvi.hotsearch.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.baidu.BaiduFragment
import com.lvvi.hotsearch.ui.douyin.DouyinFragment
import com.lvvi.hotsearch.ui.v2ex.V2exFragment
import com.lvvi.hotsearch.ui.weibo.WeiboFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3,
        R.string.tab_text_4
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> WeiboFragment.newInstance()
            1 -> BaiduFragment.newInstance()
            2 -> DouyinFragment.newInstance()
            else -> V2exFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}
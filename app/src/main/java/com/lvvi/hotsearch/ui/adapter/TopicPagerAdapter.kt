package com.lvvi.hotsearch.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lvvi.hotsearch.model.V2exModel
import com.lvvi.hotsearch.ui.v2ex.TopicFragment


class TopicPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var beans = ArrayList<V2exModel>()

    fun setData(beans: ArrayList<V2exModel>) {
        this.beans = beans
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return TopicFragment.newInstance(beans[position].id.toString(), beans[position].title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return beans[position].title
    }

    override fun getCount(): Int {
        return beans.size
    }
}
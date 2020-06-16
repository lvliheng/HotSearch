package com.lvvi.hotsearch.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import com.lvvi.hotsearch.model.DouyinModel
import com.lvvi.hotsearch.ui.douyin.DouyinLargeFragment


class DouyinPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var beans = ArrayList<DouyinModel.AwemeListBean>()
    private var fragments = ArrayList<DouyinLargeFragment>()

    fun setData(beans: ArrayList<DouyinModel.AwemeListBean>) {
        this.beans = beans
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<DouyinModel.AwemeListBean> {
        return beans
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = DouyinLargeFragment.newInstance(Gson().toJson(beans[position]))
        if (!fragments.contains(fragment)) {
            fragments.add(fragment)
        }
        return fragment
    }
}
package com.lvvi.hotsearch.ui.douyin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.DouyinModel
import com.lvvi.hotsearch.ui.adapter.DouyinPagerAdapter
import com.lvvi.hotsearch.utils.Constant

class DouyinViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_douyin_view_pager)

        init()
    }

    private fun init() {
        val douyinPagerAdapter = DouyinPagerAdapter(supportFragmentManager, lifecycle)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = douyinPagerAdapter
        viewPager.offscreenPageLimit = 1

        val bundle = intent.extras
        var currPosition = 0
        if (bundle != null) {
            val beansString = bundle.getString(Constant.DOUYIN_EXTRA_BEANS, "")
            val awemeId = bundle.getString(Constant.DOUYIN_EXTRA_AWEME_ID, "")

            val typeToken = object: TypeToken<ArrayList<DouyinModel.AwemeListBean>>(){}.type
            val beans = Gson().fromJson<ArrayList<DouyinModel.AwemeListBean>>(beansString, typeToken)

            val tempBeans = ArrayList<DouyinModel.AwemeListBean>()
            for ((index, bean) in beans.withIndex()) {
                if (awemeId == bean.aweme_info.aweme_id) {
                    currPosition = index
                }
                if (currPosition == 0) {
                    tempBeans.add(bean)
                } else {
                    tempBeans.add(index - currPosition, bean)
                }
            }

            douyinPagerAdapter.setData(tempBeans)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_down_in, R.anim.translate_down_out)
    }
}
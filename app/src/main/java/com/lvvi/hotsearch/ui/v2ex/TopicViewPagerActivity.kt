package com.lvvi.hotsearch.ui.v2ex

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.V2exModel
import com.lvvi.hotsearch.ui.main.BaseActivity
import com.lvvi.hotsearch.ui.adapter.TopicPagerAdapter
import com.lvvi.hotsearch.utils.Constant

class TopicViewPagerActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_view_pager)

        init()
    }

    private fun init() {
        val topicPagerAdapter = TopicPagerAdapter(this, supportFragmentManager)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = topicPagerAdapter

        val bundle = intent.extras
        var currPosition = 0
        if (bundle != null) {
            val beansString = bundle.getString(Constant.V2EX_EXTRA_BEANS, "")
            val topicId = bundle.getString(Constant.V2EX_EXTRA_TOPIC_ID, "")

            val typeToken = object: TypeToken<ArrayList<V2exModel>>(){}.type
            val beans = Gson().fromJson<ArrayList<V2exModel>>(beansString, typeToken)

            val tempBeans = ArrayList<V2exModel>()
            for ((index, bean) in beans.withIndex()) {
                if (topicId == bean.id.toString()) {
                    currPosition = index
                }
                if (currPosition == 0) {
                    tempBeans.add(bean)
                } else {
                    tempBeans.add(index - currPosition, bean)
                }
            }
            topicPagerAdapter.setData(tempBeans)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.translate_right_in, R.anim.translate_right_out)
    }
}
package com.lvvi.hotsearch.ui.media

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.WeiboDetailModel.DataBean.CardsBean.MblogBean.PicsBean
import com.lvvi.hotsearch.ui.adapter.ImagePagerAdapter
import com.lvvi.hotsearch.ui.main.BaseActivity
import com.lvvi.hotsearch.ui.view.MyRelativeLayout
import com.lvvi.hotsearch.utils.Constant
import com.umeng.analytics.MobclickAgent
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList


class ImagePagerActivity : BaseActivity() {

    private var images = ArrayList<PicsBean>()

    private var totalCount = 0

    private lateinit var picIndexTv: TextView

    private lateinit var imageVp: ViewPager

    private lateinit var adapter: ImagePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actiivty_image_pager)

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window.enterTransition = null

        initView()
        initData()
    }

    private fun initView() {
        picIndexTv = findViewById(R.id.pic_index_tv)
        imageVp = findViewById(R.id.image_vp)
    }

    private fun initData() {
        totalCount = 0

        val bundle = intent.extras
        if (bundle != null) {
            val picsString =
                bundle.getString(Constant.MEDIA_EXTRA_PICS, "")
            if (picsString.isNotEmpty()) {
                images = Gson().fromJson(picsString, object : TypeToken<List<PicsBean>>(){}.type)
            }
        }

        totalCount = images.size

        adapter = ImagePagerAdapter()

        adapter.setHandler(MyHandler(this))

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        adapter.setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels)

        adapter.setData(images)
        adapter.setViewPager(imageVp)

        imageVp.adapter = adapter

        imageVp.currentItem = 0

        if (totalCount == 1) {
            picIndexTv.visibility = View.GONE
        }

        picIndexTv.text = String.format(
            resources.getString(R.string.pic_index_and_count), 1, totalCount)

        imageVp.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                picIndexTv.text = String.format(
                    resources.getString(R.string.pic_index_and_count), position + 1, totalCount)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    class MyHandler(activity: ImagePagerActivity): Handler() {

        private val weakReference = WeakReference(activity)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                HANDLER_DISMISS -> weakReference.get()?.backToList()
            }
        }
    }

    fun backToList() {
        onBackPressed()
    }

    companion object {
        const val HANDLER_DISMISS = 0
    }
}
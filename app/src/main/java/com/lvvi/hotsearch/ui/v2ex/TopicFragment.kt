package com.lvvi.hotsearch.ui.v2ex

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.V2exReplyModel
import com.lvvi.hotsearch.model.V2exTopicModel
import com.lvvi.hotsearch.ui.adapter.ReplyAdapter
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import me.everything.android.ui.overscroll.IOverScrollState
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter
import java.lang.ref.WeakReference

class TopicFragment : BaseFragment() {

    private lateinit var topicId: String
    private lateinit var handler: MyHandler

    private lateinit var title: TextView
    private lateinit var replyRv: RecyclerView
    private lateinit var loadingBg: View
    private lateinit var progressBar: ProgressBar
    private lateinit var tipLayout: RelativeLayout

    private lateinit var replyAdapter: ReplyAdapter

    private var totalReplyBeans = ArrayList<V2exReplyModel>()
    private var replyBeans = ArrayList<V2exReplyModel>()

    private var header = V2exTopicModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = LayoutInflater.from(context).inflate(R.layout.fragment_topic, container, false)
        initView(root)
        initData(arguments!!)
        getTopicData()
        return root
    }

    private fun initView(view: View) {
        title = view.findViewById(R.id.title_tv)
        loadingBg = view.findViewById(R.id.loading_bg)
        progressBar = view.findViewById(R.id.progressBar)
        tipLayout = view.findViewById(R.id.tip_layout)

        val headerTip: TextView = view.findViewById(R.id.header_tip_tv)
        val footerTip: TextView = view.findViewById(R.id.footer_tip_tv)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        replyAdapter = ReplyAdapter()

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        replyAdapter.setScreenWidth(displayMetrics.widthPixels)

        replyRv = view.findViewById<RecyclerView>(R.id.replies_rv).apply {
            layoutManager = linearLayoutManager
            adapter = replyAdapter
        }

        replyRv.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() > 1) {
                        replyAdapter.getHeader().isHeaderShow = false
                        replyAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        OverScrollDecoratorHelper.setUpOverScroll(replyRv, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        val decor = VerticalOverScrollBounceEffectDecorator(RecyclerViewOverScrollDecorAdapter(replyRv))

        var loadMore = false
        var isHeaderShow = false
        var mOffset = 0f
        var headerTipString = getString(R.string.header_tip)
        var footerTipString = getString(R.string.footer_tip)

        decor.setOverScrollUpdateListener { _, _, offset ->
            mOffset = offset
            if (offset > 0) {
                headerTipString = if (offset > 120) {
                    if (replyAdapter.getHeader().isHeaderShow) {
                        getString(R.string.really_shown_tip)
                    } else {
                        getString(R.string.release_tip)
                    }
                } else {
                    if (replyAdapter.getHeader().isHeaderShow) {
                        getString(R.string.shown_tip)
                    } else {
                        getString(R.string.header_tip)
                    }
                }
            } else {
                footerTipString = if (offset < -120) {
                    if (totalReplyBeans.size == replyAdapter.itemCount) {
                        getString(R.string.really_no_more_tip)
                    } else {
                        getString(R.string.release_tip)
                    }
                } else {
                    if (totalReplyBeans.size == replyAdapter.itemCount) {
                        getString(R.string.no_more_tip)
                    } else {
                        getString(R.string.footer_tip)
                    }
                }
            }
            headerTip.text = headerTipString
            footerTip.text = footerTipString
        }

        decor.setOverScrollStateListener { _, _, newState ->
            Log.e("topic activity", "newState: $newState loadMore: $loadMore isHeaderShow: $isHeaderShow")
            when (newState) {
                IOverScrollState.STATE_IDLE -> {
                    if (loadMore) {
                        loadReplies()
                    } else if (isHeaderShow) {
                        if (replyAdapter.getHeader().isHeaderShow != isHeaderShow) {
                            replyAdapter.getHeader().isHeaderShow = isHeaderShow
                            replyAdapter.notifyDataSetChanged()
                        }
                    }

                    loadMore = false
                    isHeaderShow = false
                    mOffset = 0f
                }
                IOverScrollState.STATE_BOUNCE_BACK -> {
                    loadMore = mOffset < -120
                    isHeaderShow = mOffset > 120
                }
                else -> {
                    replyRv.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
    }

    private fun initData(bundle: Bundle) {
        topicId = bundle.getString(Constant.V2EX_EXTRA_TOPIC_ID, "")
        val titleExtra = bundle.getString(Constant.V2EX_EXTRA_TOPIC_TITLE, "")
        title.text = titleExtra

        handler = MyHandler(this)
    }

    private fun getTopicData() {
        Log.e("topic activity", API.V2EX_TOPIC + topicId)
        HttpHelper.get().request(API.V2EX_TOPIC + topicId, object : HttpHelper.HttpCallBack {
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_TOPIC_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    private fun getRepliesData() {
        Log.e("topic activity", API.V2EX_REPLIES + topicId)

        HttpHelper.get().request(API.V2EX_REPLIES + topicId, object : HttpHelper.HttpCallBack {
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_REPLIES_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    class MyHandler(activity: TopicFragment): Handler() {

        private val weakReference: WeakReference<TopicFragment> = WeakReference(activity)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                when(msg.what){
                    HANDLER_SET_TOPIC_DATA -> weakReference.get()?.setTopicData(msg.obj.toString())
                    HANDLER_SET_REPLIES_DATA -> weakReference.get()?.setRepliesData(msg.obj.toString())
                    HANDLER_GET_DATA_FAIL -> weakReference.get()?.showEmptyTip()
                }
            }
        }
    }

    private fun setTopicData(result: String) {
        try {
            val type = object : TypeToken<List<V2exTopicModel>>(){}.type
            val models = Gson().fromJson<List<V2exTopicModel>>(result, type)
            if (!models.isNullOrEmpty()) {
                if (title.text.isEmpty()) {
                    title.text = models[0].title
                }
                header = models[0]
                header.isHeaderShow = true
            }

            getRepliesData()
        } catch (e: Exception) {
            Log.e("topic activity", "e: $e")
            handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
        }
    }

    private fun setRepliesData(result: String) {
        try {
            val type = object : TypeToken<ArrayList<V2exReplyModel>>(){}.type
            val models = Gson().fromJson<ArrayList<V2exReplyModel>>(result, type)

            if (!models.isNullOrEmpty()) {
                totalReplyBeans.addAll(models)
                
                loadReplies()
            }
        } catch (e: Exception) {
            Log.e("topic activity", "e: $e")
            handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
        }
    }

    private fun loadReplies() {
        Log.e("topic activity", "loadReplies total: ${totalReplyBeans.size} current: ${replyAdapter.itemCount}")
        if (replyAdapter.itemCount >= totalReplyBeans.size) {
            return
        }

        val currCounts = replyAdapter.itemCount

        for ((index, bean) in totalReplyBeans.withIndex()) {
            if (index >= currCounts
                && index < currCounts + REPLY_PAGE_SIZE) {
                replyBeans.add(bean)
            }
        }

        if (replyAdapter.itemCount == 0) {
            replyAdapter.setData(header, replyBeans)
        } else {
            replyAdapter.notifyDataSetChanged()
        }

        hideProgressBar()
    }

    private fun hideProgressBar() {
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
            loadingBg.visibility = View.GONE
            tipLayout.visibility = View.VISIBLE
        }
    }

    private fun showEmptyTip() {
        Log.e("topic activity", "get data failed.")
    }

    companion object {
        const val HANDLER_SET_TOPIC_DATA = 0
        const val HANDLER_SET_REPLIES_DATA = 1
        const val HANDLER_GET_DATA_FAIL = 4

        const val REPLY_PAGE_SIZE = 20

        fun newInstance(topicId: String, title: String): TopicFragment {
            return TopicFragment().apply {
                arguments = Bundle().apply {
                    putString(Constant.V2EX_EXTRA_TOPIC_ID, topicId)
                    putString(Constant.V2EX_EXTRA_TOPIC_TITLE, title)
                }
            }
        }
    }

}

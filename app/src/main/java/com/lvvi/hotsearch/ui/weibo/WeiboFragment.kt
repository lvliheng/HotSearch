package com.lvvi.hotsearch.ui.weibo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.WeiboDetailModel
import com.lvvi.hotsearch.model.WeiboModel
import com.lvvi.hotsearch.ui.adapter.WeiboAdapter
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import java.lang.ref.WeakReference

class WeiboFragment() : BaseFragment() {


    private lateinit var handler: MyHandler

    private lateinit var weiboAdapter: WeiboAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTv: TextView
    private lateinit var mainRv: RecyclerView

    private val dispatcher by lazy { requireActivity().onBackPressedDispatcher }
    lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!hasItemExpanded()) {
                    callback.isEnabled = false
                    dispatcher.onBackPressed()
                }
            }
        }

        dispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        activity?.window?.exitTransition = null

        initData(root)
        getData()
        return root
    }

    private fun initData(root: View) {
        progressBar = root.findViewById(R.id.progress_bar)
        emptyTv = root.findViewById(R.id.empty_tv)

        weiboAdapter = WeiboAdapter()
        handler = MyHandler(this)
        weiboAdapter.setHandler(handler)
        weiboAdapter.setActivity(activity!!)

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        weiboAdapter.setScreenWidth(displayMetrics.widthPixels)

        val mainLayoutManager = LinearLayoutManager(activity)
        mainRv = root.findViewById<RecyclerView>(R.id.main_rv).apply {
            layoutManager = mainLayoutManager
            adapter = weiboAdapter
        }
    }

    fun hasItemExpanded(): Boolean {
        if (this::weiboAdapter.isInitialized) {
            var isDetailShow = false
            var position = -1

            for ((index, item) in weiboAdapter.getData().withIndex()) {
                if (item.isDetailShow == Constant.IS_TRUE) {
                    item.isDetailShow = Constant.IS_FALSE
                    isDetailShow = true
                    position = index
                }
            }

            if (isDetailShow) {
                val layoutManager = mainRv.layoutManager as LinearLayoutManager

                val firstVisibleItemPosition =
                    layoutManager.findFirstCompletelyVisibleItemPosition()
                val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()

                return if (position in firstVisibleItemPosition..lastVisibleItemPosition) {
                    weiboAdapter.setLastExpandedPosition(position)
                    weiboAdapter.setCurrExpandedPosition(position)
                    weiboAdapter.notifyDataSetChanged()
                    true
                } else {
                    false
                }
            } else {
                return false
            }
        } else {
            return false
        }
    }

    private fun getData() {
        HttpHelper.get().request(API.HOT_SEARCH_WEIBO, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("weibo", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                Log.e("weibo", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    class MyHandler(fragment: WeiboFragment): Handler() {

        private val weakReference: WeakReference<WeiboFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                when (msg.what) {
                    HANDLER_SET_DATA -> weakReference.get()?.setData(msg.obj.toString())
                    HANDLER_VIEW_IN_BROWSER -> weakReference.get()
                        ?.viewInBrowser(msg.obj.toString())
                    HANDLER_ITEM_CLICK -> weakReference.get()
                        ?.toggleItem(msg.arg1, msg.obj.toString())
                    HANDLER_SET_DETAIL_DATA -> weakReference.get()
                        ?.setDetailData(msg.arg1, msg.arg2, msg.obj.toString())
                    HANDLER_GET_DATA_FAIL -> weakReference.get()?.showEmptyTip()
                }
            }
        }
    }

    fun showEmptyTip() {
        progressBar.visibility = View.GONE
        emptyTv.visibility = View.VISIBLE
    }

    fun viewInBrowser(url: String) {
        Log.e("weibo", "viewInBrowser url: $url")
        val intent = Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        activity?.startActivity(intent)
    }

    private fun toggleItem(position: Int, url: String) {
        Log.e("weibo", "toggleItem position: $position")

        var isDetailShow = false
        var lastExpandedPosition = -1
        var hasContent = false

        for ((index, item) in weiboAdapter.getData().withIndex()) {
            if (item.isDetailShow == Constant.IS_TRUE) {
                lastExpandedPosition = index
                break
            }
        }

        for ((index, item) in weiboAdapter.getData().withIndex()) {
            if (position == index) {
                if (item.isDetailShow == Constant.IS_TRUE) {
                    item.isDetailShow = Constant.IS_FALSE
                    isDetailShow = true
                } else {
                    item.isDetailShow = Constant.IS_TRUE
                    hasContent = !item.detailText.isNullOrEmpty()
                }
            } else {
                item.isDetailShow = Constant.IS_FALSE
            }
        }

        if (isDetailShow || hasContent) {
            weiboAdapter.setLastExpandedPosition(lastExpandedPosition)
            weiboAdapter.setCurrExpandedPosition(position)
            weiboAdapter.notifyDataSetChanged()
        } else {
            getDetailInfo(lastExpandedPosition, position, url)
        }
    }

    private fun getDetailInfo(lastExpandedPosition: Int, position: Int, url: String) {
        val containerIdLastIndex = url.lastIndexOf(Constant.WEIBO_PARAM_CONTAINER_ID)

        val detailUrl = API.WEIBO_DETAIL +
                url.substring(containerIdLastIndex, url.length) + API.WEIBO_DETAIL_PARAMS

        Log.e("weibo", "detailUrl: $detailUrl")

        HttpHelper.get().request(detailUrl, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DETAIL_DATA
                message.arg1 = lastExpandedPosition
                message.arg2 = position
                message.obj = result
                handler.handleMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("weibo", "getDetailInfo onFailure: $msg")
            }

            override fun onError(msg: String) {
                Log.e("weibo", "getDetailInfo onError: $msg")
            }
        })
    }

    fun setData(result: String) {
        try {
            val weiboModel = Gson().fromJson(result, WeiboModel::class.java)

            weiboAdapter.setUpdateTime(weiboModel.data.cards[0].title)
            weiboAdapter.setData(weiboModel.data.cards[0].card_group
                    as ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean>)

            progressBar.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
        } catch (e: Exception) {
            Log.e("weibo", "set data failed.")
            progressBar.visibility = View.GONE
            emptyTv.visibility = View.VISIBLE
        }
    }

    fun setDetailData(lastExpandedPosition: Int, position: Int, result: String) {
        val detailModel = Gson().fromJson(result, WeiboDetailModel::class.java)
        Log.e("weibo fragment", "cards.size: ${detailModel.data.cards.size}")

        for ((index, item) in weiboAdapter.getData().withIndex()) {
            item.isDetailShow = Constant.IS_FALSE
            if (index == position) {
                for (detailItem in detailModel.data.cards) {
                    Log.e("weibo fragment", "card_type: ${detailItem.card_type}")
                    if (detailItem.card_type == 9) {
                        if (detailItem.mblog != null) {
                            if (detailItem.mblog.text != null) {
                                item.detailText = detailItem.mblog.text
                            }

                            setMediaData(detailItem, item)

                            item.isDetailShow = Constant.IS_TRUE
                            Log.e("weibo fragment", "isDetailShow = Constant.IS_TRUE")
                            break
                        }
                    } else if (detailItem.card_type == 11) {
                        if (detailItem.card_group != null) {
                            for (card in detailItem.card_group) {
                                if (card.card_type == 9) {
                                    if (card.mblog != null) {
                                        if (card.mblog.text != null) {
                                            item.detailText = card.mblog.text
                                        }

                                        val detailBean = WeiboDetailModel.DataBean.CardsBean()
                                        detailBean.mblog = card.mblog
                                        setMediaData(detailBean, item)

                                        item.isDetailShow = Constant.IS_TRUE
                                        Log.e("weibo fragment", "isDetailShow = Constant.IS_TRUE")
                                        break
                                    }
                                }
                            }
                            if (!item.detailText.isNullOrEmpty()) {
                                item.isDetailShow = Constant.IS_TRUE
                                Log.e("weibo fragment", "isDetailShow = Constant.IS_TRUE")
                                break
                            }
                        }
                    }
                }
                if (item.detailText != null) {
                    Log.e("weibo", item.detailText)
                }
                if (item.pics != null) {
                    Log.e("weibo", item.pics.toString())
                }
                if (item.videoUrl != null) {
                    Log.e("weibo", item.videoUrl)
                }
            }
        }
        activity?.runOnUiThread {
            weiboAdapter.setLastExpandedPosition(lastExpandedPosition)
            weiboAdapter.setCurrExpandedPosition(position)
            weiboAdapter.notifyDataSetChanged()
        }
    }

    private fun setMediaData(
        detailItem: WeiboDetailModel.DataBean.CardsBean,
        item: WeiboModel.DataBean.CardsBean.CardGroupBean) {
        if (detailItem.mblog.page_info != null) {
            if (detailItem.mblog.page_info.type != null) {
                if (detailItem.mblog.page_info.type == Constant.MEDIA_TYPE_ARTICLE) {
                    if (detailItem.mblog.pics != null && detailItem.mblog.pics.size > 0) {
                        item.type = Constant.MEDIA_TYPE_WEBPAGE
                        item.pics = detailItem.mblog.pics
                    } else {
                        item.type = Constant.MEDIA_TYPE_ARTICLE

                        if (detailItem.mblog.page_info.page_pic != null) {
                            item.pagePic = detailItem.mblog.page_info.page_pic.url
                        }
                        if (detailItem.mblog.page_info.page_title != null) {
                            item.pageTitle = detailItem.mblog.page_info.page_title
                        }
                        if (detailItem.mblog.page_info.page_url != null) {
                            item.pageUrl = detailItem.mblog.page_info.page_url
                        }
                        if (detailItem.mblog.page_info.icon != null) {
                            item.pageTypeIcon = detailItem.mblog.page_info.icon
                        }
                        if (detailItem.mblog.page_info.object_id != null) {
                            item.objectId = detailItem.mblog.page_info.object_id
                        }
                    }
                } else if (detailItem.mblog.page_info.type == Constant.MEDIA_TYPE_VIDEO) {
                    if (detailItem.mblog.page_info.media_info != null) {
                        if (!detailItem.mblog.page_info.media_info.mp4_hd_url.isNullOrEmpty()) {
                            item.type = Constant.MEDIA_TYPE_VIDEO
                            item.videoUrl =
                                detailItem.mblog.page_info.media_info.mp4_hd_url
                            item.duration =
                                detailItem.mblog.page_info.media_info.duration
                        }
                    }
                } else if (detailItem.mblog.page_info.type == Constant.MEDIA_TYPE_LIVE) {
                    if (detailItem.mblog.page_info.media_info != null) {
                        if (!detailItem.mblog.page_info.media_info.stream_url.isNullOrEmpty()) {
                            item.type = Constant.MEDIA_TYPE_VIDEO
                            item.videoUrl =
                                detailItem.mblog.page_info.media_info.stream_url
                            item.duration = -1
                        }
                    }
                } else if (detailItem.mblog.page_info.type == Constant.MEDIA_TYPE_WEBPAGE) {
                    if (detailItem.mblog.pics != null && detailItem.mblog.pics.size > 0) {
                        item.type = Constant.MEDIA_TYPE_WEBPAGE
                        item.pics = detailItem.mblog.pics
                    }
                } else {
                    if (detailItem.mblog.pics != null && detailItem.mblog.pics.size > 0) {
                        item.type = Constant.MEDIA_TYPE_WEBPAGE
                        item.pics = detailItem.mblog.pics
                    }
                }
            } else {
                if (detailItem.mblog.pics != null && detailItem.mblog.pics.size > 0) {
                    item.type = Constant.MEDIA_TYPE_WEBPAGE
                    item.pics = detailItem.mblog.pics
                }
            }
        } else {
            if (detailItem.mblog.pics != null && detailItem.mblog.pics.size > 0) {
                item.type = Constant.MEDIA_TYPE_WEBPAGE
                item.pics = detailItem.mblog.pics
            }
        }
    }

    companion object {
        const val HANDLER_SET_DATA = 0
        const val HANDLER_ITEM_CLICK = 1
        const val HANDLER_SET_DETAIL_DATA = 2
        const val HANDLER_VIEW_IN_BROWSER = 3
        const val HANDLER_GET_DATA_FAIL = 4

        @JvmStatic
        fun newInstance(): WeiboFragment {
            return WeiboFragment()
        }
    }
}
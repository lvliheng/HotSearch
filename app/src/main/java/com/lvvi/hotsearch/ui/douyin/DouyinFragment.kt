package com.lvvi.hotsearch.ui.douyin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.DouyinModel
import com.lvvi.hotsearch.ui.adapter.DouyinAdapter
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.ui.media.VideoViewActivity
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import com.lvvi.hotsearch.utils.MySharePreferences
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
class DouyinFragment : BaseFragment() {


    private lateinit var handler: MyHandler

    private lateinit var douyinAdapter: DouyinAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTv: TextView
    private lateinit var mainRv: RecyclerView

    private var isVisibleToUser = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        initData(root)
        getData()
        return root
    }

    private fun initData(root: View) {
        progressBar = root.findViewById(R.id.progress_bar)
        emptyTv = root.findViewById(R.id.empty_tv)

        douyinAdapter = DouyinAdapter()
        douyinAdapter.setActivity(activity!!)
        handler = MyHandler(this)
        douyinAdapter.setHandler(handler)

        val mainLayoutManager = LinearLayoutManager(activity)
        mainRv = root.findViewById<RecyclerView>(R.id.main_rv).apply {
            layoutManager = mainLayoutManager
            adapter = douyinAdapter
        }

        mainRv.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    var needNotify = false
                    for ((index, bean) in douyinAdapter.getData().withIndex()) {
                        if (index == mainLayoutManager.findFirstCompletelyVisibleItemPosition()) {
                            if (!bean.aweme_info.video.isPlaying) {
                                needNotify = true
                                bean.aweme_info.video.isSelected = true
                                bean.aweme_info.video.isPlaying = true
                            }
                            continue
                        }
                        bean.aweme_info.video.isSelected = false
                        bean.aweme_info.video.isPlaying = false
                    }
                    if (needNotify) {
                        douyinAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun getData() {
        HttpHelper.get().request(API.HOT_SEARCH_DOUYIN, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("douyin", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                Log.e("douyin", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    class MyHandler(fragment: DouyinFragment): Handler() {

        private val weakReference: WeakReference<DouyinFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                when(msg.what){
                    HANDLER_SET_DATA -> weakReference.get()?.setData(msg.obj.toString())
                    HANDLER_ITEM_CLICK -> weakReference.get()?.onItemClick(msg.arg1)
                    HANDLER_ITEM_LONG_CLICK -> weakReference.get()?.onItemLongClick(msg.obj.toString())
                    //unused
                    HANDLER_ITEM_SCROLL_TO_NEXT -> weakReference.get()?.scrollToNext(msg.arg1)
                    HANDLER_GET_DATA_FAIL -> weakReference.get()?.showEmptyTip()
                }
            }
        }
    }

    fun setData(result: String) {
        try {
            val model = Gson().fromJson(result, DouyinModel::class.java)

            for ((index, bean) in model.aweme_list.withIndex()) {
                if (index == 0) {
                    bean.aweme_info.video.isSelected = true
                    bean.aweme_info.video.isPlaying = false
                }
            }

            douyinAdapter.setUpdateTime(String.format(resources.getString(R.string.update), model.active_time))
            douyinAdapter.setData(model.aweme_list as ArrayList<DouyinModel.AwemeListBean>)

            progressBar.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
        } catch (e: Exception) {
            Log.e("douyin", "set data failed.")
            progressBar.visibility = View.GONE
            emptyTv.visibility = View.VISIBLE
        }
    }

    fun showEmptyTip() {
        progressBar.visibility = View.GONE
        emptyTv.visibility = View.VISIBLE
    }

    fun onItemClick(position: Int) {
        val intent = Intent()
        intent.setClass(context!!, DouyinViewPagerActivity::class.java)

        intent.putExtra(Constant.DOUYIN_EXTRA_BEANS, Gson().toJson(douyinAdapter.getData()))
        intent.putExtra(Constant.DOUYIN_EXTRA_AWEME_ID, douyinAdapter.getData()[position].aweme_info.aweme_id)

        activity?.startActivity(intent)
        activity!!.overridePendingTransition(R.anim.translate_up_in, R.anim.translate_up_out)
    }

    private fun onItemLongClick(url: String) {
        val intent = Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        activity?.startActivity(intent)
    }

    //unused
    private fun scrollToNext(currPosition: Int) {
        val layoutManager = mainRv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(currPosition + 1, 0)
    }

    override fun onResume() {
        Log.e("douyin fragment", "onResume")
        if (isVisibleToUser) {
            refreshPlayingState(true)
        }
        super.onResume()
    }

    override fun onPause() {
        Log.e("douyin fragment", "onPause")
        refreshPlayingState(false)
        super.onPause()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.e("douyin fragment", "setUserVisibleHint isVisibleToUser: $isVisibleToUser")
        this.isVisibleToUser = isVisibleToUser
        refreshPlayingState(isVisibleToUser)
        super.setUserVisibleHint(isVisibleToUser)
    }

    private fun refreshPlayingState(isFragmentVisible: Boolean) {
        if (this::douyinAdapter.isInitialized) {
            var needNotify = false
            for (bean in douyinAdapter.getData()) {
                if (bean.aweme_info.video.isSelected) {
                    if (bean.aweme_info.video.isPlaying != isFragmentVisible) {
                        bean.aweme_info.video.isPlaying = isFragmentVisible
                        needNotify = true
                    }
                    break
                }
            }
            if (needNotify) {
                douyinAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val HANDLER_SET_DATA = 0
        const val HANDLER_ITEM_CLICK = 1
        const val HANDLER_ITEM_LONG_CLICK = 2
        const val HANDLER_ITEM_SCROLL_TO_NEXT = 3
        const val HANDLER_GET_DATA_FAIL = 4

        @JvmStatic
        fun newInstance(): DouyinFragment {
            return DouyinFragment()
        }
    }
}
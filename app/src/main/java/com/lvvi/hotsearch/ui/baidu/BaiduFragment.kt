package com.lvvi.hotsearch.ui.baidu

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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.BaiduModel
import com.lvvi.hotsearch.ui.adapter.BaiduAdapter
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.ui.media.WebviewActivity
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
class BaiduFragment : BaseFragment() {


    private lateinit var handler: MyHandler

    private lateinit var baiduAdapter: BaiduAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var emptyTv: TextView
    private lateinit var mainRv: RecyclerView

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

        baiduAdapter = BaiduAdapter()
        handler = MyHandler(this)
        baiduAdapter.setHandler(handler)

        val mainLayoutManager = LinearLayoutManager(activity)
        mainRv = root.findViewById<RecyclerView>(R.id.main_rv).apply {
            layoutManager = mainLayoutManager
            adapter = baiduAdapter
        }
    }

    private fun getData() {
        HttpHelper.get().request(API.HOT_SEARCH_BAIDU, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("baidu", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                Log.e("baidu", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    class MyHandler(fragment: BaiduFragment): Handler() {

        private val weakReference: WeakReference<BaiduFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                when(msg.what){
                    HANDLER_SET_DATA -> weakReference.get()?.setData(msg.obj.toString())
                    HANDLER_ITEM_CLICK -> weakReference.get()?.onItemClick(msg.obj.toString())
                    HANDLER_ITEM_LONG_CLICK -> weakReference.get()?.onItemLongClick(msg.obj.toString())
                    HANDLER_GET_DATA_FAIL -> weakReference.get()?.showEmptyTip()
                }
            }
        }
    }

    fun showEmptyTip() {
        progressBar.visibility = View.GONE
        emptyTv.visibility = View.VISIBLE
    }

    fun onItemClick(url: String) {
        val intent = Intent()
        intent.setClass(context!!, WebviewActivity::class.java)
        intent.putExtra(Constant.MEDIA_EXTRA_WEBVIEW_URL, url)
        activity?.startActivity(intent)
    }

    fun onItemLongClick(url: String) {
        val intent = Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        activity?.startActivity(intent)
    }

    fun setData(result: String) {
        try {
            val model = Gson().fromJson(result, BaiduModel::class.java)
            baiduAdapter.setData(model.result.topwords as ArrayList<BaiduModel.ResultBean.TopwordsBean>)

            progressBar.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
        } catch (e: Exception) {
            Log.e("baidu", "set data failed.")
            progressBar.visibility = View.GONE
            emptyTv.visibility = View.VISIBLE
        }
    }

    companion object {
        const val HANDLER_SET_DATA = 0
        const val HANDLER_ITEM_CLICK = 1
        const val HANDLER_ITEM_LONG_CLICK = 2
        const val HANDLER_GET_DATA_FAIL = 4

        @JvmStatic
        fun newInstance(): BaiduFragment {
            return BaiduFragment()
        }
    }
}
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
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
class DouyinFragment : Fragment() {


    private lateinit var handler: MyHandler

    private lateinit var douyinAdapter: DouyinAdapter

    private lateinit var updateTime: TextView
    private lateinit var mainCv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        updateTime = root.findViewById(R.id.update_time_tv)

        douyinAdapter = DouyinAdapter()
        handler = MyHandler(this)
        douyinAdapter.setHandler(handler)

        val mainLayoutManager = LinearLayoutManager(activity)
        mainCv = root.findViewById<RecyclerView>(R.id.main_rv).apply {
            layoutManager = mainLayoutManager
            adapter = douyinAdapter
        }
    }

    private fun getData() {
        HttpHelper.get().request(Constant.HOT_SEARCH_DOUYIN, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("douyin", msg)
            }

            override fun onError(msg: String) {
                Log.e("douyin", msg)
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
                    HANDLER_ITEM_CLICK -> weakReference.get()?.onItemClick(msg.obj.toString())
                }
            }
        }
    }

    fun onItemClick(url: String) {
        val intent = Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        activity?.startActivity(intent)
    }

    fun setData(result: String) {
        try {
            val model = Gson().fromJson(result, DouyinModel::class.java)
            douyinAdapter.setData(model.aweme_list as ArrayList<DouyinModel.AwemeListBean>)

            updateTime.text = String.format(resources.getString(R.string.update), model.active_time)
        } catch (e: Exception) {
            Log.e("douyin", "set data failed.")
        }
    }

    companion object {
        const val HANDLER_SET_DATA = 0
        const val HANDLER_ITEM_CLICK = 1

        @JvmStatic
        fun newInstance(): DouyinFragment {
            return DouyinFragment()
        }
    }
}
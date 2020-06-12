package com.lvvi.hotsearch.ui.v2ex

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
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.V2exModel
import com.lvvi.hotsearch.ui.adapter.V2exAdapter
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.HttpHelper
import java.lang.ref.WeakReference

/**
 * A placeholder fragment containing a simple view.
 */
class V2exFragment : BaseFragment() {


    private lateinit var handler: MyHandler

    private lateinit var v2exAdapter: V2exAdapter

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

        v2exAdapter = V2exAdapter()
        handler = MyHandler(this)
        v2exAdapter.setHandler(handler)
        v2exAdapter.setActivity(activity!!)

        val mainLayoutManager = LinearLayoutManager(activity)
        mainRv = root.findViewById<RecyclerView>(R.id.main_rv).apply {
            layoutManager = mainLayoutManager
            adapter = v2exAdapter
        }
    }

    private fun getData() {
        HttpHelper.get().request(API.HOT_SEARCH_V2EX, object : HttpHelper.HttpCallBack{
            override fun onSuccess(result: String) {
                val message = Message()
                message.what = HANDLER_SET_DATA
                message.obj = result
                handler.sendMessage(message)
            }

            override fun onFailure(msg: String) {
                Log.e("v2ex", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

            override fun onError(msg: String) {
                Log.e("v2ex", msg)
                handler.sendEmptyMessage(HANDLER_GET_DATA_FAIL)
            }

        })
    }

    class MyHandler(fragment: V2exFragment): Handler() {

        private val weakReference: WeakReference<V2exFragment> = WeakReference(fragment)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                when(msg.what){
                    HANDLER_SET_DATA -> weakReference.get()?.setData(msg.obj.toString())
                    HANDLER_ITEM_CLICK -> weakReference.get()?.onItemClick(msg.arg1)
                    HANDLER_ITEM_LONG_CLICK -> weakReference.get()?.onItemLongCLick(msg.obj.toString())
                    HANDLER_GET_DATA_FAIL -> weakReference.get()?.showEmptyTip()
                }
            }
        }
    }

    fun showEmptyTip() {
        progressBar.visibility = View.GONE
        emptyTv.visibility = View.VISIBLE
    }

    fun onItemClick(position: Int) {
        val intent = Intent()
        intent.setClass(context!!, TopicViewPagerActivity::class.java)

        intent.putExtra(Constant.V2EX_EXTRA_BEANS, Gson().toJson(v2exAdapter.getData()))
        intent.putExtra(Constant.V2EX_EXTRA_TOPIC_ID, v2exAdapter.getData()[position].id.toString())

        activity?.startActivity(intent)
        activity!!.overridePendingTransition(R.anim.translate_left_in, R.anim.translate_left_out)
    }

    fun onItemLongCLick(url: String) {
        val intent = Intent()
        intent.data = Uri.parse(url)
        intent.action = Intent.ACTION_VIEW
        activity?.startActivity(intent)
    }

    fun setData(result: String) {
        try {
            val typeToken = object: TypeToken<ArrayList<V2exModel>>(){}.type
            val v2exModels = Gson().fromJson<ArrayList<V2exModel>>(result, typeToken)
            v2exAdapter.setData(v2exModels)

            progressBar.visibility = View.GONE
            mainRv.visibility = View.VISIBLE
        } catch (e: Exception) {
            Log.e("v2ex", "set data failed.")
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
        fun newInstance(): V2exFragment {
            return V2exFragment()
        }
    }
}
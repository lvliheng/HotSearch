package com.lvvi.hotsearch.ui.adapter

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.baidu.BaiduFragment
import com.lvvi.hotsearch.model.BaiduModel
import com.lvvi.hotsearch.utils.API
import com.lvvi.hotsearch.utils.Constant
import kotlinx.android.synthetic.main.item_baidu.view.*

class BaiduAdapter() :
    RecyclerView.Adapter<BaiduAdapter.ViewHolder>() {

    private lateinit var context: Context

    private lateinit var handler: BaiduFragment.MyHandler

    private var beans = ArrayList<BaiduModel.ResultBean.TopwordsBean>()

    fun setData(beans: ArrayList<BaiduModel.ResultBean.TopwordsBean>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun setHandler(handler: BaiduFragment.MyHandler) {
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_baidu, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.update.visibility = View.VISIBLE
            holder.update.text =
                String.format(context.resources.getString(R.string.update), beans[0].gentime)
        } else {
            holder.update.visibility = View.GONE
        }

        when(position){
            0 -> holder.index.setBackgroundResource(R.drawable.index_first_bg)
            1 -> holder.index.setBackgroundResource(R.drawable.index_second_bg)
            2 -> holder.index.setBackgroundResource(R.drawable.index_third_bg)
            else -> holder.index.setBackgroundResource(R.drawable.index_others_bg)
        }

        holder.index.text = (position + 1).toString()
        holder.title.text = beans[position].keyword

        if (beans[position].isNew == "1") {
            holder.isNew.visibility = View.VISIBLE
        } else {
            holder.isNew.visibility = View.GONE
        }

        if (beans[position].trend == "rise") {
            holder.trend.setBackgroundResource(R.mipmap.up)
        } else {
            holder.trend.setBackgroundResource(R.mipmap.down)
        }

        holder.searches.text = beans[position].searches.toString()

        holder.itemView.setOnClickListener {
            val message = Message()
            message.what =
                BaiduFragment.HANDLER_ITEM_CLICK
            message.obj = API.BAIDU_SCHEME + beans[position].keyword
            handler.sendMessage(message)
        }

        holder.itemView.setOnLongClickListener {
            val message = Message()
            message.what =
                BaiduFragment.HANDLER_ITEM_LONG_CLICK
            message.obj = API.BAIDU_SCHEME + beans[position].keyword
            handler.sendMessage(message)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val update: TextView = itemView.update_time_tv
        val index: TextView = itemView.index_tv
        val title: TextView = itemView.title_tv
        val isNew: TextView = itemView.is_new_tv
        val trend: ImageView = itemView.trend_iv
        val searches: TextView = itemView.searches_tv
    }

}
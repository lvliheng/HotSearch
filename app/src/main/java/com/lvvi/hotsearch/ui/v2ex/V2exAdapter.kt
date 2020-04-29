package com.lvvi.hotsearch.ui.v2ex

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lvvi.hotsearch.R
import kotlinx.android.synthetic.main.item_v2ex.view.*
import kotlinx.android.synthetic.main.item_weibo.view.*
import kotlinx.android.synthetic.main.item_weibo.view.title_tv

class V2exAdapter() :
    RecyclerView.Adapter<V2exAdapter.ViewHolder>() {

    private lateinit var handler: V2exFragment.MyHandler

    private var beans = ArrayList<V2exModel>()

    fun setData(beans: ArrayList<V2exModel>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun setHandler(handler: V2exFragment.MyHandler) {
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_v2ex, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.index.text = position.toString()
        holder.title.text = beans[position].title
        holder.replies.text = beans[position].replies.toString()

        holder.itemView.setOnClickListener {
            val message = Message()
            message.what = V2exFragment.HANDLER_ITEM_CLICK
            message.obj = beans[position].url
            handler.sendMessage(message)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.index_tv
        val title: TextView = itemView.title_tv
        val replies: TextView = itemView.replies_tv
    }

}
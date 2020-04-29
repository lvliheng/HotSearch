package com.lvvi.hotsearch.ui.weibo

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvvi.hotsearch.R
import kotlinx.android.synthetic.main.item_weibo.view.*

class WeiboAdapter() :
    RecyclerView.Adapter<WeiboAdapter.ViewHolder>() {

    private lateinit var handler: WeiboFragment.MyHandler

    private var beans = ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean>()

    fun setData(beans: ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun setHandler(handler: WeiboFragment.MyHandler) {
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weibo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.index).load(beans[position].pic).into(holder.index)
        holder.title.text = beans[position].desc
        holder.descExtr.text = beans[position].desc_extr
        Glide.with(holder.emoji).load(beans[position].icon).into(holder.emoji)

        holder.itemView.setOnClickListener {
            val message = Message()
            message.what = WeiboFragment.HANDLER_ITEM_CLICK
            message.obj = beans[position].scheme
            handler.sendMessage(message)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: ImageView = itemView.index_iv
        val title: TextView = itemView.title_tv
        val descExtr: TextView = itemView.desc_extr_tv
        val emoji: ImageView = itemView.emoji_iv
    }

}
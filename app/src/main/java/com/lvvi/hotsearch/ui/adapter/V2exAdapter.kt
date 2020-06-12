package com.lvvi.hotsearch.ui.adapter

import android.app.Activity
import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.v2ex.V2exFragment
import com.lvvi.hotsearch.model.V2exModel
import kotlinx.android.synthetic.main.item_v2ex.view.*
import kotlinx.android.synthetic.main.item_weibo.view.title_tv

class V2exAdapter :
    RecyclerView.Adapter<V2exAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var activity: Activity

    private lateinit var handler: V2exFragment.MyHandler

    private var beans = ArrayList<V2exModel>()

    fun setActivity(activity: Activity) {
        this.activity = activity
    }

    fun setData(beans: ArrayList<V2exModel>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<V2exModel> {
        return beans
    }

    fun setHandler(handler: V2exFragment.MyHandler) {
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_v2ex, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.update.visibility = View.VISIBLE
            holder.update.text = context.getString(R.string.v2ex)
        } else {
            holder.update.visibility = View.GONE
        }

        holder.index.text = position.toString()
        holder.title.text = beans[position].title
        holder.replies.text = beans[position].replies.toString()

        holder.itemView.setOnClickListener {
            val message = Message()
            message.what = V2exFragment.HANDLER_ITEM_CLICK
            message.arg1 = position
            handler.handleMessage(message)
        }

        holder.itemView.setOnLongClickListener {
            val message = Message()
            message.what = V2exFragment.HANDLER_ITEM_LONG_CLICK
            message.obj = beans[position].url
            handler.handleMessage(message)
            true
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val update: TextView = itemView.update_time_tv
        val cardView: CardView = itemView.card_view
        val index: TextView = itemView.index_tv
        val title: TextView = itemView.title_tv
        val replies: TextView = itemView.replies_tv
    }

}
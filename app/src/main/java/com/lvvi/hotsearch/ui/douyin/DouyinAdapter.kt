package com.lvvi.hotsearch.ui.douyin

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lvvi.hotsearch.R
import kotlinx.android.synthetic.main.item_douyin.view.*

class DouyinAdapter() :
    RecyclerView.Adapter<DouyinAdapter.ViewHolder>() {

    private lateinit var context: Context

    private lateinit var handler: DouyinFragment.MyHandler

    private var beans = ArrayList<DouyinModel.AwemeListBean>()

    private val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(20))

    fun setData(beans: ArrayList<DouyinModel.AwemeListBean>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun setHandler(handler: DouyinFragment.MyHandler) {
        this.handler = handler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_douyin, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> holder.index.setTextColor(context.resources.getColor(R.color.others))
            1 -> holder.index.setTextColor(context.resources.getColor(R.color.others))
            2 -> holder.index.setTextColor(context.resources.getColor(R.color.others))
            else -> holder.index.setTextColor(context.resources.getColor(R.color.colorButtonBg))
        }
        holder.index.text = String.format(context.resources.getString(R.string.dot), position + 1)

        if (beans[position].aweme_info.video.cover.url_list.size > 0) {
            Glide.with(holder.cover).load(beans[position].aweme_info.video.cover.url_list[0])
                .apply(requestOptions)
                .into(holder.cover)
        } else {
            Glide.with(holder.cover).load(beans[position].aweme_info.video.dynamic_cover.url_list[0])
                .into(holder.cover)
        }

        holder.title.text = beans[position].aweme_info.desc
        holder.nickName.text = beans[position].aweme_info.author.nickname
        holder.hotValue.text = beans[position].hot_value.toString()

        holder.itemView.setOnClickListener {
            val message = Message()
            message.what = DouyinFragment.HANDLER_ITEM_CLICK
            message.obj = beans[position].aweme_info.video.play_addr.url_list[0]
            handler.sendMessage(message)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.index_tv
        val cover: ImageView = itemView.cover_iv
        val title: TextView = itemView.title_tv
        val nickName: TextView = itemView.nick_name_tv
        val hotValue: TextView = itemView.hot_value_tv
    }

}
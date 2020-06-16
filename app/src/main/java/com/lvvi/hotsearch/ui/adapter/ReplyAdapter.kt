package com.lvvi.hotsearch.ui.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.V2exReplyModel
import com.lvvi.hotsearch.model.V2exTopicModel
import com.lvvi.hotsearch.utils.Utils
import kotlinx.android.synthetic.main.item_reply.view.*

class ReplyAdapter: RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {

    private lateinit var context: Context

    private var header = V2exTopicModel()
    private var beans = ArrayList<V2exReplyModel>()

    private var screenWidth = 0
    private var imgWidth = 0

    fun setData(header: V2exTopicModel, beans: ArrayList<V2exReplyModel>) {
        this.header = header
        this.beans = beans
        notifyDataSetChanged()
    }

    fun getHeader(): V2exTopicModel {
        return header
    }

    fun setScreenWidth(screenWidth: Int) {
        this.screenWidth = screenWidth

        imgWidth = Utils.convertPixelsToDp(
            context, (screenWidth - Utils.convertDpToPixel(context, 20.toFloat())).toFloat())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reply, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            if (header.isHeaderShow) {
                setHeaderContent(holder)
                    holder.headerLayout.visibility = View.VISIBLE
            } else {
                holder.headerLayout.visibility = View.GONE
            }
        } else {
            holder.headerLayout.visibility = View.GONE
        }

        holder.name.text = beans[position].member.username
        holder.time.text = Utils.getDate(beans[position].created)
        holder.index.text = (position + 1).toString()

        holder.content.setText(
            Html.fromHtml(getHtml(beans[position].content_rendered)), TextView.BufferType.SPANNABLE)
    }

    private fun setHeaderContent(holder: ViewHolder) {
        holder.headerName.text = header.member.username
        holder.headerTime.text = Utils.getDate(header.created)
        if (header.content_rendered.isNullOrEmpty()) {
            holder.headerContent.visibility = View.GONE
        } else {
            holder.headerContent.setText(
                Html.fromHtml(getHtml(header.content_rendered)), TextView.BufferType.SPANNABLE)
            holder.headerContent.visibility = View.VISIBLE
        }
        holder.headerReplyCounts.text = String.format(
            context.getString(R.string.reply_counts),
            header.replies,
            Utils.getDate(header.last_touched)
        )
    }

    private fun getHtml(content: String): String {
        var text = content

        text = text.replace("<a", "<a style=\"color:#4289c6;font-weight:bold\"")
        text = text.replace("<span", "<span style=\"color:#4289c6;font-weight:bold\"")

        if (text.contains("<img")) {
            text = text.replace("<img", "<img width=\"$imgWidth\"")
        }

        return text.trim()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val headerLayout: LinearLayout = itemView.header_layout
        val headerName: TextView = itemView.header_name_tv
        val headerTime: TextView = itemView.header_time_tv
        val headerContent: TextView = itemView.header_content_tv
        val headerReplyCounts: TextView = itemView.header_reply_counts_tv
        val name: TextView = itemView.name_tv
        val time: TextView = itemView.time_tv
        val heart: ImageView = itemView.heart_iv
        val count: TextView = itemView.count_tv
        val index: TextView = itemView.index_tv
        val content: TextView = itemView.content_tv

    }

}
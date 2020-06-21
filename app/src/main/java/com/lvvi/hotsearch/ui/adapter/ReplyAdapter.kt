package com.lvvi.hotsearch.ui.adapter

import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.V2exReplyModel
import com.lvvi.hotsearch.model.V2exTopicModel
import com.lvvi.hotsearch.utils.Utils
import kotlinx.android.synthetic.main.item_reply.view.*
import kotlin.math.log

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
            holder.headerContentWebView.visibility = View.GONE
        } else {
//            holder.headerContent.setText(
//                Html.fromHtml(getHtml(header.content_rendered)), TextView.BufferType.SPANNABLE)
//            holder.headerContent.visibility = View.VISIBLE
            holder.headerContentWebView.webViewClient = MyWebviewClient(holder)
            holder.headerContentWebView.visibility = View.INVISIBLE

            holder.headerContentWebView.clearCache(true)
            holder.headerContentWebView.clearHistory()
            holder.headerContentWebView.loadDataWithBaseURL("https://www.v2ex.com/",
                getHtml(header.content_rendered), "text/html", "UTF-8", null)
        }
        holder.headerReplyCounts.text = String.format(
            context.getString(R.string.reply_counts),
            header.replies,
            Utils.getDate(header.last_touched)
        )
    }

    private fun getHtml(content: String): String {
        var text = content

        val header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"

        val body = "<body style=\"color:GhostWhite; background-color: #222C2F;\">"

        text = text.replace("<a", "<a style=\"color:#4289c6;font-weight:bold\"")
        text = text.replace("<span", "<span style=\"color:#4289c6;font-weight:bold\"")

        if (text.contains("<img")) {
            if (imgWidth == 0) {
                imgWidth = Utils.convertPixelsToDp(
                    context, (screenWidth - Utils.convertDpToPixel(context, 20.toFloat())).toFloat())
            }
            text = text.replace("<img", "<img width=\"$imgWidth\"")
        }
        return "$header$body$text</body>"
    }

    private inner class MyWebviewClient(holder: ViewHolder): WebViewClient() {
        val mHolder = holder
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            MyHandler(mHolder).sendEmptyMessageDelayed(HANDLER_SHOW_HEADER, 100)
        }
    }

    private class MyHandler(holder: ViewHolder): Handler() {
        val mHolder = holder
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                HANDLER_SHOW_HEADER -> {
                    mHolder.headerContentWebView.visibility = View.VISIBLE
                }
            }
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val headerLayout: LinearLayout = itemView.header_layout
        val headerName: TextView = itemView.header_name_tv
        val headerTime: TextView = itemView.header_time_tv
        val headerContentWebView: WebView = itemView.header_content_webview
        val headerContent: TextView = itemView.header_content_tv
        val headerReplyCounts: TextView = itemView.header_reply_counts_tv
        val name: TextView = itemView.name_tv
        val time: TextView = itemView.time_tv
        val heart: ImageView = itemView.heart_iv
        val count: TextView = itemView.count_tv
        val index: TextView = itemView.index_tv
        val content: TextView = itemView.content_tv

    }

    companion object {
        const val HANDLER_SHOW_HEADER = 0
    }
}
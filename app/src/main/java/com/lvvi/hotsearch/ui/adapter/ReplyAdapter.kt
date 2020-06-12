package com.lvvi.hotsearch.ui.adapter

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
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

class ReplyAdapter: RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {

    private lateinit var context: Context

    private var header = V2exTopicModel()
    private var beans = ArrayList<V2exReplyModel>()

    private var headerHeight = 0
    private var screenWidth = 0

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
//                if (headerHeight > 0) {
//                    startAnimation(holder, headerHeight)
//                } else {
//                    holder.headerLayout.clearAnimation()
//                    holder.headerLayout.visibility = View.VISIBLE
//                }
                    holder.headerLayout.visibility = View.VISIBLE
            } else {
//                holder.headerLayout.clearAnimation()
                holder.headerLayout.visibility = View.GONE
            }
        } else {
//            holder.headerLayout.clearAnimation()
            holder.headerLayout.visibility = View.GONE

//            if (holder.headerContent.height > 0 && headerHeight == 0) {
//                headerHeight = holder.mainLayout.height
//            }
        }

        holder.name.text = beans[position].member.username
        holder.time.text = Utils.getDate(beans[position].created)
        holder.index.text = (position + 1).toString()

        holder.content.text = beans[position].content
    }

    private fun setHeaderContent(holder: ViewHolder) {
        holder.headerName.text = header.member.username
        holder.headerTime.text = Utils.getDate(header.created)
        if (header.content_rendered.isNullOrEmpty()) {
            holder.headerContent.visibility = View.GONE
        } else {
            holder.headerContent.webViewClient = MyWebviewClient(holder)
            holder.headerContent.visibility = View.INVISIBLE

            holder.headerContent.loadDataWithBaseURL("https://www.v2ex.com/",
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

        val body = "<body style=\"color:GhostWhite; background-color: #222222;\">"

        text = text.replace("<a", "<a style=\"color:#4289c6;font-weight:bold\"")
        text = text.replace("<span", "<span style=\"color:#4289c6;font-weight:bold\"")

        val imgWidth = Utils.convertPixelsToDp(
            context, (screenWidth - Utils.convertDpToPixel(context, 20.toFloat())).toFloat())
        text = text.replace("<img", "<img width=\"$imgWidth\"")

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
                    Log.e("reply adapter", "handleMessage")
                    mHolder.headerContent.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun startAnimation(holder: ReplyAdapter.ViewHolder, headerHeight: Int) {
        Log.e("reply adapter", "startAnimation headerHeight: $headerHeight")
        val animator = ValueAnimator.ofFloat(0f, 1f)

        animator.duration = 1000
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener {
            holder.mainLayout.layoutParams.width = Utils.convertDpToPixel(context, 411.toFloat())
            holder.mainLayout.layoutParams.height = headerHeight * (it.animatedValue as Float).toInt()

            holder.mainLayout.requestLayout()
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.e("weibo adapter", "onAnimationRepeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.e("reply adapter", "onAnimationEnd height")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.e("weibo adapter", "onAnimationCancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.e("weibo adapter", "onAnimationStart")
                holder.headerLayout.visibility = View.VISIBLE
            }
        })

        animator.start()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mainLayout: LinearLayout = itemView.main_layout
        val headerLayout: LinearLayout = itemView.header_layout
        val headerName: TextView = itemView.header_name_tv
        val headerTime: TextView = itemView.header_time_tv
        val headerContent: WebView = itemView.header_content_webview
        val headerReplyCounts: TextView = itemView.header_reply_counts_tv
        val name: TextView = itemView.name_tv
        val time: TextView = itemView.time_tv
        val heart: ImageView = itemView.heart_iv
        val count: TextView = itemView.count_tv
        val index: TextView = itemView.index_tv
        val content: TextView = itemView.webview

    }

    companion object {
        const val HANDLER_SHOW_HEADER = 0
    }
}
package com.lvvi.hotsearch.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.douyin.DouyinFragment
import com.lvvi.hotsearch.model.DouyinModel
import com.lvvi.hotsearch.utils.Utils
import kotlinx.android.synthetic.main.item_douyin.view.*
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

class DouyinAdapter() :
    RecyclerView.Adapter<DouyinAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var activity: Activity

    private lateinit var handler: DouyinFragment.MyHandler

    private lateinit var updateTime: String
    private var beans = ArrayList<DouyinModel.AwemeListBean>()

    private var currPlayingPosition = 0

    private var timer = Timer()

    fun setData(beans: ArrayList<DouyinModel.AwemeListBean>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<DouyinModel.AwemeListBean> {
        return beans
    }

    fun setActivity(activity: Activity) {
        this.activity = activity
    }

    fun setUpdateTime(updateTime: String) {
        this.updateTime = updateTime
    }

    fun setHandler(handler: DouyinFragment.MyHandler) {
        this.handler = handler
    }

    fun getCurrPlayingPosition(): Int {
        return currPlayingPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_douyin, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    @SuppressLint("ClickableViewAccessibility", "SetJavaScriptEnabled")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.update.visibility = View.VISIBLE
            holder.update.text = updateTime
        } else {
            holder.update.visibility = View.GONE
        }
        when (position) {
            0 -> holder.index.setTextColor(ContextCompat.getColor(context, R.color.others))
            1 -> holder.index.setTextColor(ContextCompat.getColor(context, R.color.others))
            2 -> holder.index.setTextColor(ContextCompat.getColor(context, R.color.others))
            else -> holder.index.setTextColor(ContextCompat.getColor(context, R.color.colorButtonBg))
        }
        holder.index.text = String.format(context.resources.getString(R.string.dot), position + 1)

        val coverUrl = if (beans[position].aweme_info.video.cover.url_list.size > 0) {
            beans[position].aweme_info.video.cover.url_list[0].replace("300x400", "226x400")
        } else {
            beans[position].aweme_info.video.dynamic_cover.url_list[0]
        }

        Glide.with(holder.cover)
            .load(coverUrl)
            .placeholder(R.mipmap.default_pic)
            .into(holder.cover)

        holder.title.text = beans[position].aweme_info.desc
        holder.nickName.text = beans[position].aweme_info.author.nickname
        holder.hotValue.text = beans[position].hot_value.toString()

        holder.title.visibility = View.VISIBLE
        holder.nickName.visibility = View.VISIBLE
        holder.fire.visibility = View.VISIBLE
        holder.hotValue.visibility = View.VISIBLE

        holder.webView.settings.javaScriptEnabled = true

        val layoutParams = holder.video.layoutParams
        layoutParams.width = Utils.convertDpToPixel(context, 113.toFloat())
        layoutParams.height = Utils.convertDpToPixel(context, 200.toFloat())
        holder.video.layoutParams = layoutParams

        if (beans[position].aweme_info.video.isSelected) {
            prepareVideo(holder, position)
        } else {
            if (MyHandler(this).hasMessages(HANDLER_SHOW_VIDEO)) {
                MyHandler(this).removeMessages(HANDLER_SHOW_VIDEO)
            }
            hideVideo(holder)
        }

        holder.video.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    Log.e("douyin adapter", "videoView onTouch action up")
                    val message = Message()
                    message.what = DouyinFragment.HANDLER_ITEM_CLICK
                    message.arg1 = position
                    handler.handleMessage(message)
                }
            }
            true
        }

        holder.itemView.setOnClickListener {
            Log.e("douyin adapter", "itemView onClick isPlaying: ${holder.video.isPlaying} currentPosition: ${holder.video.currentPosition}")
            if (holder.video.isPlaying) {
                holder.video.pause()
                holder.play.visibility = View.VISIBLE
            } else {
                if (holder.video.visibility == View.VISIBLE && holder.video.currentPosition > 0) {
                    holder.video.start()
                    holder.play.visibility = View.GONE
                } else {
                    playNext(position)
                }
            }
        }

        holder.itemView.setOnLongClickListener {
            val message = Message()
            message.what = DouyinFragment.HANDLER_ITEM_LONG_CLICK
            message.obj = beans[position].aweme_info.video.play_addr.url_list[0]
            handler.handleMessage(message)
            true
        }
    }

    private fun prepareVideo(holder: ViewHolder, position: Int) {
        if (beans[position].aweme_info.video.url.isNullOrEmpty()) {
            holder.webView.webViewClient = MyWebviewClient(holder, position)
            holder.webView.loadUrl(beans[position].aweme_info.video.play_addr.url_list[0])
        } else {
            if (beans[position].aweme_info.video.isPlaying) {
                currPlayingPosition = position

                playVideo(holder, position)
            } else {
                if (holder.video.isPlaying) {
                    holder.video.pause()
                }
                holder.cover.visibility = View.VISIBLE
                holder.video.visibility = View.GONE
                holder.duration.visibility = View.GONE
                holder.play.visibility = View.VISIBLE
                holder.progressBar.visibility = View.GONE
            }
        }
    }

    private fun hideVideo(holder: ViewHolder) {
        if (holder.video.isPlaying) {
            holder.video.pause()
        }

        holder.cover.visibility = View.VISIBLE
        holder.video.visibility = View.GONE
        holder.duration.visibility = View.GONE
        holder.play.visibility = View.VISIBLE
        holder.progressBar.visibility = View.GONE
    }

    private fun playVideo(holder: ViewHolder, position: Int) {
        Log.e("douyin adapter", "playVideo currentPosition: ${holder.video.currentPosition}")
        if (holder.video.currentPosition > 0) {
            holder.video.start()
            return
        }

        holder.video.setOnPreparedListener {
            Log.e("douyin adapter", "OnPrepared")
            it.setVolume(0f, 0f)
            it.isLooping = true

            if (holder.video.currentPosition == 0 && beans[position].aweme_info.video.duration > 1000 * 60) {
                holder.duration.visibility = View.VISIBLE
                startDurationTimer(holder.duration, beans[position].aweme_info.video.duration)
            }

            if (it.videoWidth > it.videoHeight) {
                setHorizontalLayoutParams(holder)
            }

            val message = Message()
            message.what = HANDLER_SHOW_VIDEO
            message.obj = holder
            MyHandler(this).sendMessageDelayed(message, 300)
        }

        holder.video.setOnErrorListener { _, what, _ ->
            Log.e("douyin adapter", "OnError what: $what")
            if (holder.video.isPlaying) {
                holder.video.pause()
            }
            holder.webView.loadUrl(beans[position].aweme_info.video.play_addr.url_list[0])
            true
        }

        holder.video.setOnCompletionListener {
//            scrollToNext(position)
//            playNext(position + 1)
        }

        holder.video.setVideoURI(Uri.parse(beans[position].aweme_info.video.url))
        holder.video.requestFocus()
        holder.video.start()

        holder.duration.visibility = View.GONE
        holder.play.visibility = View.GONE
        holder.progressBar.visibility = View.VISIBLE
        holder.video.visibility = View.VISIBLE
    }

    class MyHandler(adapter: DouyinAdapter): Handler() {
        private val weakReference = WeakReference(adapter)
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                HANDLER_SHOW_VIDEO ->
                    weakReference.get()?.showVideo(msg.obj as ViewHolder)
            }
        }
    }

    private fun showVideo(holder: ViewHolder) {
        holder.progressBar.visibility = View.GONE
        holder.cover.visibility = View.GONE
    }

    private fun setHorizontalLayoutParams(holder: ViewHolder) {
        val layoutParams = holder.video.layoutParams
        layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT
        layoutParams.height = Utils.convertDpToPixel(context, 200.toFloat())
        holder.video.layoutParams = layoutParams

        holder.title.visibility = View.GONE
        holder.nickName.visibility = View.GONE
        holder.fire.visibility = View.GONE
        holder.hotValue.visibility = View.GONE
    }

    //unused
    private fun scrollToNext(position: Int) {
        val message = Message()
        message.what =
            DouyinFragment.HANDLER_ITEM_SCROLL_TO_NEXT
        message.arg1 = position
        handler.sendMessage(message)
    }

    private fun playNext(position: Int) {
        for ((index, bean) in beans.withIndex()) {
            bean.aweme_info.video.isSelected = false
            bean.aweme_info.video.isPlaying = false
            if (index == position) {
                bean.aweme_info.video.isSelected = true
                bean.aweme_info.video.isPlaying = true
            }
        }
        notifyDataSetChanged()
    }

    private fun startDurationTimer(textView: TextView, duration: Int) {
        var mDuration = duration
        when {
            mDuration == -1 -> {
                textView.text = context.getString(R.string.live)
            }
            mDuration > 0 -> {
                textView.text = Utils.getTime(mDuration.toLong())
            }
            else -> {
                textView.visibility = View.GONE
                return
            }
        }

        timer.cancel()
        timer = Timer()

        var timerTaskExecuteTimes = 0

        timer.schedule(object : TimerTask() {
            override fun run() {
                timerTaskExecuteTimes ++

                if (timerTaskExecuteTimes >= 5) {
                    timer.cancel()

                    activity.runOnUiThread {
                        textView.visibility = View.GONE
                    }
                } else {
                    if (mDuration > 1000) {
                        mDuration -= 1000
                        activity.runOnUiThread {
                            textView.text = Utils.getTime(mDuration.toLong())
                        }
                    }
                }
            }

        }, 1000, 1000)
    }

    inner class MyWebviewClient(holder: ViewHolder, position: Int): WebViewClient() {
        private val mHolder = holder
        private val mPosition = position
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.e("douyin adapter", "onPageStarted " +
                    "position: $mPosition isPlaying: ${beans[mPosition].aweme_info.video.isPlaying} " +
                    "isSameUrl: ${url != beans[mPosition].aweme_info.video.url} url: $url")
            if (url != beans[mPosition].aweme_info.video.url) {
                view?.loadUrl("javascript:document.getElementsByTagName(\"video\")[0].pause();")

                beans[mPosition].aweme_info.video.url = url
                if (beans[mPosition].aweme_info.video.isPlaying) {
                    playVideo(mHolder, mPosition)
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val update: TextView = itemView.update_time_tv
        val index: TextView = itemView.index_tv
        val cover: ImageView = itemView.cover_iv
        val title: TextView = itemView.title_tv
        val nickName: TextView = itemView.nick_name_tv
        val fire: ImageView = itemView.fire_iv
        val hotValue: TextView = itemView.hot_value_tv
        val webView: WebView = itemView.webview
        val video: VideoView = itemView.video_view
        val duration: TextView = itemView.duration_tv
        val play: Button = itemView.play_btn
        val progressBar: ProgressBar = itemView.progressBar
    }

    companion object {
        const val HANDLER_SHOW_VIDEO = 0
    }

}
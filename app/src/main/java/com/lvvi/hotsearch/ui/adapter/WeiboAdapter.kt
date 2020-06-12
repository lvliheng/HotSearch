package com.lvvi.hotsearch.ui.adapter

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Message
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.WeiboModel
import com.lvvi.hotsearch.ui.media.ImagePagerActivity
import com.lvvi.hotsearch.ui.media.VideoViewActivity
import com.lvvi.hotsearch.ui.weibo.WeiboFragment
import com.lvvi.hotsearch.utils.Constant
import com.lvvi.hotsearch.utils.Utils
import kotlinx.android.synthetic.main.item_weibo.view.*
import java.util.*
import kotlin.collections.ArrayList

class WeiboAdapter() :
    RecyclerView.Adapter<WeiboAdapter.ViewHolder>() {


    private lateinit var context: Context
    private lateinit var activity: Activity

    private lateinit var handler: WeiboFragment.MyHandler
    private lateinit var updateTime: String

    private var screenWidth: Int = -1

    private var beans = ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean>()

    private var lastExpandedPosition = -1
    private var currExpandedPosition = -1

    private var timer = Timer()

    fun setActivity(activity: Activity) {
        this.activity = activity
    }

    fun setData(beans: ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean>){
        this.beans = beans
        notifyDataSetChanged()
    }

    fun setUpdateTime(updateTime: String) {
        this.updateTime = updateTime
    }

    fun getData(): ArrayList<WeiboModel.DataBean.CardsBean.CardGroupBean> {
        return beans
    }

    fun setLastExpandedPosition(position: Int) {
        Log.e("weibo adapter", "lastExpandedPosition: $position")
        lastExpandedPosition = position
    }

    fun setCurrExpandedPosition(position: Int) {
        Log.e("weibo adapter", "currExpandedPosition: $position")
        currExpandedPosition = position
    }

    fun setHandler(handler: WeiboFragment.MyHandler) {
        this.handler = handler
    }

    fun setScreenWidth(screenWidth: Int) {
        this.screenWidth = screenWidth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weibo, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return beans.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.update.visibility = View.VISIBLE
            holder.update.text = updateTime
        } else {
            holder.update.visibility = View.GONE
        }

        Glide.with(holder.index)
            .load(beans[position].pic)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.index)

        val text = beans[position].desc
        holder.title.text = text
        holder.hotValue.text = beans[position].desc_extr

        holder.hotValue.measure(0, 0)
        holder.title.maxWidth =
            screenWidth - Utils.convertDpToPixel(context, 90.toFloat()) - holder.hotValue.measuredWidth

        Glide.with(holder.emoji)
            .load(beans[position].icon)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.emoji)

        holder.progressBar.visibility = View.GONE

        if (beans[position].isDetailShow == Constant.IS_TRUE) {
            Log.e("weibo adapter", "isDetailShow: $position")
            if (!beans[position].detailText.isNullOrEmpty()) {
                loadWebViewData(holder, position)
            }

            when (beans[position].type) {
                Constant.MEDIA_TYPE_WEBPAGE -> {
                    timer.cancel()
                    loadPics(holder, position)
                }
                Constant.MEDIA_TYPE_VIDEO -> {
                    loadVideo(holder, position)
                }
                Constant.MEDIA_TYPE_ARTICLE -> {
                    timer.cancel()
                    loadArticle(holder, position)
                }
                else -> {
                    timer.cancel()
                    holder.picLayout.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    holder.pic.visibility = View.GONE
                    holder.picCount.text = ""
                    holder.picCount.visibility = View.GONE
                    holder.video.visibility = View.GONE
                }
            }

            if (currExpandedPosition == position) {
                Log.e("weibo adapter", "----open animation---- $position")
                startAnimation(context, holder, false)

                currExpandedPosition = -1
            } else {
                holder.detailLayout.visibility = View.VISIBLE

                holder.main.clearAnimation()

                holder.main.layoutParams.width = screenWidth
                holder.main.layoutParams.height = Utils.convertDpToPixel(context, 250.toFloat())
                holder.main.setBackgroundColor(ContextCompat.getColor(context, R.color.item_pressed))
            }
        } else {
            holder.picCount.text = ""
            holder.picCount.visibility = View.GONE

            if (lastExpandedPosition == position) {
                Log.e("weibo adapter", "----close animation---- $position")
                startAnimation(context, holder, true)

                lastExpandedPosition = -1
            } else {
                holder.detailLayout.visibility = View.GONE

                if (holder.video.isPlaying) {
                    holder.video.pause()
                }
                holder.video.stopPlayback()
                holder.video.visibility = View.GONE

                holder.webView.visibility = View.INVISIBLE

                holder.main.clearAnimation()

                holder.main.layoutParams.width = screenWidth - Utils.convertDpToPixel(context, 20.toFloat())
                holder.main.layoutParams.height = Utils.convertDpToPixel(context, 40.toFloat())
                holder.main.setBackgroundColor(ContextCompat.getColor(context, R.color.main_bg))
            }
        }

        holder.mediaLayout.setOnClickListener {
            val intent = Intent()

            when (beans[position].type) {
                Constant.MEDIA_TYPE_WEBPAGE -> {
                    intent.setClass(context, ImagePagerActivity::class.java)
                    intent.putExtra(Constant.MEDIA_EXTRA_PICS, Gson().toJson(beans[position].pics))

                    if (beans[position].pics.size > 1) {
                        context.startActivity(intent)
                        (context as Activity).overridePendingTransition(R.anim.scale_in, R.anim.no_change)
                    } else {
                        holder.pic.transitionName = context.getString(R.string.pic_transition_name)

                        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity, holder.pic, holder.pic.transitionName)

                        context.startActivity(intent, optionsCompat.toBundle())
                    }
                }
                Constant.MEDIA_TYPE_VIDEO -> {
                    intent.setClass(context, VideoViewActivity::class.java)
                    intent.putExtra(Constant.MEDIA_EXTRA_VIDEO_URL, beans[position].videoUrl)
                    intent.putExtra(Constant.MEDIA_EXTRA_VIDEO_POSITION, holder.video.currentPosition)

                    holder.video.transitionName = beans[position].videoUrl
                    val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, holder.video, holder.video.transitionName)

                    context.startActivity(intent, optionsCompat.toBundle())
                }
                Constant.MEDIA_TYPE_ARTICLE -> {
                    Log.e("weibo adapter", "MEDIA_TYPE_ARTICLE: ${beans[position].objectId}")

                    intent.data = Uri.parse(beans[position].pageUrl)
                    intent.action = Intent.ACTION_VIEW
                    context.startActivity(intent)
                }
            }

        }

        holder.itemView.setOnClickListener {
            if (beans[position].isDetailShow != Constant.IS_TRUE) {
                holder.progressBar.visibility = View.VISIBLE
            }

            if (holder.video.isPlaying) {
                holder.video.pause()
            }
            holder.video.stopPlayback()

            val message = Message()
            message.what =
                WeiboFragment.HANDLER_ITEM_CLICK
            message.arg1 = position
            message.obj = beans[position].scheme
            handler.sendMessage(message)
        }

        holder.itemView.setOnLongClickListener {
            val message = Message()
            message.what =
                WeiboFragment.HANDLER_VIEW_IN_BROWSER
            message.obj = beans[position].scheme
            handler.sendMessage(message)

            true
        }
    }

    private fun loadWebViewData(holder: ViewHolder, position: Int) {
        holder.webView.webViewClient = MyWebViewClient(holder)

        val header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
        val body = "<body style=\"color:GhostWhite; background-color: #222C2F;\">"

        var text = beans[position].detailText.trim()
        text = text.replace("<a", "<a style=\"color:#4289c6;font-weight:bold\"")
        text = text.replace("<span", "<span style=\"color:#4289c6;font-weight:bold\"")
        text = text.replace("src=\"//", "src=\"https://")
        text = text.replace("src='https://", "src=\"https://")
        text = text.replace("src='//", "src=\"https://")
        text = text.replace(".png'>", ".png\">")
        text = text.replace("<br />", "")
        text = "$header$body$text</body>"

        holder.webView.loadDataWithBaseURL("https://m.weibo.cn", text, "text/html", "UTF-8", "")
    }

    private fun loadPics(holder: ViewHolder, position: Int) {
        holder.picLayout.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT

        if (beans[position].pics[0].url.endsWith(".gif")) {
            Log.e("weibo adapter", beans[position].pics[0].url)
            Glide.with(holder.pic)
                .asGif()
                .load(beans[position].pics[0].url)
                .into(holder.pic)
        } else {
            Glide.with(holder.pic)
                .load(beans[position].pics[0].url)
                .into(holder.pic)
        }

        if (beans[position].pics.size > 1) {
            holder.picCount.visibility = View.VISIBLE
            holder.picCount.text = String.format(context.resources.getString(R.string.pic_count),
                beans[position].pics.size - 1)
        } else {
            holder.picCount.text = ""
            holder.picCount.visibility = View.GONE
        }

        holder.pic.visibility = View.VISIBLE
        holder.video.visibility = View.GONE
    }

    private fun loadVideo(holder: ViewHolder, position: Int) {
        holder.video.setOnPreparedListener {
            it.setVolume(0f, 0f)
            it.isLooping = true

            if (it.videoWidth > 0 && it.videoHeight > 0) {
                holder.video.layoutParams.width =
                    it.videoWidth * Utils.convertDpToPixel(context, 200.toFloat()) / it.videoHeight
                holder.picLayout.layoutParams.width =
                    it.videoWidth * Utils.convertDpToPixel(context, 200.toFloat()) / it.videoHeight
            }

            if (holder.video.currentPosition == 0) {
                holder.picCount.visibility = View.VISIBLE
                startDurationTimer(holder.picCount, beans[position].duration * 1000)
            }

            holder.detailLayout.postDelayed({
                holder.detailLayout.fullScroll(HorizontalScrollView.FOCUS_RIGHT)

                if (!holder.video.isPlaying) {
                    holder.video.start()
                }
            }, 100L)
        }

        holder.video.setVideoURI(Uri.parse(beans[position].videoUrl))
        holder.video.requestFocus()
        holder.video.start()

        holder.pic.visibility = View.GONE
        holder.video.visibility = View.VISIBLE
    }

    private fun loadArticle(holder: ViewHolder, position: Int) {
        holder.picLayout.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT

        Glide.with(holder.pic)
            .load(beans[position].pagePic)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.pic)

        holder.picCount.visibility = View.VISIBLE
        holder.picCount.text = context.getString(R.string.article)

        holder.pic.visibility = View.VISIBLE
        holder.video.visibility = View.GONE
    }

    private fun startAnimation(context: Context, holder: ViewHolder, isExpanded: Boolean) {

        val animator = if (isExpanded) {
            ValueAnimator.ofFloat(1f, 0f)
        } else {
            ValueAnimator.ofFloat(0f, 1f)
        }

        animator.duration = 300
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener {
            val originalWidth = screenWidth - Utils.convertDpToPixel(context, 20.toFloat())
            val expandedWidth = screenWidth

            val originalHeight = Utils.convertDpToPixel(context, 40.toFloat())
            val expandedHeight = Utils.convertDpToPixel(context, 250.toFloat())

            holder.main.layoutParams.width =
                (originalWidth + (expandedWidth - originalWidth) * it.animatedValue as Float).toInt()
            holder.main.layoutParams.height =
                (originalHeight + (expandedHeight - originalHeight) * it.animatedValue as Float).toInt()

            holder.main.setBackgroundColor(ColorUtils.blendARGB(
                ContextCompat.getColor(context, R.color.main_bg),
                ContextCompat.getColor(context, R.color.item_pressed),
                it.animatedValue as Float))

            holder.main.requestLayout()
        }

        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.e("weibo adapter", "onAnimationRepeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (isExpanded) {
                    Log.e("weibo adapter", "onAnimationEnd detailView: GONE")
                    holder.detailLayout.visibility = View.GONE
                    holder.video.stopPlayback()
                    holder.video.visibility = View.GONE
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.e("weibo adapter", "onAnimationCancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                if (!isExpanded) {
                    Log.e("weibo adapter", "onAnimationEnd detailView: VISIBLE")
                    holder.detailLayout.visibility = View.VISIBLE
                }
            }
        })

        animator.start()
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
                textView.text = ""
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
                        textView.text = ""
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

    inner class MyWebViewClient(holder: ViewHolder): WebViewClient(){
        private val mHolder = holder

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.e("weibo adapter", "WebViewClient onPageFinished")
            mHolder.webView.visibility = View.VISIBLE
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val update: TextView = itemView.update_time_tv
        val main: LinearLayout = itemView.main_ll
        val index: ImageView = itemView.index_iv
        val title: TextView = itemView.title_tv
        val hotValue: TextView = itemView.hot_value_tv
        val emoji: ImageView = itemView.emoji_iv
        val detailLayout: HorizontalScrollView = itemView.detail_layout
        val webView: WebView = itemView.weibo_detail_webview
        val mediaLayout: RelativeLayout = itemView.media_layout
        val picLayout: RelativeLayout = itemView.pic_layout
        val pic: ImageView = itemView.pic_iv
        val picCount: TextView = itemView.pic_count_tv
        val video: VideoView = itemView.video_vv
        val progressBar: ProgressBar = itemView.progress_bar
    }

}
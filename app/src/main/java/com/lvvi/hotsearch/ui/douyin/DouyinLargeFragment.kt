package com.lvvi.hotsearch.ui.douyin

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import com.google.gson.Gson
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.DouyinModel
import com.lvvi.hotsearch.ui.main.BaseFragment
import com.lvvi.hotsearch.utils.Constant
import java.util.*


class DouyinLargeFragment : BaseFragment(), OnPreparedListener, MediaPlayer.OnErrorListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var videoView: VideoView
    private lateinit var webView: WebView
    private lateinit var title: TextView
    private lateinit var nickName: TextView
    private lateinit var cover: ImageView
    private lateinit var play: Button
    private lateinit var position: SeekBar


    private var bean = DouyinModel.AwemeListBean()

    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_douyin_large, container, false)
        initView(root)
        initData(arguments!!)
        return root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView(view: View) {
        val mainLayout: RelativeLayout = view.findViewById(R.id.main_layout)
        progressBar = view.findViewById(R.id.progressBar)
        videoView = view.findViewById(R.id.video_view)
        webView = view.findViewById(R.id.content_tv)
        cover = view.findViewById(R.id.cover_iv)
        title = view.findViewById(R.id.title_tv)
        nickName = view.findViewById(R.id.nick_name_tv)
        play = view.findViewById(R.id.play_btn)
        position = view.findViewById(R.id.position_seekbar)

        position.setPadding(0, 0, 0, 0)

        videoView.setOnPreparedListener(this)
        videoView.setOnErrorListener(this)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()

        mainLayout.setOnClickListener {
            if (videoView.visibility == View.VISIBLE) {
                if (videoView.isPlaying) {
                    videoView.pause()
                    play.visibility = View.VISIBLE
                } else {
                    videoView.start()
                    play.visibility = View.GONE
                }
            }
        }
    }

    private fun initData(bundle: Bundle) {
        val beanString = bundle.getString(Constant.DOUYIN_EXTRA_BEAN, "")

        bean = Gson().fromJson(beanString, DouyinModel.AwemeListBean::class.java)

        title.text = bean.aweme_info.desc
        nickName.text = "@${bean.aweme_info.author.nickname}"

//        val displayMetrics = DisplayMetrics()
//        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
//
//        val imgSize = "${displayMetrics.widthPixels}x${displayMetrics.widthPixels * 400 / 300}"
//
//        val coverUrl = if (bean.aweme_info.video.cover.url_list.size > 0) {
//            bean.aweme_info.video.cover.url_list[0].replace("300x400", imgSize)
//        } else {
//            bean.aweme_info.video.dynamic_cover.url_list[0]
//        }
//
//        Glide.with(cover)
//            .load(coverUrl)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .thumbnail(Glide.with(context!!).load(bean.aweme_info.video.cover.url_list[0]))
//            .into(cover)

        prepareVideo()
    }

    private fun prepareVideo() {
        if (bean.aweme_info.video.url.isNullOrEmpty()) {
            webView.clearCache(true)
            webView.clearHistory()
            webView.loadUrl(bean.aweme_info.video.play_addr.url_list[0].replace("playwm", "play"))
        } else {
            playVideo()
        }
    }

    private fun playVideo() {
        videoView.setVideoURI(Uri.parse(bean.aweme_info.video.url))
        videoView.requestFocus()

        play.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        videoView.visibility = View.VISIBLE
    }

    inner class MyWebViewClient(): WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            val titleStr = if (bean.aweme_info != null) {
                bean.aweme_info.author.nickname
            } else {
                ""
            }
            Log.e("douyin large fragment", "onPageStarted title: $titleStr")
            view?.loadUrl("javascript:document.getElementsByTagName(\"video\")[0].pause();")

            if (url != bean.aweme_info.video.url) {
                bean.aweme_info.video.url = url
                playVideo()
            }
        }
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        val titleStr = if (bean.aweme_info != null) {
            bean.aweme_info.author.nickname
        } else {
            ""
        }
        Log.i("douyin large fragment", "onPrepared title: $titleStr")
        mediaPlayer.isLooping = true

        mediaPlayer.start()

        progressBar.visibility = View.GONE
        cover.visibility = View.GONE
        if (videoView.visibility == View.GONE) {
            videoView.visibility = View.VISIBLE
        }

        if (!bean.aweme_info.video.isPlaying) {
            mediaPlayer.pause()
        }
    }

    private fun startTimer() {
        if (timer != null) {
            timer?.cancel()
        }

        timer = Timer()

        timer?.schedule(object : TimerTask() {
            override fun run() {
                if (videoView.isPlaying && videoView.currentPosition > 0) {
                    if (videoView.duration < 1000 * 60) {
                        timer?.cancel()
                    } else {
                        position.progress = videoView.currentPosition * 100 / videoView.duration * 100 / 100
                    }
                }
            }
        }, 100, 100)
    }

    override fun onError(mediaPlayer: MediaPlayer, i: Int, i1: Int): Boolean {
        Log.e("douyin large fragment", "onError: i: $i i1: $i1")
        webView.clearCache(true)
        webView.clearHistory()
        webView.loadUrl(bean.aweme_info.video.play_addr.url_list[0].replace("playwm", "play"))
        return false
    }

    override fun onResume() {
        val titleStr = if (bean.aweme_info != null) {
            bean.aweme_info.author.nickname
        } else {
            ""
        }
        Log.e("douyin large fragment", "onResume title: $titleStr")
        if (bean.aweme_info != null) {
            bean.aweme_info.video.isPlaying = true
        }

        Log.e("douyin large fragment", "onResume videoView.visibility: ${videoView.visibility} isPlaying: ${videoView.isPlaying}")
        if (videoView.visibility == View.GONE) {
            videoView.visibility = View.VISIBLE
        } else {
            if (!videoView.isPlaying) {
                videoView.requestFocus()
                videoView.start()
            }
        }

        if (play.visibility == View.VISIBLE) {
            play.visibility = View.GONE
        }

        startTimer()

        super.onResume()
    }

    override fun onPause() {
        val titleStr = if (bean.aweme_info != null) {
            bean.aweme_info.author.nickname
        } else {
            ""
        }
        Log.e("douyin large fragment", "onPause title: $titleStr")

        bean.aweme_info.video.isPlaying = false

        if (videoView.isPlaying) {
            videoView.seekTo(0)
            videoView.pause()
        }

        videoView.visibility = View.GONE

        //直接隐藏的话，返回上一视频会黑屏卡顿；不隐藏的话，切换到下一视频时会显示上一视频的画面
        MyHandler(videoView).sendEmptyMessageDelayed(HANDLER_SHOW_VIDEO_VIEW, 300)

        if (timer != null) {
            timer?.cancel()
        }
        position.progress = 0

        super.onPause()
    }

    override fun onDestroy() {
        Log.e("douyin large fragment", "onDestroy")
        webView.destroy()
        videoView.stopPlayback()
        super.onDestroy()
    }

    class MyHandler(videoView: VideoView): Handler() {
        private val mVideoView = videoView

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                HANDLER_SHOW_VIDEO_VIEW -> mVideoView.visibility = View.VISIBLE
            }
        }
    }

    fun play() {
        val titleStr = if (bean.aweme_info != null) {
            bean.aweme_info.author.nickname
        } else {
            ""
        }
        Log.e("douyin large fragment", "play title: $titleStr videoView isVisible: ${videoView.visibility == View.VISIBLE}")
        if (bean.aweme_info != null) {
            bean.aweme_info.video.isPlaying = true

            if (videoView.visibility == View.VISIBLE) {
                videoView.start()
            } else {
                playVideo()
            }
        }
    }

    companion object {

        const val HANDLER_SHOW_VIDEO_VIEW = 0

        fun newInstance(beanString: String): DouyinLargeFragment {
            return DouyinLargeFragment().apply {
                arguments = Bundle().apply {
                    putString(Constant.DOUYIN_EXTRA_BEAN, beanString)
                }
            }
        }
    }
}
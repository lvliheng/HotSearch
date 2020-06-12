package com.lvvi.hotsearch.ui.media

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.main.BaseActivity
import com.lvvi.hotsearch.ui.view.MyRelativeLayout
import com.lvvi.hotsearch.utils.Constant


class VideoViewActivity : BaseActivity(), OnCompletionListener, OnPreparedListener,
    MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MyRelativeLayout.MyRelativeLayoutCallBack {

    private lateinit var mainRl: MyRelativeLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var videoView: VideoView

    private var originalY: Float = -1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        Log.e("video view activity", "onCreate")
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        window.enterTransition = null

        initView()
        initData()
    }

    private fun initView() {
        mainRl = findViewById(R.id.main_my_rl)
        progressBar = findViewById(R.id.progressBar)
        videoView = findViewById(R.id.video_view)

        mainRl.setCallBack(this)
        videoView.setOnCompletionListener(this)
        videoView.setOnPreparedListener(this)
        videoView.setOnErrorListener(this)
        videoView.setOnInfoListener(this)
    }

    private fun initData() {
        mainRl.background.alpha = 255

        var url = ""
        var position = 0
        val bundle = intent.extras
        if (bundle != null) {
            url = bundle.getString(Constant.MEDIA_EXTRA_VIDEO_URL, "")
            position = bundle.getInt(Constant.MEDIA_EXTRA_VIDEO_POSITION, 0)
        }

        Log.e("video view activity", "position: $position")

        videoView.transitionName = url

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)

        videoView.setVideoURI(Uri.parse(url))
        videoView.requestFocus()

        if (position > 0) {
            videoView.seekTo(position)
        } else {
            videoView.seekTo(10)
        }

        videoView.start()
    }

    override fun setAlpha(alpha: Float) {
        mainRl.background.alpha = (alpha * 255).toInt()
    }

    override fun setMovedY(y: Int) {
        if (originalY == -1f) {
            originalY = videoView.y
        }
        videoView.y = originalY - y
    }

    override fun onDismiss() {
        onBackPressed()
    }

    override fun onCancel() {
        Log.e("video view activity", "onCancel")
    }

    override fun onCompletion(mediaPlayer: MediaPlayer) {
        Log.i("video view activity", "onCompletion")
    }

    override fun onPrepared(mediaPlayer: MediaPlayer) {
        Log.i("video view activity", "onPrepared")
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
        }

        mediaPlayer.isLooping = true

        videoView.postDelayed({
            originalY = videoView.y
            Log.e("video view activity", "originalY: $originalY")
        }, 400L)
    }

    override fun onError(mediaPlayer: MediaPlayer, i: Int, i1: Int): Boolean {
        Log.e("video view activity", "onError: i: $i i1: $i1")
        Toast.makeText(this, "$i   $i1", Toast.LENGTH_SHORT).show()
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
        }
        return false
    }

    override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Log.e("video view activity", "onInfo: what: $what extra: $extra")

        return true
    }

    override fun onPause() {
        Log.e("video view activity", "onPause currPosition: ${videoView.currentPosition}")
        if (videoView.isPlaying) {
            videoView.pause()
        }
        super.onPause()
    }

    override fun onDestroy() {
        Log.e("video view activity", "onDestroy")
        videoView.stopPlayback()
        super.onDestroy()
    }

}
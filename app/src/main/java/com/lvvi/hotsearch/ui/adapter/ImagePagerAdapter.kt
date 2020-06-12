package com.lvvi.hotsearch.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.Transition
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.model.WeiboDetailModel.DataBean.CardsBean.MblogBean.PicsBean
import com.lvvi.hotsearch.ui.media.ImagePagerActivity
import com.lvvi.hotsearch.ui.view.MyScrollView
import me.everything.android.ui.overscroll.IOverScrollState
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator
import me.everything.android.ui.overscroll.adapters.ScrollViewOverScrollDecorAdapter
import kotlin.math.abs

class ImagePagerAdapter : PagerAdapter() {

    private lateinit var images: List<PicsBean>

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    private var targets = ArrayList<SimpleTarget<Bitmap>>()

    private lateinit var handler: ImagePagerActivity.MyHandler

    private lateinit var viewPager: ViewPager

    fun setData(images: List<PicsBean>) {
        this.images = images
        notifyDataSetChanged()
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
    }

    fun setScreenSize(width: Int, height: Int) {
        screenWidth = width
        screenHeight = height
    }

    fun setHandler(handler: ImagePagerActivity.MyHandler) {
        this.handler = handler
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.item_image_pager, container, false)

        val main = view.findViewById<RelativeLayout>(R.id.main_layout)
        val scrollView = view.findViewById<MyScrollView>(R.id.scroll_view)
        val imageLayout = view.findViewById<LinearLayout>(R.id.image_layout)
        val imageIv = view.findViewById<ImageView>(R.id.image_iv)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        if (main.background != null) {
            main.background.alpha = 255
        }

        if (position == 0) {
            imageIv.transitionName = container.context.getString(R.string.pic_transition_name)
        } else {
            imageIv.transitionName = images[position].url
        }

        if (images[position].large.geo != null &&
            !images[position].large.geo.width.isNullOrEmpty() &&
            !images[position].large.geo.height.isNullOrEmpty()
        ) {
            imageIv.layoutParams.width = screenWidth
            imageIv.layoutParams.height =
                images[position].large.geo.height.toInt() * screenWidth / images[position].large.geo.width.toInt()

            val params = if (imageIv.layoutParams.height > screenHeight) {
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.NO_GRAVITY
                }
            } else {
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
            imageLayout.layoutParams = params
        }

        if (images[position].large.url.endsWith("gif")) {
            Log.e("image pager adapter", images[position].large.url)
            Glide.with(container.context)
                .asGif()
                .load(images[position].large.url)
                .thumbnail(Glide.with(container.context).asGif().load(images[position].url))
                .into(imageIv)
        } else {
            Glide.with(container.context)
                .load(images[position].large.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(Glide.with(container.context).load(images[position].url))
                .override(Target.SIZE_ORIGINAL)
                .fitCenter()
                .into(imageIv)
        }

        scrollView.setClickCallBack(object : MyScrollView.ClickCallBack{
            override fun onCLick() {
                handler.sendEmptyMessage(ImagePagerActivity.HANDLER_DISMISS)
            }
        })

        OverScrollDecoratorHelper.setUpOverScroll(scrollView)
        val decor = VerticalOverScrollBounceEffectDecorator(ScrollViewOverScrollDecorAdapter(scrollView))

        var needDismiss = false
        decor.setOverScrollUpdateListener { decor, state, offset ->
            if (255 - (abs(offset) / 2 % 255).toInt() < 90) {
                main.background.alpha = 90
            } else {
                main.background.alpha = 255 - (abs(offset) / 2 % 255).toInt()
            }

            needDismiss = abs(offset) > 120
        }

        decor.setOverScrollStateListener { decor, oldState, newState ->
            when (newState) {
                IOverScrollState.STATE_BOUNCE_BACK -> {
                    if (needDismiss) {
                        handler.sendEmptyMessage(ImagePagerActivity.HANDLER_DISMISS)
                    }
                }
                else -> {
                    scrollView.requestDisallowInterceptTouchEvent(true)
                    viewPager.requestDisallowInterceptTouchEvent(false)
                }
            }
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        clearTarget(container.context, position)
        container.removeView(`object` as RelativeLayout)
    }

    private fun clearTarget(context: Context, position: Int) {
        for ((index, target) in targets.withIndex()) {
            if (index == position) {
                Glide.with(context).clear(target)
            }
        }
    }

}
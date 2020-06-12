package com.lvvi.hotsearch.ui.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ScrollView
import kotlin.math.abs

class MyScrollView: ScrollView {


    private lateinit var clickCallBack: ClickCallBack


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setClickCallBack(clickCallBack: ClickCallBack) {
        this.clickCallBack = clickCallBack
    }

    private var startX = 0f
    private var startY = 0f


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
                if (abs(startX - ev.x) == 0f && abs(startY - ev.y) == 0f) {
                    clickCallBack.onCLick()
                }
                startX = 0f
                startY = 0f
            }
        }
        return super.onTouchEvent(ev)
    }

    interface ClickCallBack {
        fun onCLick()
    }
}
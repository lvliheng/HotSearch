package com.lvvi.hotsearch.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.RelativeLayout
import kotlin.math.abs

class MyRelativeLayout : RelativeLayout {


    private lateinit var callBack: MyRelativeLayoutCallBack
    private var viewHeight = 0f

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun setCallBack(callBack: MyRelativeLayoutCallBack) {
        this.callBack = callBack
    }

    fun setViewHeight(height: Float) {
        viewHeight = height
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("my relative layout", "onTouchEvent action: ${event?.action}")
        performClick()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> touchDown(event)
            MotionEvent.ACTION_MOVE -> touchMove(event)
            MotionEvent.ACTION_UP -> touchUP(event)
        }
        return true
//        return super.onTouchEvent(event)
    }

    private var startY: Float? = 0f
    private val originalY = y
    private var needDismiss = false

    private fun touchDown(event: MotionEvent?) {
        startY = event?.y
    }

    private fun touchMove(event: MotionEvent?) {
        if (event != null) {
            if (abs(startY!!.minus(event.y)) > 50) {
                val movedDistance = if (startY!!.minus(event.y) > 0) {
                    ((abs(startY!!.minus(event.y)) - 50).toInt() / 2)
                } else {
                    ((abs(startY!!.minus(event.y)) - 50).toInt() / 2) * -1
                }

                Log.e("my relative layout", "movedDistance: $movedDistance")

                val mAlpha: Float
                if (abs(startY!!.minus(event.y)) / 2 > 200) {
                    needDismiss = true
                    mAlpha = 0.5f
                } else {
                    needDismiss = false
                    mAlpha = if ((225 - abs(startY!!.minus(event.y)) / 2) / 200 < 0.3f) {
                        0.5f
                    } else {
                        (225 - abs(startY!!.minus(event.y)) / 2) / 200
                    }
                }
                callBack.setAlpha(mAlpha)
                callBack.setMovedY(movedDistance)
            }
        }
    }

    private fun touchUP(event: MotionEvent?) {
        if (needDismiss) {
            callBack.onDismiss()
            needDismiss = false
        } else {
            callBack.setAlpha(1f)
            callBack.setMovedY(0)
        }
    }

    interface MyRelativeLayoutCallBack {
        fun setAlpha(alpha: Float)
        fun setMovedY(y: Int)
        fun onDismiss()
        fun onCancel()
    }
}
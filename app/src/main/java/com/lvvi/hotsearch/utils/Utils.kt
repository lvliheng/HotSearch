package com.lvvi.hotsearch.utils

import android.content.Context
import android.net.Uri
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import com.lvvi.hotsearch.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

object Utils {


    fun convertDpToPixel(context: Context, dp: Float): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val px =
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return px.toInt()
    }

    fun convertPixelsToDp(context: Context, px: Float): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        val dp =
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        return dp.toInt()
    }

    fun getTime(duration: Long): String {
        when {
            duration >= 60 * 60 * 1000 -> {
                return String.format(
                    "%d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(duration),
                    TimeUnit.MILLISECONDS.toMinutes(duration) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1)
                )
            }
            duration >= 60 * 1000 -> {
                return String.format(
                    "%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1)
                )
            }
            else -> {
                return String.format(
                    "0:%02d",
                    duration / 1000
                )
            }
        }
    }

    fun getDate(time: Int): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return simpleDateFormat.format(Date(time.toLong() * 1000))
    }

}
package com.lvvi.hotsearch.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.umeng.analytics.MobclickAgent

open class BaseActivity: AppCompatActivity() {



    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}
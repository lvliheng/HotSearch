package com.lvvi.hotsearch.utils

import android.app.Application
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure


class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        UMConfigure.init(this, "5eda4621895cca8bf0000171", "UMENG", UMConfigure.DEVICE_TYPE_PHONE, null)

        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO)

        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true)
    }

}
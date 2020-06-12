package com.lvvi.hotsearch.ui.media

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lvvi.hotsearch.R
import com.lvvi.hotsearch.ui.main.BaseActivity
import com.lvvi.hotsearch.utils.Constant

class WebviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        window.enterTransition = null

        init()
    }

    private fun init() {
        val webView = findViewById<WebView>(R.id.webview)

        webView.webViewClient = MyWebviewClient()

        var url = ""
        val bundle = intent.extras
        if (bundle != null) {
            url = bundle.getString(Constant.MEDIA_EXTRA_WEBVIEW_URL, "")
        }

        webView.loadUrl(url)
    }

    class MyWebviewClient: WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.e("webview activity", "onPageStarted url: $url")
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            Log.e("webview activity", "onPageFinished url: $url")
            super.onPageFinished(view, url)
        }
    }

}

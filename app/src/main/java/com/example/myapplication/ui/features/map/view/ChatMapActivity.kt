package com.example.myapplication.ui.features.map.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChatMapBinding
import com.example.myapplication.ui.base.activity.BaseActivity
import com.example.myapplication.ui.base.utils.visible
import com.example.myapplication.ui.features.map.di.DaggerChatMapActivityComponent

class ChatMapActivity : BaseActivity<ActivityChatMapBinding>() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Startup Land"

        binding.webview.settings.apply {
            javaScriptEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
        binding.webview.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val categoryId = request?.url?.toString()?.substringAfter("=")
                categoryId?.let { id ->
                    router.navigateChatListActivityByCategory(this@ChatMapActivity, id)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showProgress(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                showProgress(false)
            }
        }
        binding.webview.loadUrl("https://astroreptiloids.github.io/map/")
        showProgress(false)
    }

    override val layoutResId: Int
        get() = R.layout.activity_chat_map

    override fun injectDependencies() {
        DaggerChatMapActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(this)
    }

    private fun showProgress(progress: Boolean) {
        binding.progress.visible(progress)
        binding.webview.visible(progress.not())
    }
}
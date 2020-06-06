package com.example.myapplication.ui.features.menu.view

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.ui.base.activity.BaseActivity
import com.example.myapplication.ui.features.menu.di.DaggerMenuActivityComponent

class MenuActivity : BaseActivity<ActivityMenuBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.chatMapButton.setOnClickListener { router.navigateChatMap(this) }
    }

    override val layoutResId: Int
        get() = R.layout.activity_menu

    override fun injectDependencies() {
        DaggerMenuActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
            .inject(this)
    }
}
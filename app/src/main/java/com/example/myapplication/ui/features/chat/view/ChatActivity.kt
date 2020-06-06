package com.example.myapplication.ui.features.chat.view

import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityChatBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.chat.di.ChatActivityModule
import com.example.myapplication.ui.features.chat.di.DaggerChatActivityComponent
import com.example.myapplication.ui.features.chat.presenter.IChatActivityPresenter

class ChatActivity : BaseMvpActivity<ActivityChatBinding, IChatView, IChatActivityPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("wtf","khgvjh")

    }

    override val layoutResId: Int
        get() = R.layout.activity_chat

    override fun injectDependencies() {
        DaggerChatActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .chatActivityModule(ChatActivityModule())
            .build().inject(this)
    }

    override fun hideProgress() {

    }

    override fun showProgress() {

    }
}
package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.data.network.repository.IRepository
import javax.inject.Inject

class ChatListActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatListActivityPresenter() {

    override fun createChat() {
        // API: create chat
    }

    override fun openChat() {

    }


}
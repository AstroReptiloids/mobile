package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.data.network.repository.IRepository
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatActivityPresenter() {


    override fun sendMessage(text: String) {

    }

    fun createChat() {

    }
}
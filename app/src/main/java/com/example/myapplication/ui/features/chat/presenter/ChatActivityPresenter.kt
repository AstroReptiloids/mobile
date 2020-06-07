package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.data.network.repository.IRepository
import com.example.myapplication.ui.features.chat.data.Message
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatActivityPresenter() {


    override fun sendMessage(message: Message) {
// API: send message

        repository.sendMessage(message.text, message.id)
    }

    override fun getMessages(microchatId: String) {
        runAsync(repository.getMessages(microchatId), {
            view?.showChats(it)
        }, {

        }, true)
    }
}
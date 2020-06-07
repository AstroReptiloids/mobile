package com.example.myapplication.ui.features.chat.presenter

import android.util.Log
import com.example.myapplication.data.network.repository.IRepository
import com.example.myapplication.ui.features.chat.data.Message
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatActivityPresenter() {


    override fun sendMessage(message: Message) {
// API: send message

    }

    override fun getMessages(microchatId: String) {
        runAsync(repository.getMessages(microchatId), {
            view?.showChats(it)
        }, {
            Log.i("wtf", "error $it")
        }, true)
    }
}
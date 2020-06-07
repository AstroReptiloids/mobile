package com.example.myapplication.ui.features.chat.presenter

import android.util.Log
import com.example.myapplication.data.network.repository.IRepository
import com.example.myapplication.ui.features.chat.data.Message
import com.example.myapplication.ui.features.chat.view.IChatView
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatActivityPresenter() {


    override fun sendMessage(message: Message) {
        runAsync(repository.sendMessage(
            message.text,
            message.microchatBO?.id ?: (message.messageBO?.microchatId ?: "")
        ),
            {
                //view?.showMessage(it)
            }
            , {
                view?.showToast("Govno ${it.message}")

            })
    }

    override fun getMessages(microchatId: String) {
        runAsync(repository.getMessages(microchatId), {
            view?.showChats(it)
        }, {
            Log.i("wtf", "error $it")
        }, true)

        runAsync(repository.observeNewMessages().filter { it.microchatId == microchatId }, {
            view?.showMessage(it)
        })
    }
}
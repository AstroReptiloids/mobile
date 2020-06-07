package com.example.myapplication.ui.features.chat.presenter

import android.util.Log
import com.example.myapplication.data.network.repository.IRepository
import javax.inject.Inject

class ChatListActivityPresenter @Inject constructor(
    private val repository: IRepository
) : IChatListActivityPresenter() {


    override fun onCreate(categoryId: String?) {
        runAsync(repository.getMicrochats(null, categoryId), {
            view?.showChats(it)
        }, {

        })
    }

    override fun createChat(
        title: String,
        description: String,
        parentId: String?,
        categoryId: String?
    ) {
        runAsync(repository.createMicrochat(title, description, parentId, categoryId), {
            view?.showToast("Заебок")
            view?.openCreatedChat(it)
        }, {
            view?.showToast("Не заебок")
        }, true)
    }

    override fun getChatAndOpen(id: String) {
        runAsync(repository.getMicrochats(id), {
            view?.showToast("Заебок")
            view?.openCreatedChat(it.first())
        }, {
            view?.showToast("Не заебок")
        }, true)
    }

    override fun openChat() {

    }


}
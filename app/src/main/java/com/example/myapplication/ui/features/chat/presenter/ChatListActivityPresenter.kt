package com.example.myapplication.ui.features.chat.presenter

import android.util.Log
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
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
            view?.openCreatedChat(it)
        }, {

        }, true)
    }

    override fun getChatAndOpen(microchatBO: MicrochatBO?) {
        if(microchatBO == null){
            view?.showToast("microchatBO null")
            return
        }
        view?.openCreatedChat(microchatBO)
    }

    override fun openChat() {

    }


}
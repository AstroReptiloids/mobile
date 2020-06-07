package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.ui.base.presenter.BasePresenter
import com.example.myapplication.ui.features.chat.view.IChatListView
import com.example.myapplication.ui.features.chat.view.IChatView

abstract class IChatListActivityPresenter : BasePresenter<IChatListView>(){

    abstract fun createChat(title: String,
                            description: String,
                            parentId: String? = null,
                            categoryId: String? = null)

    abstract fun getChatAndOpen(id: String)

    abstract fun openChat()

    abstract fun onCreate(categoryId: String?)
}
package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.ui.base.presenter.BasePresenter
import com.example.myapplication.ui.features.chat.view.IChatListView
import com.example.myapplication.ui.features.chat.view.IChatView

abstract class IChatListActivityPresenter : BasePresenter<IChatListView>(){

    abstract fun createChat()

    abstract fun openChat()
}
package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.ui.base.presenter.BasePresenter
import com.example.myapplication.ui.features.chat.view.IChatView

abstract class IChatActivityPresenter : BasePresenter<IChatView>(){

    abstract fun sendMessage(text: String)
}
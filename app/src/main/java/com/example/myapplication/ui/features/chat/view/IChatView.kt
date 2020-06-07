package com.example.myapplication.ui.features.chat.view

import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.ui.base.view.IView

interface IChatView : IView {

    fun showChats(list: List<MessageBO>)

    fun showMessage(messageBO: MessageBO)
}
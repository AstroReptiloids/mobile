package com.example.myapplication.ui.features.chat.view

import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.ui.base.view.IView

interface IChatListView : IView {

    abstract fun showChats(list: List<MicrochatBO>)

    abstract fun openCreatedChat(chatBO: MicrochatBO)
}
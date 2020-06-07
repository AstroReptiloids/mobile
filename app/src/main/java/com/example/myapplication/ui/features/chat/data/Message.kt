package com.example.myapplication.ui.features.chat.data

import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO

class Message(
    val messageBO: MessageBO?,
    val microchatBO: MicrochatBO?,
    val text: String,
    val memberData: MemberData,
    val isBelongsToCurrentUser: Boolean,
    val isChat: Boolean,
    var fireCount: Int = 0,
    var peoplesCount: Int = 0,
    var forkCount: Int = 0
)
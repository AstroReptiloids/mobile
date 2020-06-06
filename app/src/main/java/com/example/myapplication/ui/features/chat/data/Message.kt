package com.example.myapplication.ui.features.chat.data

class Message(
    val text: String,
    val memberData: MemberData,
    val isBelongsToCurrentUser: Boolean,
    val isChat: Boolean,
    var fireCount: Int = 0,
    var peoplesCount: Int = 0,
    var forkCount: Int = 0
)
package com.example.myapplication.ui.features.chat.data

class Message(
    val text: String,
    val memberData: MemberData,
    val isBelongsToCurrentUser: Boolean
)
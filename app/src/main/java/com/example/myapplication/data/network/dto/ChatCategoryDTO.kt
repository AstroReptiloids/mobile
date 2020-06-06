package com.example.myapplication.data.network.dto

import com.example.myapplication.data.model.ChatCategoryBO

data class ChatCategoryDTO(
    val id: String,
    val name: String
) {

    fun toBO(): ChatCategoryBO = ChatCategoryBO(
        id = id,
        name = name
    )
}
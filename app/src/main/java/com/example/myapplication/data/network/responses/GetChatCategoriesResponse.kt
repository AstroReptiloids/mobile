package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.ChatCategoryDTO

class GetChatCategoriesResponse(
    val data: List<ChatCategoryDTO>?
) : BaseResponse()
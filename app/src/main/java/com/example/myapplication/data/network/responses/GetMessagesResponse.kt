package com.example.myapplication.data.network.responses

import com.example.myapplication.data.network.dto.MessageDTO

data class GetMessagesResponse(
    val data: List<MessageDTO>?
) : BaseResponse()
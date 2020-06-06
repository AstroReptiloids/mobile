package com.example.myapplication.data.network.dto

import com.example.myapplication.data.model.MessageBO
import com.google.gson.annotations.SerializedName

data class MessageDTO(
    val id: String,
    @SerializedName("microchat_id")
    val microchatId: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("reference_id")
    val referenceId: String?,
    val timestamp: String?,
    val text: String
) {

    fun toBO(): MessageBO = MessageBO(
        id = id,
        microchatId = microchatId,
        userId = userId,
        referenceId = referenceId ?: "",
        timestamp = timestamp ?: "",
        text = text
    )
}
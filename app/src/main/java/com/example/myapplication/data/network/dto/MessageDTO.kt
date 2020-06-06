package com.example.myapplication.data.network.dto

import com.example.myapplication.data.DateMapper
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
    val text: String,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {

    fun toBO(): MessageBO = MessageBO(
        id = id,
        microchatId = microchatId,
        userId = userId,
        referenceId = referenceId ?: "",
        text = text,
        createdAt = DateMapper.parseDate(createdAt),
        updatedAt = DateMapper.parseDate(updatedAt)
    )

    companion object {
        fun fromBO(messageBO: MessageBO): MessageDTO {
            return MessageDTO(
                id = messageBO.id,
                userId = messageBO.userId,
                microchatId = messageBO.microchatId,
                referenceId = messageBO.referenceId,
                text = messageBO.text
            )
        }
    }
}
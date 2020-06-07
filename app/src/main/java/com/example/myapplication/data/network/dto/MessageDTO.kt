package com.example.myapplication.data.network.dto

import com.example.myapplication.data.DateMapper
import com.example.myapplication.data.model.MessageBO
import com.google.gson.annotations.SerializedName

data class MessageDTO(
    val id: String,
    @SerializedName("microchat_id")
    val microchatId: String,
    val user: UserDTO?,
    @SerializedName("reference_id")
    val referenceId: String?,
    val text: String,
    @SerializedName("is_parent")
    val isParent: Boolean?,
    val microchatCount: Int?,
    @SerializedName("people_count")
    val peopleCount: Int?,
    val hot: Float?,
    val createdAt: String? = null,
    val updatedAt: String? = null
) {

    fun toBO(): MessageBO = MessageBO(
        id = id,
        microchatId = microchatId,
        user = user?.toBO(),
        referenceId = referenceId ?: "",
        text = text,
        isParent = isParent,
        microchatCount = microchatCount ?: 0,
        peopleCount = peopleCount ?: 0,
        hot = hot ?: 0F,
        isMy = null,
        createdAt = DateMapper.parseDate(createdAt),
        updatedAt = DateMapper.parseDate(updatedAt)
    )

    fun toBO(userId: String): MessageBO = MessageBO(
        id = id,
        microchatId = microchatId,
        user = user?.toBO(),
        referenceId = referenceId ?: "",
        text = text,
        isParent = isParent,
        microchatCount = microchatCount ?: 0,
        peopleCount = peopleCount ?: 0,
        hot = hot ?: 0F,
        isMy = userId == user?.id,
        createdAt = DateMapper.parseDate(createdAt),
        updatedAt = DateMapper.parseDate(updatedAt)
    )

    companion object {
        fun fromBO(messageBO: MessageBO): MessageDTO {
            return MessageDTO(
                id = messageBO.id,
                user = UserDTO.fromBO(messageBO.user),
                microchatId = messageBO.microchatId,
                referenceId = messageBO.referenceId,
                text = messageBO.text,
                isParent = messageBO.isParent,
                microchatCount = messageBO.microchatCount,
                peopleCount = messageBO.peopleCount,
                hot = messageBO.hot
            )
        }
    }
}
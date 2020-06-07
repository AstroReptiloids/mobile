package com.example.myapplication.data.network.dto

import com.example.myapplication.data.DateMapper
import com.example.myapplication.data.model.MicrochatBO
import com.google.gson.annotations.SerializedName

open class MicrochatDTO(
    val id: String,
    @SerializedName("parent_id")
    val parentId: String?,
    @SerializedName("category_id")
    val categoryId: String?,
    val title: String,
    val description: String?,
    @SerializedName("message_count")
    val messageCount: Int?,
    @SerializedName("microchat_count")
    val microchatCount: Int?,
    @SerializedName("people_count")
    val peopleCount: Int?,
    val hot: Float?,
    val createdAt: String,
    val updatedAt: String,
    val creator: UserDTO?
) {

    fun toBO(): MicrochatBO {
        return MicrochatBO(
            id = id,
            parentId = parentId ?: "",
            categoryId = categoryId,
            title = title,
            description = description,
            creator = creator?.toBO(),
            messageCount = messageCount ?: 0,
            microchatCount = microchatCount ?: 0,
            peopleCount = peopleCount ?: 0,
            hot = hot ?: 0F,
            createdAt = DateMapper.parseDate(createdAt),
            updatedAt = DateMapper.parseDate(updatedAt)
        )
    }
}
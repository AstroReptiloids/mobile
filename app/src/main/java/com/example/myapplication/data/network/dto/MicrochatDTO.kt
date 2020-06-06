package com.example.myapplication.data.network.dto

import com.example.myapplication.data.model.MicrochatBO
import com.google.gson.annotations.SerializedName

open class MicrochatDTO(
    val id: String,
    @SerializedName("parent_id")
    val parentId: String?,
    @SerializedName("category_id")
    val categoryId: String,
    val title: String,
    val description: String,
    @SerializedName("creator_id")
    val creatorId: String?,
    val timestamp: String?,
    @SerializedName("message_count")
    val messageCount: Int?,
    @SerializedName("microchat_count")
    val microchatCount: Int?,
    @SerializedName("people_count")
    val peopleCount: Int?,
    val hot: Float?
) {

    fun toBO(): MicrochatBO {
        return MicrochatBO(
            id = id,
            parentId = parentId ?: "",
            categoryId = categoryId,
            title = title,
            description = description,
            creatorId = creatorId ?: "",
            timestamp = timestamp ?: "",
            messageCount = messageCount ?: 0,
            microchatCount = microchatCount ?: 0,
            peopleCount = peopleCount ?: 0,
            hot = hot ?: 0F
        )
    }
}
package com.example.myapplication.data.model

import java.io.Serializable
import java.util.*

data class MicrochatBO(
    val id: String,
    val parentId: String,
    val categoryId: String?,
    val title: String,
    val description: String?,
    val creator: UserBO?,
    val messageCount: Int,
    val microchatCount: Int,
    val peopleCount: Int,
    val hot: Float,
    val createdAt: Date?,
    val updatedAt: Date?
) : Serializable
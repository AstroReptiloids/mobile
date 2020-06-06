package com.example.myapplication.data.model

data class MicrochatBO(
    val id: String,
    val parentId: String,
    val categoryId: String,
    val title: String,
    val description: String,
    val creatorId: String,
    val timestamp: String,
    val messageCount: Int,
    val microchatCount: Int,
    val peopleCount: Int,
    val hot: Float
)
package com.example.myapplication.data.model

import java.util.*

data class MessageBO(
    val id: String,
    val microchatId: String,
    val user: UserBO,
    val referenceId: String,
    val text: String,
    val isParent: Boolean?,
    val microchatCount: Int,
    val peopleCount: Int,
    val hot: Float,
    val createdAt: Date?,
    val updatedAt: Date?
)
package com.example.myapplication.data.model

import java.util.*

data class MessageBO(
    val id: String,
    val microchatId: String,
    val userId: String,
    val referenceId: String,
    val text: String,
    val createdAt: Date?,
    val updatedAt: Date?
)
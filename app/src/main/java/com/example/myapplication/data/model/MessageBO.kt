package com.example.myapplication.data.model

data class MessageBO(
    val id: String,
    val microchatId: String,
    val userId: String,
    val referenceId: String,
    val timestamp: String,
    val text: String
)
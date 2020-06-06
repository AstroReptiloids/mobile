package com.example.myapplication.data.network.requests

import com.google.gson.annotations.SerializedName

data class CreateMicrochatRequest(
    @SerializedName("parent_id")
    val parentId: String,
    val title: String,
    val description: String
)
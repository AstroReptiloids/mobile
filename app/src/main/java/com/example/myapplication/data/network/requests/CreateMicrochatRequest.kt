package com.example.myapplication.data.network.requests

import com.google.gson.annotations.SerializedName

data class CreateMicrochatRequest(
    val title: String,
    val description: String,
    @SerializedName("parent_id")
    val parentId: String? = null,
    @SerializedName("category_id")
    val categoryId: String? = null
)
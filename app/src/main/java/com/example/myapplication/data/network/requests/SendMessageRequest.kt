package com.example.myapplication.data.network.requests

import com.google.gson.annotations.SerializedName

data class SendMessageRequest(
    @SerializedName("microchat_id")
    val microchatId: String,
    @SerializedName("reference_id")
    val referenceId: String?,
    val text: String
)
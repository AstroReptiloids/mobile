package com.example.myapplication.data.network.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val data: Data?
) : BaseResponse() {
    data class Data(
        val token: String,
        @SerializedName("user_id")
        val userId: String
    )
}
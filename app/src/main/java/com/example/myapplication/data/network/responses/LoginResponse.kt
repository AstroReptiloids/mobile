package com.example.myapplication.data.network.responses

data class LoginResponse(
    val data: Data?
) : BaseResponse() {
    data class Data(
        val token: String,
        val userId: String
    )
}
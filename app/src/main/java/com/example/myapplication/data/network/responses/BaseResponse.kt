package com.example.myapplication.data.network.responses

open class BaseResponse {

    var error: Error? = null

    val isSuccess: Boolean
        get() = error == null

    data class Error(
        var code: Int,
        var message: String
    )
}
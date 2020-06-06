package com.example.myapplication.data.network.responses

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("error_code")
    var error: Error? = null

    val isSuccess: Boolean
        get() = error == null

    data class Error(
        var code: Int,
        var message: String
    )
}
package com.example.myapplication.data.network.responses

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("error_code")
    val errorCode: String? = null
    @SerializedName("error_message")
    val message: String? = null

    val isSuccess: Boolean
        get() = errorCode == null
}
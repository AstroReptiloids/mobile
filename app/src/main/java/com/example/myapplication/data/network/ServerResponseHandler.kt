package com.example.myapplication.data.network

import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.network.responses.BaseResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ServerResponseHandler() {

    @Throws(IOException::class)
    fun <T : BaseResponse> handleCode(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body()?.let { it } ?: run {
                throw NoConnectionToServerException()
            }
        }
        throw HttpException(response)
    }
}
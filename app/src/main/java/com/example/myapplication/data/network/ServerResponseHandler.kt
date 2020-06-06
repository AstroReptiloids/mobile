package com.example.myapplication.data.network

import com.example.myapplication.data.exceptions.InternalServerException
import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.network.responses.BaseResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ServerResponseHandler() {

    @Throws(IOException::class)
    fun <T : BaseResponse> handleCode(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body()?.let {
                if (it.isSuccess.not()) {
                    throw InternalServerException(it.error?.code ?: 0, it.error?.message)
                }
                it
            } ?: run {
                throw NoConnectionToServerException()
            }
        }
        throw HttpException(response)
    }
}
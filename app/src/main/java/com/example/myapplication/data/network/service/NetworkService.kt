package com.example.myapplication.data.network.service

import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.exceptions.NoNetworkException
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.responses.BaseResponse
import retrofit2.Call

class NetworkService(
    private val serverApi: ServerApi,
    private val networkStateWatcher: NetworkStateWatcher,
    private val serverResponseHandler: ServerResponseHandler
) : INetworkService {


    private fun <T : BaseResponse> execute(tCall: Call<T>): T {
        try {
            if (networkStateWatcher.isInternetAvailable()) {
                return serverResponseHandler.handleCode(tCall.execute())
            }
            throw NoNetworkException()
        } catch (e: Throwable) {
            throw NoConnectionToServerException(e.message)
        }
    }
}
package com.example.myapplication.data.network.service

import android.util.Log
import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.exceptions.NoNetworkException
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.responses.BaseResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class NetworkService(
    private val restApi: RestApi,
    private val webSocketApi: WebSocketApi,
    private val networkStateWatcher: NetworkStateWatcher,
    private val serverResponseHandler: ServerResponseHandler
) : INetworkService {

    private val wsDisposable: Disposable =
        webSocketApi.observeWebSocketEvent().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.i("wtf", "message $it")
            }


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
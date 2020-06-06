package com.example.myapplication.data.network.service

import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.exceptions.NoNetworkException
import com.example.myapplication.data.model.ChatCategoryBO
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.data.model.UserBO
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.responses.BaseResponse
import io.reactivex.Single
import retrofit2.Call
import java.util.*

class NetworkService(
    private val restApi: RestApi,
    //private val webSocketApi: WebSocketApi,
    private val networkStateWatcher: NetworkStateWatcher,
    private val serverResponseHandler: ServerResponseHandler
) : INetworkService {

    /*private val wsDisposable: Disposable =
        webSocketApi.observeWebSocketEvent().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.i("wtf", "message $it")
            }*/

    override fun getUsers(): Single<List<UserBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getUsers())
            return@fromCallable response.data?.map { userDTO -> userDTO.toBO() }
                ?: Collections.emptyList()
        }
    }

    override fun getChatCategories(): Single<List<ChatCategoryBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getChatCategories())
            return@fromCallable response.data?.map { chatCategoryDTO -> chatCategoryDTO.toBO() }
                ?: Collections.emptyList()
        }
    }

    override fun getMicrochats(): Single<List<MicrochatBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getMicrochats())
            return@fromCallable response.data?.map { microchatDTO -> microchatDTO.toBO() }
                ?: Collections.emptyList()
        }
    }

    override fun getMessages(): Single<List<MessageBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getMessages())
            return@fromCallable response.data?.map { messageDTO -> messageDTO.toBO() }
                ?: Collections.emptyList()
        }
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
package com.example.myapplication.data.network.repository

import android.util.Log
import com.example.myapplication.data.exceptions.NoConnectionToServerException
import com.example.myapplication.data.exceptions.NoNetworkException
import com.example.myapplication.data.model.ChatCategoryBO
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.data.model.UserBO
import com.example.myapplication.data.network.AuthorizationInterceptor
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.requests.LoginRequest
import com.example.myapplication.data.network.requests.SendMessageRequest
import com.example.myapplication.data.network.responses.BaseResponse
import com.example.myapplication.data.network.service.RestApi
import com.example.myapplication.data.network.service.WebSocketApi
import com.tinder.scarlet.WebSocket
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import java.util.*

class Repository(
    private val restApi: RestApi,
    private val webSocketApi: WebSocketApi,
    private val networkStateWatcher: NetworkStateWatcher,
    private val serverResponseHandler: ServerResponseHandler,
    private val authorizationInterceptor: AuthorizationInterceptor
) : IRepository {

    private val wsDisposable: Disposable =
        webSocketApi.observeWebSocketEvent().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                if(it is WebSocket.Event.OnConnectionOpened<*>){
                    webSocketApi.sendMess("hey huy")
                }
                Log.i("wtf", "message $it")
            }

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

    override fun getMicrochats(parentId: String?): Single<List<MicrochatBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getMicrochats(parentId))
            return@fromCallable response.data?.microchats?.map { microchatDTO -> microchatDTO.toBO() }
                ?: Collections.emptyList()
        }
    }

    override fun getMessages(microchatId: String?): Single<List<MessageBO>> {
        return Single.fromCallable {
            val response = execute(restApi.getMessages(microchatId))
            return@fromCallable response.data?.messages?.map { messageDTO -> messageDTO.toBO() }
                ?: Collections.emptyList()
        }
    }

    override fun auth(login: String, password: String): Single<Boolean> {
        return Single.fromCallable {
            val response = execute(restApi.login(LoginRequest(login, password)))
            val token = response.data?.token ?: return@fromCallable false
            authorizationInterceptor.token = token
            return@fromCallable true
        }
    }

    override fun isSignedIn(): Single<Boolean> {
        return Single.just(authorizationInterceptor.token != null)
    }

    override fun sendMessage(
        text: String,
        microchatId: String,
        referenceId: String?
    ): Single<MessageBO> {
        return Single.fromCallable {
            val request = SendMessageRequest(microchatId, referenceId, text)
            val response = execute(restApi.sendMessage(request))
            return@fromCallable response.data
        }.map { messageDTO -> messageDTO.toBO() }
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

    override fun observeNewMessages(): Observable<MessageBO> {
        return webSocketApi.observeNewMessages().map { it.toBO() }
    }
}
package com.example.myapplication.data.network.service

import com.example.myapplication.data.network.dto.MessageDTO
import com.example.myapplication.data.network.requests.SendTokenRequest
import com.example.myapplication.data.network.responses.WebSocketMessageResponse
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Observable

interface WebSocketApi {

    @Receive
    fun observeWebSocketEvent(): Observable<WebSocket.Event>

    @Receive
    fun observeNewMessages(): Observable<WebSocketMessageResponse>

    @Send
    fun sendToken(request: SendTokenRequest)
}
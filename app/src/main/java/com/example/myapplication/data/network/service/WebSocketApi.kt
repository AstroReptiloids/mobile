package com.example.myapplication.data.network.service

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import io.reactivex.Observable

interface WebSocketApi {

    @Receive
    fun observeWebSocketEvent(): Observable<WebSocket.Event>
}
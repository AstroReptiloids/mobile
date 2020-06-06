package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.data.network.service.INetworkService
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val networkService: INetworkService
) : IChatActivityPresenter() {

}
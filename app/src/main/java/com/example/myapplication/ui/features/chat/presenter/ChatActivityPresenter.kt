package com.example.myapplication.ui.features.chat.presenter

import com.example.myapplication.data.network.service.IRepository
import javax.inject.Inject

class ChatActivityPresenter @Inject constructor(
    private val networkService: IRepository
) : IChatActivityPresenter() {

}
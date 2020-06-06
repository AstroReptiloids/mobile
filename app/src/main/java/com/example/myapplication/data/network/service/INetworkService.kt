package com.example.myapplication.data.network.service

import com.example.myapplication.data.model.ChatCategoryBO
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.data.model.UserBO
import io.reactivex.Single

interface INetworkService {

    fun getUsers(): Single<List<UserBO>>

    fun getChatCategories(): Single<List<ChatCategoryBO>>

    fun getMicrochats(): Single<List<MicrochatBO>>

    fun getMessages(): Single<List<MessageBO>>

}
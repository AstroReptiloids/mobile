package com.example.myapplication.data.network.repository

import com.example.myapplication.data.model.ChatCategoryBO
import com.example.myapplication.data.model.MessageBO
import com.example.myapplication.data.model.MicrochatBO
import com.example.myapplication.data.model.UserBO
import io.reactivex.Observable
import io.reactivex.Single

interface IRepository {

    fun getUsers(): Single<List<UserBO>>

    fun getChatCategories(): Single<List<ChatCategoryBO>>

    fun getMicrochats(parentId: String? = null, categoryId: String? = null): Single<List<MicrochatBO>>

    fun getMessages(microchatId: String? = null): Single<List<MessageBO>>

    fun auth(login: String, password: String): Single<Boolean>

    fun isSignedIn(): Single<Boolean>

    fun sendMessage(
        text: String,
        microchatId: String,
        referenceId: String? = null
    ): Single<MessageBO>

    fun createMicrochat(
        title: String,
        description: String,
        parentId: String? = null,
        categoryId: String? = null
    ) : Single<MicrochatBO>

    fun observeNewMessages(): Observable<MessageBO>

}
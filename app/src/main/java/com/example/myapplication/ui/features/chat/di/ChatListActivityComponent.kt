package com.example.myapplication.ui.features.chat.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.chat.view.ChatActivity
import com.example.myapplication.ui.features.chat.view.ChatListActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ChatListActivityModule::class]
)
interface ChatListActivityComponent {

    fun inject(activity: ChatListActivity)
}
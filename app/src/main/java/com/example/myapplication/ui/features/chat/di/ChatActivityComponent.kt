package com.example.myapplication.ui.features.chat.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.chat.view.ChatActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ChatActivityModule::class]
)
interface ChatActivityComponent {

    fun inject(activity: ChatActivity)
}
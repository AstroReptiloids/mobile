package com.example.myapplication.ui.features.chat.di

import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.chat.presenter.ChatActivityPresenter
import com.example.myapplication.ui.features.chat.presenter.ChatListActivityPresenter
import com.example.myapplication.ui.features.chat.presenter.IChatActivityPresenter
import com.example.myapplication.ui.features.chat.presenter.IChatListActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class ChatListActivityModule {

    @Provides
    @PerActivity
    fun providePresenter(presenter: ChatListActivityPresenter): IChatListActivityPresenter {
        return presenter
    }
}
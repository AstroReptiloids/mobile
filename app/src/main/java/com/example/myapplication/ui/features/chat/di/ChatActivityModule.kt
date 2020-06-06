package com.example.myapplication.ui.features.chat.di

import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.chat.presenter.ChatActivityPresenter
import com.example.myapplication.ui.features.chat.presenter.IChatActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class ChatActivityModule {

    @Provides
    @PerActivity
    fun providePresenter(presenter: ChatActivityPresenter): IChatActivityPresenter {
        return presenter
    }
}
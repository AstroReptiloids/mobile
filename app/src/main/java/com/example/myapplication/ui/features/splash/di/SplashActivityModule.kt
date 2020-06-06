package com.example.myapplication.ui.features.splash.di

import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.splash.presenter.ISplashActivityPresenter
import com.example.myapplication.ui.features.splash.presenter.SplashActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    @PerActivity
    fun providePresenter(presenter: SplashActivityPresenter): ISplashActivityPresenter {
        return presenter
    }
}
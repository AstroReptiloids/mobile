package com.example.myapplication.ui.features.login.di

import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.login.presenter.LoginActivityPresenter
import com.example.myapplication.ui.features.login.presenter.ILoginActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    @PerActivity
    fun providePresenter(presenter: LoginActivityPresenter): ILoginActivityPresenter {
        return presenter
    }

}
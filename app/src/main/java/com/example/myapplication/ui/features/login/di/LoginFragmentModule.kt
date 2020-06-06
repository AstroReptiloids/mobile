package com.example.myapplication.ui.features.login.di

import com.example.myapplication.di.scopes.PerFragment
import com.example.myapplication.ui.features.login.presenter.LoginFragmentPresenter
import com.example.myapplication.ui.features.login.presenter.ILoginFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginFragmentModule {

    @Provides
    @PerFragment
    fun providePresenter(presenter: LoginFragmentPresenter): ILoginFragmentPresenter {
        return presenter
    }

}
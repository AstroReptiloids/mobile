package com.example.myapplication.ui.features.menu.di

import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.menu.presenter.IMenuActivityPresenter
import com.example.myapplication.ui.features.menu.presenter.MenuActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MenuActivityModule {

    @PerActivity
    @Provides
    fun providePresenter(presenter: MenuActivityPresenter): IMenuActivityPresenter {
        return presenter
    }
}
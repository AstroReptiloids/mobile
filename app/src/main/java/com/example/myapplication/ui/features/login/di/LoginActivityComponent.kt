package com.example.myapplication.ui.features.login.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.login.view.LoginActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [LoginActivityModule::class]
)
interface LoginActivityComponent {

    fun inject(fragment: LoginActivity)
}
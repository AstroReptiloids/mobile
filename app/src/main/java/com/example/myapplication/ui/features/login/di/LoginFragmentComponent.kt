package com.example.myapplication.ui.features.login.di

import com.example.myapplication.di.ActivityComponent
import com.example.myapplication.di.scopes.PerFragment
import com.example.myapplication.ui.features.login.view.LoginActivity
import dagger.Component

@PerFragment
@Component(
    dependencies = [ActivityComponent::class],
    modules = [LoginFragmentModule::class]
)
interface LoginFragmentComponent {

    fun inject(fragment: LoginActivity)
}
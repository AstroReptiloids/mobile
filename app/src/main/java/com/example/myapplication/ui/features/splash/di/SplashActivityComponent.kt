package com.example.myapplication.ui.features.splash.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.splash.view.SplashActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [SplashActivityModule::class]
)
interface SplashActivityComponent {

    fun inject(activity: SplashActivity)

}
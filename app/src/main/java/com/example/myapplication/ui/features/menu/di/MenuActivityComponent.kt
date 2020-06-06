package com.example.myapplication.ui.features.menu.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.menu.view.MenuActivity
import dagger.Component

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [MenuActivityModule::class]
)
interface MenuActivityComponent {

    fun inject(activity: MenuActivity)
}
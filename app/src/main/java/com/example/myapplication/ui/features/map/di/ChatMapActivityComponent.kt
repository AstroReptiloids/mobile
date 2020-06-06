package com.example.myapplication.ui.features.map.di

import com.example.myapplication.di.ApplicationComponent
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.features.map.view.ChatMapActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class])
interface ChatMapActivityComponent {

    fun inject(activity: ChatMapActivity)
}
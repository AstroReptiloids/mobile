package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.network.repository.IRepository
import com.example.myapplication.di.modules.ApplicationModule
import com.example.myapplication.di.modules.NetworkModule
import com.example.myapplication.ui.base.handler.IErrorHandler
import com.example.myapplication.ui.base.utils.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    val context: Context
    val errorHandler: IErrorHandler
    val repository: IRepository
    val router: Router
}
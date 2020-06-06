package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.ui.base.handler.ErrorHandler
import com.example.myapplication.ui.base.handler.IErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(
    private val context: Context
) {

    @Provides
    @Singleton
    internal fun providerContext(): Context {
        return context
    }

    @Provides
    @Singleton
    internal fun providerHandler(context: Context): IErrorHandler {
        return ErrorHandler(context)
    }

}
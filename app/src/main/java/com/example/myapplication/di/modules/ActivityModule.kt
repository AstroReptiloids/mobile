package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.di.scopes.PerActivity
import com.example.myapplication.ui.base.handler.ErrorHandler
import com.example.myapplication.ui.base.handler.IErrorHandler
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val context: Context
) {

    @Provides
    @PerActivity
    internal fun providerContext(): Context {
        return context
    }

    @Provides
    @PerActivity
    internal fun providerHandler(context: Context): IErrorHandler {
        return ErrorHandler(context)
    }

}
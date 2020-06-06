package com.example.myapplication.ui.base.handler

import android.util.Log
import com.example.myapplication.ui.base.view.IView

interface IErrorHandler {

    fun handleError(throwable: Throwable, view: IView?) {
        Log.e(javaClass.simpleName, throwable.message, throwable)
    }
}
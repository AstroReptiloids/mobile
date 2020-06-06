package com.example.myapplication.ui.base.presenter

import androidx.lifecycle.Lifecycle
import com.example.myapplication.ui.base.view.IView

interface IPresenter<V : IView> {

    fun bindView(view: V)

    fun attachLifecycle(lifecycle: Lifecycle)

    fun detachLifecycle(lifecycle: Lifecycle)
}
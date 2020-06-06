package com.example.myapplication.ui.base.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import com.example.myapplication.ui.base.presenter.IPresenter
import com.example.myapplication.ui.base.view.IView
import javax.inject.Inject

abstract class BaseMvpActivity<B : ViewDataBinding, V : IView, P : IPresenter<V>> :
    BaseActivity<B>(), IView {

    @Inject
    lateinit var presenter: P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachLifecycle(lifecycle)
        @Suppress("UNCHECKED_CAST")
        presenter.bindView(this as V)
    }
}
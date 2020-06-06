package com.example.myapplication.ui.features.menu.view

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMenuBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.menu.di.DaggerMenuActivityComponent
import com.example.myapplication.ui.features.menu.di.MenuActivityModule
import com.example.myapplication.ui.features.menu.presenter.IMenuActivityPresenter

class MenuActivity : BaseMvpActivity<ActivityMenuBinding, IMenuView, IMenuActivityPresenter>(),
    IMenuView {

    override val layoutResId: Int
        get() = R.layout.activity_menu

    override fun injectDependencies() {
        DaggerMenuActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .menuActivityModule(MenuActivityModule())
            .build()
            .inject(this)
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }
}
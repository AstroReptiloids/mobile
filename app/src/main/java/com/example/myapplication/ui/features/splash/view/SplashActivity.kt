package com.example.myapplication.ui.features.splash.view

import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.splash.di.DaggerSplashActivityComponent
import com.example.myapplication.ui.features.splash.di.SplashActivityModule
import com.example.myapplication.ui.features.splash.presenter.ISplashActivityPresenter

class SplashActivity :
    BaseMvpActivity<ActivitySplashBinding, ISplashView, ISplashActivityPresenter>(), ISplashView {

    override val layoutResId: Int
        get() = R.layout.activity_splash

    override fun navigateMenu() {
        router.navigateMenu(this)
    }

    override fun navigateLoginScreen() {
        router.navigateLoginActivity(this)
    }

    override fun injectDependencies() {
        DaggerSplashActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .splashActivityModule(SplashActivityModule())
            .build()
            .inject(this)
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }
}
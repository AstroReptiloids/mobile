package com.example.myapplication.ui.features.splash.presenter

import com.example.myapplication.data.network.repository.IRepository
import com.example.myapplication.ui.features.splash.view.ISplashView
import javax.inject.Inject

class SplashActivityPresenter @Inject constructor(
    private val repository: IRepository
) : ISplashActivityPresenter() {

    override fun bindView(view: ISplashView) {
        super.bindView(view)
        runAsync(repository.isSignedIn(), {
            if (it) {
                view.navigateMenu()
            } else {
                view.navigateLoginScreen()
            }
        }, {}, true)
    }
}
package com.example.myapplication.ui.features.login.presenter

import com.example.myapplication.data.network.service.INetworkService
import io.reactivex.Single
import javax.inject.Inject

class LoginFragmentPresenter @Inject constructor(
    private val networkService: INetworkService
) : ILoginFragmentPresenter() {

    override fun onSignInClicked(login: String?, password: String?) {
        runAsync(Single.just("124"), {}, {}, true)
    }
}
package com.example.myapplication.ui.features.login.presenter

import com.example.myapplication.R
import com.example.myapplication.data.network.repository.IRepository
import javax.inject.Inject

class LoginActivityPresenter @Inject constructor(
    private val repository: IRepository
) : ILoginActivityPresenter() {

    override fun onSignInClicked(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank()) {
            return
        }
        runAsync(repository.auth(login, password), {
            view?.navigateMenu()
        }, {
            view?.showToast(R.string.auth_error_text)
        }, true)
    }
}
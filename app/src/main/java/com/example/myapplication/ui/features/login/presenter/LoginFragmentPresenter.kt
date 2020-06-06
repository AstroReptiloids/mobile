package com.example.myapplication.ui.features.login.presenter

import android.util.Log
import com.example.myapplication.data.network.service.IRepository
import javax.inject.Inject

class LoginFragmentPresenter @Inject constructor(
    private val repository: IRepository
) : ILoginFragmentPresenter() {

    override fun onSignInClicked(login: String?, password: String?) {
        if (login.isNullOrBlank() || password.isNullOrBlank()) {
            return
        }
        runAsync(repository.auth(login, password), {
            Log.i("wtf", "auth success")
        }, {
            Log.i("wtf", "auth failed")
        }, true)
    }
}